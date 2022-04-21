package com.colossus.todolist.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tagList")
    private Set<Todo> todoList = new HashSet<>();


    public Set<Todo> getTodoList(){
        return todoList;
    }

    public void addTodo (Todo todo){
        addTodo(todo,false);
    }

    public void addTodo (Todo todo, boolean otherSideHasBeenSet){
        if (otherSideHasBeenSet) return;
        todo.addTag(this,true);
        this.getTodoList().add(todo);

    }

    public void removeTodo (Todo todo){
        removeTodo(todo,false);
    }

    public void removeTodo (Todo todo, boolean otherSideHasBeenSet){
        if (otherSideHasBeenSet) return;
        todo.removeTag(this,true);
        this.getTodoList().remove(todo);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return id == tag.id &&
                name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
