package com.colossus.todolist.services;

import com.colossus.todolist.domain.Tag;
import com.colossus.todolist.domain.Todo;
import com.colossus.todolist.domain.User;
import com.colossus.todolist.domain.plainObjects.TodoPojo;
import com.colossus.todolist.repositories.TodoRepository;
import com.colossus.todolist.repositories.UserRepository;
import com.colossus.todolist.services.interfaces.ITagService;
import com.colossus.todolist.services.interfaces.ITodoService;
import com.colossus.todolist.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TodoService implements ITodoService {

    private final Converter converter;
    private final ITagService tagService;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoService(Converter converter, ITagService tagService, TodoRepository todoRepository, UserRepository userRepository) {
        this.converter = converter;
        this.tagService = tagService;
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public TodoPojo createTodo(Todo todo, long userId) {

        Optional<User> todoUserOptional = userRepository.findById(userId);

        if (todoUserOptional.isPresent()){

            todo.setUser(todoUserOptional.get());

            Set<Tag> tags = new HashSet<>(todo.getTagList());

            todo.getTagList().clear();

            todoRepository.save(todo);

            tags.stream().map(tagService::findOrCreate)
                    .collect(Collectors.toSet())
                    .forEach(todo::addTag);

            return converter.todoToPojo(todo);

        } else {

            return converter.todoToPojo(new Todo());
        }
    }

    @Override
    @Transactional
    public TodoPojo getTodo(long todoId) {

        Optional<Todo> todoOptional = todoRepository.findById(todoId);

        if (todoOptional.isPresent()) {

            return converter.todoToPojo(todoOptional.get());
        } else {

            return converter.todoToPojo(new Todo());
        }
    }

    @Override
    @Transactional
    public TodoPojo updateTodo(Todo source, Long todoId) {

        Optional<Todo> targetOptional = todoRepository.findById(todoId);

        if (targetOptional.isPresent()) {
            Todo target = targetOptional.get();

            target.setName(source.getName());
            target.setComment(source.getComment());
            target.setStartDate(source.getStartDate());
            target.setEndDate(source.getEndDate());
            target.setImportant(source.isImportant());
            target.setPriority(source.getPriority());

            todoRepository.save(target);

            return converter.todoToPojo(target);
        } else {
            return converter.todoToPojo(new Todo());
        }
    }

    @Override
    @Transactional
    public String deleteTodo(long todoId) {

        Optional<Todo> todoForDeleteOptional = todoRepository.findById(todoId);

        if (todoForDeleteOptional.isPresent()) {
            todoRepository.delete(todoForDeleteOptional.get());
            todoForDeleteOptional.get().getTagList().stream()
                    .collect(Collectors.toList())
                    .forEach(tag -> tag.removeTodo(todoForDeleteOptional.get()));
            return "Todo with id = " + todoId + " from User: "+ todoForDeleteOptional.get().getUser().getId() + " was successfully removed";
        } else {

            return "Todo with id = " + todoId + " doesn't exist";
        }
    }

    @Override
    @Transactional
    public List<TodoPojo> getAllTodos(long userId) {

        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.map(user -> todoRepository.findAllByUser(user).stream()
                .map(converter::todoToPojo)
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }
}
