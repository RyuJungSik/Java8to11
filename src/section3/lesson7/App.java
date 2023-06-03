package section3.lesson7;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
public static void main(String[] args) {
    
    
    List<String> names = new ArrayList<>();
    names.add("Kim");
    names.add("Lee");
    names.add("Kang");
    
    //바로 변경이 되는것이 아니라 또다른 스트림이 되는것이다.
    Stream<String> stringStream = names.stream().map(String::toUpperCase);
    
    names.forEach(System.out::println);
    
    
    //출력이 되지 않는다. 왜냐하면 중개형 오퍼레이션은 터미널 오퍼레이션을 만나기전까지 스트림을 리턴하지 않는다.
    names.stream().map((s) -> {
        System.out.println("s = " + s);
        return s.toUpperCase();
    });
    System.out.println("===================");
    names.forEach(System.out::println);
    System.out.println("===================");
    
    
    //종료형 오퍼레이션을 만나면 처리한다..
    List<String> collect = names.stream().map((s) -> {
        System.out.println("s = " + s);
        return s.toUpperCase();
    }).collect(Collectors.toList());
    
    collect.forEach(System.out::println);
    
    
    //손쉽게 병렬처리를 할 수 있다.
    //만약 스트림 사용안할시
    // 각각의 엘리먼트를 돌면서 하는건데 루프를 병렬적으로 처리하기 어렵다.
    for (String name : names) {
        if (name.startsWith("K")) {
            System.out.println(name.toUpperCase());
        }
    }
    
    //스트림은 jvm이 병렬적으로 처리해준다.
    List<String> collect1 = names.parallelStream().map(String::toLowerCase).collect(Collectors.toList());
    collect1.forEach(System.out::println);
}
}
