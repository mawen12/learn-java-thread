package com.mawen.learn.thread.future;

import java.util.concurrent.TimeUnit;

public class TaskResult {

    private String name;

    private int secs;

    public TaskResult(String name, int secs) {
        this.name = name;
        this.secs = secs;
    }

    public String getName() {
        return name;
    }

    public int getSecs() {
        return secs;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "name='" + name + '\'' +
                ", secs=" + secs +
                '}';
    }
}
