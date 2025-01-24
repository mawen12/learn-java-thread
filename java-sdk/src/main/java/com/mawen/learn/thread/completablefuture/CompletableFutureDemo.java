package com.mawen.learn.thread.completablefuture;

import com.mawen.learn.thread.future.FuturesPlay;
import com.mawen.learn.thread.future.TaskResult;

import java.sql.Time;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CompletableFutureDemo {

    public static void main(String[] args) throws InterruptedException {
        // Tasks to execute in parallel
        Supplier<TaskResult> task1 = () -> FuturesPlay.doTask("task1", 3, false);
        Supplier<TaskResult> task2 = () -> FuturesPlay.doTask("task2", 5, false);

        // supplyAsync will start the task in a separate thread
        // thenCombine will combine the results of Task1 and Task2
        // thenApply will operate on this combined Result and generate new Result
        // thenAccept will handle the fine Result
        CompletableFuture.supplyAsync(task1)
                .thenCombine(CompletableFuture.supplyAsync(task2), (result1, result2) -> String.format("Combined (%s : %s", result1.getName(), result2.getName()))
                .thenApply(data -> data + " :: Handled Apply")
                .thenAccept(data -> System.out.println(data + " :: Handled Accept"));

        TimeUnit.SECONDS.sleep(10);
    }
}
