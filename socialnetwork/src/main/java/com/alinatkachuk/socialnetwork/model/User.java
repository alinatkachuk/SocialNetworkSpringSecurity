package com.alinatkachuk.socialnetwork.model;

public class User {

    private Long userId;

    private String firstName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User (Long userId, String firstName) {
        this.userId=userId;
        this.firstName=firstName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
