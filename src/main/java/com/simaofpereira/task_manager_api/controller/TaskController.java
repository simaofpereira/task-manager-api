package com.simaofpereira.task_manager_api.controller;

import com.simaofpereira.task_manager_api.model.Task;
import com.simaofpereira.task_manager_api.model.User;
import com.simaofpereira.task_manager_api.security.AuthenticatedUserProvider;
import com.simaofpereira.task_manager_api.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public TaskController(TaskService service, AuthenticatedUserProvider authenticatedUserProvider) {
        this.service = service;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        User owner = authenticatedUserProvider.getCurrentUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTask(task, owner));
    }

    @GetMapping
    public List<Task> getAll() {
        User owner = authenticatedUserProvider.getCurrentUser();
        return service.getAllTasks(owner);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        User owner = authenticatedUserProvider.getCurrentUser();
        return service.getTaskById(id, owner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task updatedTask) {
        User owner = authenticatedUserProvider.getCurrentUser();
        return ResponseEntity.ok(service.updateTask(id, updatedTask, owner));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        User owner = authenticatedUserProvider.getCurrentUser();
        service.deleteTask(id, owner);
        return ResponseEntity.noContent().build();
    }
}