package com.colossus.todolist.services;

import com.colossus.todolist.domain.Tag;
import com.colossus.todolist.domain.Todo;
import com.colossus.todolist.domain.User;
import com.colossus.todolist.domain.plainObjects.TodoPojo;
import com.colossus.todolist.services.interfaces.ITagService;
import com.colossus.todolist.services.interfaces.ITodoService;
import com.colossus.todolist.utils.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TodoService implements ITodoService {

    private final Converter converter;
    private final ITagService tagService;

    @PersistenceContext
    EntityManager entityManager;

    public TodoService(Converter converter, ITagService tagService) {
        this.converter = converter;
        this.tagService = tagService;
    }

    @Override
    @Transactional
    public TodoPojo createTodo(Todo todo, long userId) {

        User todoUser = entityManager
                .createQuery("SELECT user FROM User user WHERE user.id = :id", User.class)
                .setParameter("id", userId)
                .getSingleResult();

        todo.setUser(todoUser);

        Set<Tag> tags = new HashSet<>(todo.getTagList());

        todo.getTagList().clear();

        entityManager.persist(todo);

        tags.stream().map(tagService::findOrCreate)
                .collect(Collectors.toSet())
                .forEach(todo::addTag);

        return converter.todoToPojo(todo);
    }

    @Override
    @Transactional
    public TodoPojo getTodo(long todoId) {
        return null;
    }

    @Override
    @Transactional
    public TodoPojo updateTodo(Todo updatedTodo, long todoId) {
        return null;
    }

    @Override
    @Transactional
    public TodoPojo deleteTodo(long todoId) {
        return null;
    }

    @Override
    @Transactional
    public List<TodoPojo> getAllTodos(long userId) {
        return null;
    }
}
