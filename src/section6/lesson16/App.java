package section6.lesson16;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class App {
public static void main(String[] args) throws InterruptedException, ExecutionException {
    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
        System.out.println("Hello " + Thread.currentThread().getName());
        return "Hello";
    });
    
    CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
        System.out.println("World " + Thread.currentThread().getName());
        return "World";
    });
    
    
    //hello 후 World 을 해야한다.

//    hello.get();
//    world.get();
    
    System.out.println("thenCompose");
    CompletableFuture<String> future = hello.thenCompose(App::getWorld);
    System.out.println(future.get());
    
    
    //따로 실행
    //
    System.out.println("thenCombine");
    CompletableFuture<String> future1 = hello.thenCombine(world, (h, w) -> {
        return h + " " + w;
    });
    System.out.println("future1 = " + future1.get());
    
    
    //2개 이상일
    CompletableFuture<Void> future2 = CompletableFuture.allOf(hello, world).thenAccept(System.out::println);
    System.out.println("future2 = " + future2.get());
    
    
    //콜렉션으로 만들어서 받고싶다
    System.out.println("콜렉션으로 만들어서 받기");
    List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
    CompletableFuture<List<String>> results =
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                    .thenApply(v -> {
                        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
                    });
    results.get().forEach(System.out::println);
    
    
    //아무거나 하나 빨리끝나는거 결과 받아서 호출하기
    System.out.println("/아무거나 하나 빨리끝나는거 결과 받아서 호출하기");
    CompletableFuture<Void> future3 = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
    future3.get();
    
    
    //에러 처리
    System.out.println("예외 처리");
    boolean throwError = true;
    CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
        if (throwError) {
            throw new IllegalArgumentException();
        }
        System.out.println("Hello " + Thread.currentThread().getName());
        return "Hello";
    }).exceptionally(ex -> {
        System.out.println(ex);
        return "Error";
    });
    
    System.out.println(exceptionally.get());
    
    //제너럴한 에러처리
    System.out.println("제너럴한 예외 처리");
    CompletableFuture<String> exceptionally2 = CompletableFuture.supplyAsync(() -> {
        if (throwError) {
            throw new IllegalArgumentException();
        }
        System.out.println("Hello " + Thread.currentThread().getName());
        return "Hello";
    }).handle((result, ex) -> {
        if (ex != null) {
            System.out.println(ex);
            return "ERROR";
        }
        return result;
    });
    
    System.out.println(exceptionally2.get());
}

private static CompletableFuture<String> getWorld(String message) {
    return CompletableFuture.supplyAsync(() -> {
        System.out.println("World" + Thread.currentThread().getName());
        return message + "World";
    });
}

}
