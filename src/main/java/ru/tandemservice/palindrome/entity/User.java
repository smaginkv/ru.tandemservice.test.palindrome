package ru.tandemservice.palindrome.entity;

import java.util.Objects;

public class User {

    private static long counter;
    private long id;
    private String name;

    public User(String name) {
        this.name = name;
        this.id = counter++;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
