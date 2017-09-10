/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service.impl;

import com.andrievskaja.business.model.TodoList;
import com.andrievskaja.business.model.User;
import com.andrievskaja.business.service.TodoServise;
import com.andrievskaja.business.service.model.form.TodoListForm;
import com.andrievskaja.business.service.model.view.TodoListView;
import com.andrievskaja.dao.TodoListRepository;
import com.andrievskaja.dao.UserRepository;

import static com.andrievskaja.dao.specification.TodoListSpecifications.todoList_id;
import static com.andrievskaja.dao.specification.TodoListSpecifications.todoList_user;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import static org.springframework.data.jpa.domain.Specifications.where;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

;

/**
 *
 * @author Людмила
 */
@Service("todoServise")
public class TodoServiseImpl implements TodoServise {

    private final ModelMapper mapper = new ModelMapper();
    @Autowired
    private TodoListRepository todoListRepository;
    @Autowired
    private UserRepository userRepository;

    /*
    Find the TodoTables by authorization the user 
     */
    @Override
    @Transactional
    public List<TodoListView> findAll(Long userId) {
        List<TodoList> todoList = todoListRepository.findAll(
                where(todoList_user(userId)));
        List<TodoListView> todoListView = new ArrayList<>(todoList.size());
        todoList.stream().forEach((list) -> {
            todoListView.add(mapper.map(list, TodoListView.class));
        });
        return todoListView;
    }
  /*
    Add new TodoTables. Find user by id and add his to TodoList
     */
    @Override
    @Transactional
    public TodoListView add(Long userId) {
        User user = userRepository.findOne(userId);
        TodoList todoList = new TodoList();
        todoList.setUser(user);
        todoListRepository.save(todoList);
        return mapper.map(todoList, TodoListView.class);
    }
  /*
    Edit the TaskTable. Find todoList by todoListId and userId
     */
    @Override
    @Transactional
    public TodoListView edit(TodoListForm todoListForm) {
        TodoList todoList = todoListRepository.findByIdAndUserId(todoListForm.getTodoListId(), todoListForm.getUserId());
        if (todoList == null) {
            return null;
        }
        todoList.setName(todoListForm.getName());
        todoListRepository.save(todoList);
        return mapper.map(todoList, TodoListView.class);
    }
  /*
    Delete the TaskTable. Find todoList by todoListId and userId
     */
    @Override
    @Transactional
    public boolean delete(Long todoListId, Long userId) {
        TodoList todoList = todoListRepository.findByIdAndUserId(todoListId, userId);
        if (todoList == null) {
            return false;
        }
        todoListRepository.delete(todoList);
        return true;
    }

}
