/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.web.engine.controller;

import com.andrievskaja.business.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Людмила
 */
@Controller
@RequestMapping("/")
public class StartController {

    @Autowired
    private ContactsService contactsService;

    @RequestMapping("/")
    public String startPage(Model model) {
        model.addAttribute("contacts", contactsService.listContacts()); 
        return "/start";
    }
}
