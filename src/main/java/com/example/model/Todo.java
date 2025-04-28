package com.example.model;

public class Todo {
    private int id;
    private String task;
    private boolean isDone;

    public Todo() {}

    public Todo(int id, String task, boolean isDone) {
        this.id = id;
        this.task = task;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public boolean isDone() {
        return isDone;
    }
}
