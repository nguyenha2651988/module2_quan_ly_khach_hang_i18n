package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/home")
    public ModelAndView listCustomer(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/list");
        Page<Customer> customers = customerService.findAll(pageable);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("view/{id}")
    public ModelAndView viewCustomer(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/view");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("create-customer")
    public ModelAndView createCustomer(@ModelAttribute Customer customer, RedirectAttributes redirec) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        redirec.addAttribute("a", "abc");
        customerService.save(customer);
        return modelAndView;
    }

    @GetMapping("update/{id}")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/edit");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("update-customer")
    public ModelAndView updateCustomer(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        customerService.save(customer);
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/delete");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @PostMapping("delete-customer")
    public ModelAndView deleteCustomer(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        customerService.delete(customer);
        return modelAndView;
    }
}
