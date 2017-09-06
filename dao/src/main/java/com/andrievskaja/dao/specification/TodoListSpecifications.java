/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.dao.specification;

import com.andrievskaja.business.model.TodoList;
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
public class TodoListSpecifications {
      public static Specification<TodoList> todoList_id(final Long id) {
        if (id == null) {
            return null;
        }
        return new Specification<TodoList>() {
            @Override
            public Predicate toPredicate(Root<TodoList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(TodoList_.id), id);
//                 return cb.equal(root.get(Site_.id), id);
            }
        };
    }   
      public static Specification<TodoList> todoList_user(final Long userId) {
        if (userId == null) {
            return null;
        }
        return new Specification<TodoList>() {
            @Override
            public Predicate toPredicate(Root<TodoList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(TodoList_.user), userId);
            }
        };
    }   
}
