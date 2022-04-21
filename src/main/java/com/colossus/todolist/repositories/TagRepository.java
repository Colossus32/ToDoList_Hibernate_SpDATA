package com.colossus.todolist.repositories;

import com.colossus.todolist.domain.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
    List<Tag> findByName(String name);
}
