package com.andrievskaja.business.service.impl;

import com.andrievskaja.business.model.Task;
import com.andrievskaja.business.model.TodoList;
import com.andrievskaja.business.service.TaskService;
import com.andrievskaja.business.service.exception.FaultCode;
import com.andrievskaja.business.service.exception.TaskDeleteException;
import com.andrievskaja.business.service.model.form.TaskForm;
import com.andrievskaja.business.service.model.view.TaskView;
import com.andrievskaja.dao.TaskRepository;
import com.andrievskaja.dao.TodoListRepository;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    private TodoListRepository todoListRepository;

    /*
    Add new task in TaskTable
     */
    @Transactional
    @Override
    public TaskView add(TaskForm form) {
        TodoList todoList = todoListRepository.findByIdAndUserId(form.getTodoListId(), form.getUserId());
        if (todoList == null) {
            return null;
        }
        Task task = new Task();
        mapper.map(form, task);
        task.setTodoList(todoList);
        task = taskRepository.save(task);
        return mapper.map(task, TaskView.class);
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

    /*
    Delete the Task  from TaskTable 
     */
    @Transactional
    @Override
    public void delete(Long id, Long idTodo, Long userId) throws TaskDeleteException {
        Task task = taskRepository.findByIdAndTodoListId(id, idTodo);
        if (task == null) {
            throw new TaskDeleteException(FaultCode.TASK_NOT_DELETE);
        }
        taskRepository.delete(task);
    }

    /*
    Edit  task in TaskTable
     */
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
    /*
    Execution status the Task in TaskTable
     */
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
