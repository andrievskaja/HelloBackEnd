package com.andrievskaja.business.service.impl;

import com.andrievskaja.business.model.Task;
import com.andrievskaja.business.service.TaskService;
import com.andrievskaja.business.service.exception.FaultCode;
import com.andrievskaja.business.service.exception.TaskDeleteException;
import com.andrievskaja.business.service.model.form.TaskForm;
import com.andrievskaja.business.service.model.view.TaskView;
import com.andrievskaja.dao.TaskRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Людмила
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    @Override
    public TaskView add(TaskForm form) {
        Task tack = taskRepository.save(mapper.map(form, Task.class));
        return mapper.map(tack, TaskView.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskView> getAll() {
        List<Task> lists = taskRepository.findAll();
        List<TaskView> views = new ArrayList(lists.size());
        lists.stream().forEach((list) -> {
            views.add(mapper.map(list, TaskView.class));
        });
        return views;
    }

    @Transactional(readOnly = true)
    @Override
    public TaskView get(long id) {
        Task task = taskRepository.findOne(id);
        return mapper.map(task, TaskView.class);
    }

    @Transactional
    @Override
    public void delete(Long id) throws TaskDeleteException {
        Task task = taskRepository.findOne(id);
        if (task == null) {
            throw new TaskDeleteException(FaultCode.TASK_NOT_DELETE);
        }
        taskRepository.delete(task);
    }

    @Transactional
    @Override
    public TaskView edit(TaskForm form) throws TaskDeleteException {
        Task task = taskRepository.findOne(form.getId());
        if (task == null) {
            throw new TaskDeleteException(FaultCode.TASK_NOT_DELETE);
        }
        task.setTask(form.getTask());
        taskRepository.save(task);
        return mapper.map(task, TaskView.class);
    }

    @Transactional
    @Override
    public void changeStatus(Long id) throws TaskDeleteException {
        Task task = taskRepository.findOne(id);
        if (task == null) {
            throw new TaskDeleteException(FaultCode.TASK_NOT_DELETE);
        } else {
            if (task.isDone() == true) {
                task.setDone(false);
            } else {
                task.setDone(true);
            }
            taskRepository.save(task);
        }
    }
}
