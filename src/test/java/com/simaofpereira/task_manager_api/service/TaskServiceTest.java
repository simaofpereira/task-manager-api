package com.simaofpereira.task_manager_api.service;

import com.simaofpereira.task_manager_api.exception.TaskNotFoundException;
import com.simaofpereira.task_manager_api.model.Task;
import com.simaofpereira.task_manager_api.model.TaskStatus;
import com.simaofpereira.task_manager_api.model.User;
import com.simaofpereira.task_manager_api.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    // A reusable fake user, used as the "owner" in every test
    private User owner;

    @BeforeEach
    void setUp() {
        owner = new User();
        owner.setId(1L);
        owner.setEmail("owner@test.com");
    }

    @Test
    void shouldCreateTaskWithPendingStatusByDefault() {
        // Arrange
        Task newTask = new Task();
        newTask.setTitle("Study Spring");

        when(repository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Task result = service.createTask(newTask, owner);

        // Assert
        assertEquals(TaskStatus.PENDING, result.getStatus());
        assertNotNull(result.getCreatedAt());
        assertEquals(owner, result.getOwner());
        verify(repository, times(1)).save(newTask);
    }

    @Test
    void shouldReturnTaskWhenIdExistsAndBelongsToOwner() {
        // Arrange
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Existing task");
        existingTask.setOwner(owner);

        when(repository.findById(1L)).thenReturn(Optional.of(existingTask));

        // Act
        Task result = service.getTaskById(1L, owner);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Existing task", result.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        // Arrange
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> service.getTaskById(99L, owner));
    }

    @Test
    void shouldUpdateExistingTask() {
        // Arrange
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Old title");
        existingTask.setStatus(TaskStatus.PENDING);
        existingTask.setOwner(owner);

        Task updatedData = new Task();
        updatedData.setTitle("New title");
        updatedData.setStatus(TaskStatus.DONE);

        when(repository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(repository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Task result = service.updateTask(1L, updatedData, owner);

        // Assert
        assertEquals("New title", result.getTitle());
        assertEquals(TaskStatus.DONE, result.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenTaskBelongsToAnotherUser() {
        // Arrange: task belongs to a different user
        User anotherUser = new User();
        anotherUser.setId(2L);

        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setOwner(anotherUser);

        when(repository.findById(1L)).thenReturn(Optional.of(existingTask));

        // Act & Assert: the current owner should not be able to access it
        assertThrows(TaskNotFoundException.class, () -> service.getTaskById(1L, owner));
    }

    @Test
    void shouldDeleteExistingTask() {
        // Arrange
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setOwner(owner);

        when(repository.findById(1L)).thenReturn(Optional.of(existingTask));

        // Act
        service.deleteTask(1L, owner);

        // Assert
        verify(repository, times(1)).delete(existingTask);
    }

}
