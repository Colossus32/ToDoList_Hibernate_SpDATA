package com.colossus.todolist.services.interfaces;

import com.colossus.todolist.domain.Todo;
import com.colossus.todolist.domain.plainObjects.TodoPojo;

import java.util.List;

public interface ITodoService {
    TodoPojo createTodo (Todo todo, long userId);
    TodoPojo getTodo (long todoId);
    TodoPojo updateTodo (Todo updatedTodo, long todoId);
    TodoPojo deleteTodo (long todoId);
    List<TodoPojo> getAllTodos (long userId);
}
