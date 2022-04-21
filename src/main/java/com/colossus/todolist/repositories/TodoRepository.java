package com.colossus.todolist.repositories;

import com.colossus.todolist.domain.Todo;
import com.colossus.todolist.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findAllByUser(User user);
}
