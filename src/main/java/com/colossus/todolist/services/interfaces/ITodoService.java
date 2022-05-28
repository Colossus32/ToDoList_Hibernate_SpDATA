package com.colossus.todolist.services.interfaces;

import com.colossus.todolist.domain.Todo;
import com.colossus.todolist.domain.plainObjects.TodoPojo;

import java.util.List;

public interface ITodoService {
    TodoPojo createTodo (Todo todo, long userId);
    TodoPojo getTodo (long todoId, long userId);
    TodoPojo updateTodo(Todo source, Long todoId, Long userId);
    String deleteTodo (long todoId, long userId);
    List<TodoPojo> getAllTodos (long userId);
}
