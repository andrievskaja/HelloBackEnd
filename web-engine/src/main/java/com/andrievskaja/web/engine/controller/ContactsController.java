/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.web.engine.controller;

import com.andrievskaja.business.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.andrievskaja.business.service.TaskService;
import com.andrievskaja.business.service.exception.TaskDeleteException;
import com.andrievskaja.business.service.model.form.TaskForm;
import com.andrievskaja.business.service.model.view.TaskView;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Людмила
 */
@Controller
@RequestMapping("/task")
public class ContactsController {

    @Autowired
    private TaskService contactsService;

    /**
     *
     * @author nameFilter
     * @param form
     * @param result
     * @param request
     * @param userProfile
     * @return
     *
     */
    @ResponseBody
    @RequestMapping("/add")
    public TaskView add(@Valid TaskForm form, BindingResult result, HttpServletRequest request, Principal userProfile) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        form.setUserId(user.getUserId());
        return contactsService.add(form);
    }

    @ResponseBody
    @RequestMapping("/edit")
    public TaskView edit(@Valid TaskForm form, BindingResult result) throws TaskDeleteException {
        return contactsService.edit(form);
    }


    @RequestMapping("/changeStatus")
    public void checkboxTask(boolean status, Long id) throws TaskDeleteException {
        contactsService.changeStatus(id);

    }
}
