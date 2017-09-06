/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.dao.specification;

import com.andrievskaja.business.model.Task;
import com.andrievskaja.business.model.Task_;
import com.andrievskaja.business.model.TodoList_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Людмила
 */
public class TaskSpecifications {

    public static Specification<Task> task_id(final Long taskId) {
        if (taskId == null) {
            return null;
        }
        return new Specification<Task>() {
            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(Task_.id), taskId);
            }
        };
    }
    public static Specification<Task> task_user(final Long userId) {
        if (userId == null) {
            return null;
        }
        return new Specification<Task>() {
            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.join(Task_.todoList).get(TodoList_.user),userId);
            }
        };
    }
    public static Specification<Task> task_todoList(final Long todoListId) {
        if (todoListId == null) {
            return null;
        }
        return new Specification<Task>() {
            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(Task_.todoList).get(TodoList_.id),todoListId);
            }
        };
    }
}
