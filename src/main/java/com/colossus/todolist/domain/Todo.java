package com.colossus.todolist.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TODO")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "IMPORTANT")
    private boolean important;

    @Column(name = "PRIORITY")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "TODO_TAG", joinColumns = @JoinColumn(name = "TODO_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tagList = new HashSet<>();

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        setUser(user,false);
    }

    public void setUser(User user, boolean otherSideHasBeenSet){
        if (otherSideHasBeenSet) return;
        this.user = user;
        user.addTodo(this,true);
    }

    public void removeUser(User user){
        removeUser(user,false);
    }

    public void removeUser(User user, boolean otherSideHasBeenSet){
        if (otherSideHasBeenSet) return;
        this.user = null;
        user.removeTodo(this,true);
    }

    public Set<Tag> getTodolist(){
        return tagList;
    }

    public void addTag (Tag tag){
        addTag(tag,false);
    }

    public void addTag (Tag tag, boolean otherSideHasBeenSet){
        if (otherSideHasBeenSet) return;
        tag.addTodo(this,true);
        this.getTodolist().add(tag);

    }

    public void removeTag (Tag tag){
        removeTag(tag,false);
    }

    public void removeTag (Tag tag, boolean otherSideHasBeenSet){
        if (otherSideHasBeenSet) return;
        tag.removeTodo(this,true);
        getTodolist().remove(tag);

    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", important=" + important +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo)) return false;
        Todo todo = (Todo) o;
        return id == todo.id &&
                important == todo.important &&
                Objects.equals(name, todo.name) &&
                Objects.equals(comment, todo.comment) &&
                Objects.equals(startDate, todo.startDate) &&
                Objects.equals(endDate, todo.endDate) &&
                priority == todo.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, comment, startDate, endDate, important, priority);
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
