package com.simaofpereira.task_manager_api.service;

import com.simaofpereira.task_manager_api.exception.TaskNotFoundException;
import com.simaofpereira.task_manager_api.model.Task;
import com.simaofpereira.task_manager_api.model.TaskStatus;
import com.simaofpereira.task_manager_api.model.User;
import com.simaofpereira.task_manager_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // Creates a task and assigns it to the authenticated user
    public Task createTask(Task task, User owner) {
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(TaskStatus.PENDING);
        task.setOwner(owner);
        return repository.save(task);
    }

    // Returns only the tasks that belong to the given user
    public List<Task> getAllTasks(User owner) {
        return repository.findByOwner(owner);
    }

    // Returns a task by id, but only if it belongs to the given user
    public Task getTaskById(Long id, User owner) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (!task.getOwner().getId().equals(owner.getId())) {
            throw new TaskNotFoundException(id); // hide existence of tasks that aren't yours
        }

        return task;
    }

    public Task updateTask(Long id, Task updatedTask, User owner) {
        Task existingTask = getTaskById(id, owner); // already checks ownership

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setDueDate(updatedTask.getDueDate());

        return repository.save(existingTask);
    }

    public void deleteTask(Long id, User owner) {
        Task existingTask = getTaskById(id, owner); // already checks ownership
        repository.delete(existingTask);
    }
}