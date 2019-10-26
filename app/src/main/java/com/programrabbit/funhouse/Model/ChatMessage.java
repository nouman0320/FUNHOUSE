package com.programrabbit.funhouse.Model;


import java.util.Date;

public class ChatMessage {
    User user;
    String message;
    Date createdAt;

    public ChatMessage(User user, String message, Date createdAt) {
        this.user = user;
        this.message = message;
        this.createdAt = createdAt;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
