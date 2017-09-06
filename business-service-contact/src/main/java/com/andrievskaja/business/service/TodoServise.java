/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service;

import com.andrievskaja.business.service.model.form.TodoListForm;
import com.andrievskaja.business.service.model.view.TodoListView;
import java.util.List;

/**
 *
 * @author Людмила
 */
public interface TodoServise {

    public List<TodoListView> findAll(Long userId);

    public TodoListView add(Long userId);

    public TodoListView edit(TodoListForm todoListForm);

    public boolean delete(Long todoListId, Long userId);

}
