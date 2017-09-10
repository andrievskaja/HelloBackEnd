package com.andrievskaja.business.service;

import com.andrievskaja.business.service.exception.TaskDeleteException;
import com.andrievskaja.business.service.model.form.TaskForm;
import com.andrievskaja.business.service.model.view.TaskView;
import java.util.List;

/**
 *
 * @author Людмила
 */
public interface TaskService {

    /*
    Add new task in TaskTable
     */
    public TaskView add(TaskForm form);

    public TaskView get(long id);

    public List<TaskView> getAll();

    /*
    Delete  task in TaskTable
     */
    public void delete(Long id, Long idTodo, Long userId) throws TaskDeleteException;

    /*
    Edit  task in TaskTable
     */
    public TaskView edit(TaskForm form) throws TaskDeleteException;

    /*
    Execution status the TaskTable
     */
    public void changeStatus(Long id) throws TaskDeleteException;

}
