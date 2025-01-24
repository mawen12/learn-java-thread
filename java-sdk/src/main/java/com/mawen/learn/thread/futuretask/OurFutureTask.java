package com.mawen.learn.thread.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class OurFutureTask<V> extends FutureTask<V> {

    public OurFutureTask(Callable<V> callable) {
        super(callable);
    }

    @Override
    protected void done() {
        try {
            System.out.println("Done task1..." + get());
        } catch (Exception e) {
            System.out.println("Exception Task1..." + exceptionNow());
        }
    }
}
