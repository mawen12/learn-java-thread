# learn-java-thread
Learn Java MultiThread

## Futures

### Thread Pools

Platform Thread is an expensive Resource.

### Key Abstraction is a Task

Runnable

```java
@FunctionalInterface
public interface Runnable {

    /**
     * Runs this operation.
     */
    void run();
}
```

Callable

```java
@FunctionalInterface
public interface Callable<V> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     * 
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    V call() throws Exception;
}
```

### Executor Service

Mostly backed by a Thread Pool
Separates Task from its execution Policy

```java
public interface ExecutorService extends Executor, AutoCloseable {
    // only the most important methods are shown here

    // Submit a Runnable or a Callable task
    <T> Future<T> submit(Callable<T> task);
    Future<?> submit(Runnable task);
    
    // Orderly shutdown. All submited Tasks will be executed
    void shutdown();
    
    // Attempts to stop all executing Tasks, halts processing of waiting Tasks
    List<Runnable> shutdownNow();
    
    // Initializes Orderly Shutdown and waits for all tasks to finish
    default void close();
}
```

```java
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public interface Future<V> {

    // Get the Result of the Task Execution. Wait till result is available
    V get() throws InterruptedException, ExecutionException;
    V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
    
    // Get the result immediately. Assumes that Task is Completed.
    default V resultNow();
    default Throwable exceptionNow();

    // Attempts the cancel the execution of the Task
    boolean cancel(boolean mayInterruptIfRunning);
    
    // Computation State: RUNNING, SUCCESS, FAILED, CANCELED
    default Future.State state();
    boolean isCancelled();
}
```

```java
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

public class FutureTask<V> implements RunnableFuture<V> {
    public FutureTask(Callable<V> callable);
    
    // Only the most important method is shown
    
    // Protected method invoked when this task transitions to state(@code isDone}
    // (whether normally or via calcellation).  The default implementation does nothing.
    protected void done() {}
}
```

```java
import java.util.concurrent.Future;

public interface RunnableFuture<V> extends Runnable, Future<V> {
    /**
     * Sets ths future to the result of its computation
     * unless it has been calcelled.
     */
    void run();
}
```

### Limitations

Cannot create an Asynchronous Pipeline
Cannot Complete a Future
Limited Features

### Imperative Style - Pseudo Code(Blocking)

```java
// Pseudo code for handing User Request

// Fetch some data from DB
data1 = FetchDataFromDB(dbUrl);

// Fetch some data from a Microserver 1
data2 = FetchDataFromService1(url1);

// Process all data
combinedData = ProcessAndCombine(data1, data2);

// send data to user
SendData(combinedData);
```

### Reactive Style - Pseudo Code

```java
// Reactive Pseudo code for handling User Request
// The user thread does not block

Pipeline
        .Run(FetchDataFromDB(dbUrl))
        .Run(FetchDataFromService1(url1))
        .Combine(dataResult, serviceResult)
        .SendData(combinedData);

// Method exists before Database and Service operations are completed
```

## Completable Future

