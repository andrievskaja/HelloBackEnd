/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.web.engine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.andrievskaja.business.service.TaskService;
import com.andrievskaja.business.service.exception.TaskDeleteException;
import com.andrievskaja.business.service.model.form.TaskForm;
import com.andrievskaja.business.service.model.view.TaskView;
import javax.validation.Valid;
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
     * @return
     *
     */
    @ResponseBody
    @RequestMapping("/add")
    public TaskView add(@Valid TaskForm form, BindingResult result) {
        return contactsService.add(form);
    }
    @ResponseBody
    @RequestMapping("/edit")
    public TaskView edit(@Valid TaskForm form, BindingResult result) throws TaskDeleteException {
        return contactsService.edit(form);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Long id) {
        try {
            contactsService.delete(id);
        } catch (TaskDeleteException ex) {
            return ex.getCode().toString();
        }
        return "ok";
    }

    @RequestMapping("/changeStatus")
    public void checkboxTask(boolean status, Long id) throws TaskDeleteException {
        contactsService.changeStatus(id);

    }
}
