package com.colossus.todolist.domain.plainObjects;

import java.util.HashSet;
import java.util.Set;

public class UserPojo {

    private Long id;

    private String email;

    private String password;

    private Set<TodoPojo> todolist = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<TodoPojo> getTodolist() {
        return todolist;
    }

    public void setTodolist(Set<TodoPojo> todolist) {
        this.todolist = todolist;
    }
}
