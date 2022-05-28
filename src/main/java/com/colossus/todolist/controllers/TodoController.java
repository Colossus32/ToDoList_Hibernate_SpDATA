package com.colossus.todolist.controllers;

import com.colossus.todolist.annotations.Authenticational;
import com.colossus.todolist.domain.Todo;
import com.colossus.todolist.domain.plainObjects.TodoPojo;
import com.colossus.todolist.exceptions.CustomEmptyDataException;
import com.colossus.todolist.services.interfaces.ITodoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class TodoController {

    private final ITodoService todoService;
    Long userId;

    public TodoController(ITodoService todoService) {
        this.todoService = todoService;
    }

    @Authenticational
    @PostMapping(path = "/user/{userId}/todo")
    public ResponseEntity<TodoPojo> createTodo(HttpServletRequest request, @RequestBody Todo todo){
        return new ResponseEntity<>(todoService.createTodo(todo, userId), HttpStatus.CREATED);
    }

    @Authenticational
    @GetMapping(path = "/user/{userId}/todo/{todoId}")
    public ResponseEntity<TodoPojo> getTodo (HttpServletRequest request, @PathVariable long todoId){
        return new ResponseEntity<>(todoService.getTodo(todoId, userId), HttpStatus.OK);
    }

    @Authenticational
    @PutMapping(path = "/user/{userId}/todo/{todoId}")
    public ResponseEntity<TodoPojo> updateTodo (HttpServletRequest request, @PathVariable long todoId, @RequestBody Todo source){
        return new ResponseEntity<>(todoService.updateTodo(source, todoId, userId), HttpStatus.OK);
    }

    @Authenticational
    @DeleteMapping(path = "/user/{userId}/todo/{todoId}")
    public ResponseEntity<String> deleteTodo (HttpServletRequest request, @PathVariable long todoId){
        return new ResponseEntity<>(todoService.deleteTodo(todoId, userId), HttpStatus.OK);
    }

    @Authenticational
    @GetMapping(path = "/user/{userId}/todos")
    public ResponseEntity<List<TodoPojo>> getAllTodo (HttpServletRequest request){
        return new ResponseEntity<>(todoService.getAllTodos(userId), HttpStatus.OK);
    }

    /**
     * Exception handling
     */

    @ExceptionHandler
    public ResponseEntity<String> onMissingTodoName (DataIntegrityViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + ": Name of todo is obligatory");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingTodoId(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass())
                + " # "
                + exception.getLocalizedMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingTodo (EmptyResultDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + ": no one todo was found");
    }

    @ExceptionHandler
    public ResponseEntity<String> SQLProblems (SQLException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(exception.getClass())
                + exception.getSQLState()
                + exception.getLocalizedMessage()
                + ": something went wrong with todo");
    }

    @ExceptionHandler
    public ResponseEntity<String> customExceptionHandler (CustomEmptyDataException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass())
                + " "
                + exception.getCause()
                + " "
                + exception.getLocalizedMessage());
    }
}
