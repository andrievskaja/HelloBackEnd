/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service.model.view;

/**
 *
 * @author Людмила
 */
public class TaskView {

    private Integer id;
    private String task;
    private boolean done;

    public TaskView(Integer id, String task, boolean done) {
        this.id = id;
        this.task = task;
        this.done = done;
    }

    public TaskView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
