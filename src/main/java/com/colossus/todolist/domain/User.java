package com.colossus.todolist.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "_User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Todo> todolist = new HashSet<>();

    public Set<Todo> getTodolist(){
        return todolist;
    }

    public void addTodo (Todo todo){
        addTodo(todo,false);
    }

    public void addTodo (Todo todo, boolean otherSideHasBeenSet){
        if (otherSideHasBeenSet) return;
        todo.setUser(this,true);
        this.getTodolist().add(todo);

    }

    public void removeTodo (Todo todo){
        removeTodo(todo,false);
    }

    public void removeTodo (Todo todo, boolean otherSideHasBeenSet){
        if (otherSideHasBeenSet) return;
        todo.setUser(this,true);
        getTodolist().remove(todo);

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                email.equals(user.email) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
