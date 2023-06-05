package section6.lesson15;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App {
public static void main(String[] args) throws InterruptedException, ExecutionException {
    
//    ExecutorService executorService = Executors.newFixedThreadPool(4);
//    Future<String> future = executorService.submit(() -> "hello");
//
//    future.get();
    
    CompletableFuture<String> future = new CompletableFuture<>();
    future.complete("Kim");
    
    System.out.println(future.get());
    
    CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
        System.out.println("Hello  " + Thread.currentThread().getName());
    });
    
    future1.get();
    
    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
        System.out.println("Hello " + Thread.currentThread().getName());
        return "Hello";
    }).thenApply((s)->{
        return s.toUpperCase();
    });
    
    System.out.println(future2.get());
    
    
    CompletableFuture<Void> future3 = CompletableFuture.supplyAsync(() -> {
        System.out.println("Hello3 " + Thread.currentThread().getName());
        return "Hello3";
    }).thenAccept((s)->{
        System.out.println(s.toUpperCase());
    });
    
    
    CompletableFuture<Void> future4 = CompletableFuture.supplyAsync(() -> {
        System.out.println("Hello4 " + Thread.currentThread().getName());
        return "Hello4";
    }).thenRun(()->{
        System.out.println(Thread.currentThread().getName());
    });
    
    
    
    
}
}
