package com.example.backend.controller;

import com.example.backend.dto.TaskRequest;
import com.example.backend.dto.TaskResponse;
import com.example.backend.entity.Task;
import com.example.backend.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/v1/api/tasks")
public class TaskController {
    private final TaskService taskService;
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return new ResponseEntity<>("Welcome", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest task){
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<TaskResponse>> fetchTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> doneTask(@PathVariable("id") Long id){
        return new ResponseEntity<>(taskService.doneTask(id), HttpStatus.OK);
    }

}
