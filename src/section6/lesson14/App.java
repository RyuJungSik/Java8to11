package section6.lesson14;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App {
public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    
    Callable<String> hello = () -> {
        Thread.sleep(2000L);
        return "Hello";
    };
    
    Callable<String> java = () -> {
        Thread.sleep(3000L);
        return "Java";
    };
    
    Callable<String> Kim = () -> {
        Thread.sleep(500L);
        return "Kim";
    };
    
    Future<String> helloFuture = executorService.submit(hello);
//    System.out.println("Started");
//    helloFuture.get();
//    System.out.println("Ennd");
//    executorService.shutdown();
    
    System.out.println(helloFuture.isDone());
    System.out.println("Started");
    helloFuture.get();
    System.out.println(helloFuture.isDone());
    System.out.println("End!!");
    
    
    
    System.out.println("Started");
    helloFuture.cancel(false);
    System.out.println(helloFuture.isDone());
    System.out.println("End!!");
    
    List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, Kim));
    for (Future<String> f : futures) {
        System.out.println("f.get() = " + f.get());
    }
    
    
    String s = executorService.invokeAny(Arrays.asList(hello, java, Kim));
    System.out.println("s = " + s);
    
    executorService.shutdown();
    
}
}
