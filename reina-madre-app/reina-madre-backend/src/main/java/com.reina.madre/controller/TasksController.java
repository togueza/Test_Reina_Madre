package com.reina.madre.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.reina.madre.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reina.madre.repository.TasksRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TasksController {
  @Autowired
  TasksRepository tasksRepository;
  @GetMapping("/tasks")
  public ResponseEntity<List<Tasks>> getAllTasks(@RequestParam(required = false) String title) {
    try {
      List<Tasks> tasks = new ArrayList<Tasks>();

      if (title == null || title.isEmpty()) {
        tasksRepository.findAll().forEach(tasks::add);
      } else if (title.equalsIgnoreCase("Completed")) {
          tasksRepository.findByCompleted(true).forEach(tasks::add);
      } else if (title.equalsIgnoreCase("Uncompleted")) {
          tasksRepository.findByCompleted(false).forEach(tasks::add);
      } else if (title.equalsIgnoreCase("ALTA") || title.equalsIgnoreCase("MEDIA") || title.equalsIgnoreCase("BAJA")) {
        tasksRepository.findByPriorities(title).forEach(tasks::add);
      }

      if (tasks.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tasks, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/tasks/{id}")
  public ResponseEntity<Tasks> getTasksById(@PathVariable("id") long id) {
    Optional<Tasks> tasksData = tasksRepository.findById(id);

    if (tasksData.isPresent()) {
      return new ResponseEntity<>(tasksData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/tasks")
  public ResponseEntity<Tasks> createTasks(@RequestBody Tasks tasks) {
    try {
      Tasks _tasks = tasksRepository.save(new Tasks(tasks.getTitle(), tasks.getDescription(), false, tasks.getPriorities()));
      return new ResponseEntity<>(_tasks, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/tasks/{id}")
  public ResponseEntity<Tasks> updateTasks(@PathVariable("id") long id, @RequestBody Tasks tasks) {
    Optional<Tasks> tasksData = tasksRepository.findById(id);

    if (tasksData.isPresent()) {
      Tasks _tasks = tasksData.get();
      _tasks.setTitle(tasks.getTitle());
      _tasks.setDescription(tasks.getDescription());
      _tasks.setCompleted(tasks.isCompleted());
      _tasks.setPriorities(tasks.getPriorities());
      return new ResponseEntity<>(tasksRepository.save(_tasks), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<HttpStatus> deleteTasks(@PathVariable("id") long id) {
    try {
      tasksRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @DeleteMapping("/tasks")
  public ResponseEntity<HttpStatus> deleteAllTasks() {
    try {
      tasksRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
  @GetMapping("/tasks/completed")
  public ResponseEntity<List<Tasks>> findByCompleted() {
    try {
      List<Tasks> tasks = tasksRepository.findByCompleted(true);

      if (tasks.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(tasks, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
