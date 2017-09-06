/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.web.engine.controller;

/**
 *
 * @author Людмила
 */
import com.andrievskaja.business.model.UserProfile;
import com.andrievskaja.business.service.TaskService;
import com.andrievskaja.business.service.TodoServise;
import com.andrievskaja.business.service.exception.TaskDeleteException;
import com.andrievskaja.business.service.model.form.TaskForm;
import com.andrievskaja.business.service.model.view.TaskView;
import com.andrievskaja.business.service.model.view.TodoListView;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/portal/user")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class TaskController {

    @Autowired
    private TaskService contactsService;
    @Autowired
    private TodoServise todoServise;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor(null, true));
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(DateFormat.getDateInstance(DateFormat.SHORT), true, 10));
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(
                        new SimpleDateFormat("dd.MM.yyyy"), false)
        );
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getTaskTodo(Model model, HttpServletRequest request, Principal userProfile) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TodoListView> list = todoServise.findAll(user.getUserId());
        model.addAttribute("todoList", list);
        return "portal/tasks";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public TodoListView add(Model model, HttpServletRequest request) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TodoListView view = todoServise.add(user.getUserId());
        return view;
    }

    @ResponseBody
    @RequestMapping("/addTask")
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

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Long id, Long idTodo) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            contactsService.delete(id, idTodo, user.getUserId());
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
