package com.mawen.learn.thread.future;

import java.util.concurrent.*;

public class FutureDemo {

    public static void main(String[] args) throws Exception {

        ExecutorService service = Executors.newFixedThreadPool(3);

//        Future<?> future = service.submit(FuturesPlay::doSimpleTask);
//        Future<TaskResult> future1 = service.submit(() -> FuturesPlay.doTask("SimpleTask", 1, false));
//
//        // wait for future to complete
//        future.get();
//
//        // supposed to do some other work
//
//        try {
//            TaskResult taskData = future1.get();
//            System.out.println(taskData);
//        } catch (InterruptedException | ExecutionException e) {
//            System.out.println(e);
//        }

//        Future<TaskResult> task1Future = service.submit(() -> FuturesPlay.doTask("task1", 3, false));
//        Future<TaskResult> task2Future = service.submit(() -> FuturesPlay.doTask("task1", 2, false));
//        Future<TaskResult> task3Future = service.submit(() -> FuturesPlay.doTask("task1", 1, false));
//
//        try {
//            // Handle taskData1. get() will block till task1 completes
//            TaskResult taskResult1 = task1Future.get();
//            System.out.println(taskResult1);
//
//            // Handle taskData2. get() will block till task2 completes
//            TaskResult taskResult2 = task2Future.get();
//            System.out.println(taskResult2);
//
//            // Handle taskData3. get() will block till task3 completes
//            TaskResult taskResult3 = task3Future.get();
//            System.out.println(taskResult3);
//        } catch (InterruptedException | ExecutionException e) {
//            System.out.println(e);
//        }

        ExecutorCompletionService<TaskResult> srv = new ExecutorCompletionService<>(service);

        Callable<TaskResult> task1 = () -> FuturesPlay.doTask("task1", 2, false);
        Callable<TaskResult> task2 = () -> FuturesPlay.doTask("task2", 1, false);

        Future<TaskResult> task1Future = srv.submit(task1);
        Future<TaskResult> task2Future = srv.submit(task2);

        try {
            for (int i = 0; i < 2; i++) {
                // wait till any one of the futures is completed
                Future<TaskResult> future = srv.take();
                if (future == task1Future) {
                    System.out.println(future.get());
                } else if (future == task2Future) {
                    System.out.println(future.get());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }

        service.shutdown();
    }
}
