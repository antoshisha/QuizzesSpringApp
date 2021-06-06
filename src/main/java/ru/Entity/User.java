package ru.Entity;

public class User {
    int id;
    boolean isAnonymous;

    public User(int id, boolean isAnonymous) {
        this.id = id;
        this.isAnonymous = isAnonymous;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }
}
