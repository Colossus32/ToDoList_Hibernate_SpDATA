package com.colossus.todolist.controllers;

import com.colossus.todolist.domain.Todo;
import com.colossus.todolist.domain.plainObjects.TodoPojo;
import com.colossus.todolist.services.interfaces.ITodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private final ITodoService todoService;

    public TodoController(ITodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(path = "/user/{userId}/todo")
    public ResponseEntity<TodoPojo> createTodo(@PathVariable Long userId, @RequestBody Todo todo){
        return new ResponseEntity<>(todoService.createTodo(todo, userId), HttpStatus.CREATED);
    }

    @GetMapping(path = "/user/{userId}/todo/{todoId}")
    public ResponseEntity<TodoPojo> getTodo (@PathVariable long todoId){
        TodoPojo result = todoService.getTodo(todoId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(path = "/user/{userId}/todo/{todoId}")
    public ResponseEntity<TodoPojo> updateTodo (@PathVariable long todoId, @RequestBody Todo source){
        return new ResponseEntity<>(todoService.updateTodo(source, todoId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{userId}/todo/{todoId}")
    public ResponseEntity<String> deleteTodo (@PathVariable long todoId){
        return new ResponseEntity<>(todoService.deleteTodo(todoId), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}/todos")
    public ResponseEntity<List<TodoPojo>> getAllTodo (@PathVariable long userId){
        return new ResponseEntity<>(todoService.getAllTodos(userId), HttpStatus.OK);
    }
}
