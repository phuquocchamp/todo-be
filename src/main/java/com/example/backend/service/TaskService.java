package com.example.backend.service;

import com.example.backend.dto.TaskRequest;
import com.example.backend.dto.TaskResponse;
import com.example.backend.entity.Task;
import com.example.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse createTask(TaskRequest task){
        Task newTask = new Task();
        newTask.setName(task.getName());
        newTask.setDueDate(task.getDueDate());
        newTask.setIsDone(task.getIsDone());

        Task savedTask = taskRepository.save(newTask);
        return new TaskResponse(
                savedTask.getId(),
                savedTask.getName(),
                savedTask.getDueDate(),
                savedTask.getIsDone()
        );
    }

    public List<TaskResponse> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(
                task -> new TaskResponse(
                        task.getId(),
                        task.getName(),
                        task.getDueDate(),
                        task.getIsDone())
                )
                .toList();
    }

    public TaskResponse doneTask(Long id){
        Task retrievedTask = taskRepository.findById(id).orElseThrow();
        retrievedTask.setIsDone(!retrievedTask.getIsDone());
        taskRepository.save(retrievedTask);

        return new TaskResponse(
                retrievedTask.getId(),
                retrievedTask.getName(),
                retrievedTask.getDueDate(),
                retrievedTask.getIsDone()
        );
    }
 }
