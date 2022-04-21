package com.colossus.todolist.services.interfaces;

import com.colossus.todolist.domain.Tag;

public interface ITagService {
    Tag findOrCreate (Tag tag);
}
