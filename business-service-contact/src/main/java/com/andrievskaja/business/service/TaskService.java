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

    public TaskView add(TaskForm form);

    public TaskView get(long id);

    public List<TaskView> getAll();

    public void delete(Long id) throws TaskDeleteException;

    public TaskView edit(TaskForm form) throws TaskDeleteException ;

    public void changeStatus(Long id)throws TaskDeleteException ;

}
