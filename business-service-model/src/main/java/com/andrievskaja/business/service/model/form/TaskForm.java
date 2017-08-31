/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service.model.form;

/**
 *
 * @author Людмила
 */
public class TaskForm {

    private Long id;
    private String task;
    private boolean done;

    public TaskForm() {
    }

    public TaskForm(Long id, String task, boolean done) {
        this.id = id;
        this.task = task;
        this.done = done;
    }

    public TaskForm(String task, boolean done) {
        this.task = task;
        this.done = done;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
