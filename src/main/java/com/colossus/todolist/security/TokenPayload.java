package com.colossus.todolist.security;

import java.util.Date;

public class TokenPayload {

    private long userId;
    private String email;
    private Date timeOfCreation;

    public TokenPayload(long userId, String email, Date timeOfCreation) {
        this.userId = userId;
        this.email = email;
        this.timeOfCreation = timeOfCreation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(Date timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }
}
