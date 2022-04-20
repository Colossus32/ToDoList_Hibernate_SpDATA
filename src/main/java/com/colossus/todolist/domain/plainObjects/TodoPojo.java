package com.colossus.todolist.domain.plainObjects;

import com.colossus.todolist.domain.Priority;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TodoPojo {

    private long id;

    private String name;

    private String comment;

    private Date startDate;

    private Date endDate;

    private boolean important;

    private Priority priority;

    private long userId;

    private Set<TagPojo> tagListIds = new HashSet<>();

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Set<TagPojo> getTagListIds() {
        return tagListIds;
    }

    public void setTagListIds(Set<TagPojo> tagListIds) {
        this.tagListIds = tagListIds;
    }
}
