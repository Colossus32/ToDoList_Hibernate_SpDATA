package com.colossus.todolist.utils;

import com.colossus.todolist.domain.Tag;
import com.colossus.todolist.domain.Todo;
import com.colossus.todolist.domain.User;
import com.colossus.todolist.domain.plainObjects.TagPojo;
import com.colossus.todolist.domain.plainObjects.TodoPojo;
import com.colossus.todolist.domain.plainObjects.UserPojo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Converter {

    public UserPojo userToPojo(User source){
        UserPojo result = new UserPojo();

        result.setId(source.getId());
        result.setEmail(source.getEmail());
        result.setPassword(source.getPassword());

        result.setTodolist(source.getTodolist()
                .stream()
                .map(this::todoToPojo)
                .collect(Collectors.toSet()));

        return result;
    }

    public TodoPojo todoToPojo (Todo source){
        TodoPojo result = new TodoPojo();

        result.setId(source.getId());
        result.setName(source.getName());
        result.setComment(source.getComment());
        result.setStartDate(source.getStartDate());
        result.setEndDate(source.getEndDate());
        result.setImportant(source.isImportant());
        result.setPriority(source.getPriority());
        result.setUserId(source.getUser().getId());

        result.setTagList(source.getTagList()
                .stream()
                .map(tag -> tagToPojo(tag))
                .collect(Collectors.toSet()));

        return result;
    }

    public TagPojo tagToPojo (Tag source){
        TagPojo result = new TagPojo();

        result.setId(source.getId());
        result.setName(source.getName());
        result.setTodoListIds(source.getTodoList().stream()
                .map(todo -> todo.getId())
                .collect(Collectors.toSet()));

        return result;
    }
}
