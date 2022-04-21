package com.colossus.todolist.controllers;

import com.colossus.todolist.domain.Todo;
import com.colossus.todolist.domain.plainObjects.TodoPojo;
import com.colossus.todolist.services.interfaces.ITodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final ITodoService todoService;

    public TodoController(ITodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(path = "/user/{userId}/todo")
    public ResponseEntity<TodoPojo> createTodo(@PathVariable Long userId, @RequestBody Todo todo){

        TodoPojo result = todoService.createTodo(todo, userId);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
