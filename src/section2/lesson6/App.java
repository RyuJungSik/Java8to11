package section2.lesson6;

import java.util.*;
import java.util.stream.Collectors;

public class App {
public static void main(String[] args) {
    List<String> name = new ArrayList<>();
    
    name.add("Kim");
    name.add("Lee");
    name.add("Kang");
    name.add("Ryu");
    
    //foreach lambda
//    name.forEach((s)->{
//        System.out.println(s);
//    });
    
    //foreach method reference
//    name.forEach(System.out::println);

    //spliterator 평행하게 사용할때 유용하게 쓰인다.
    Spliterator<String> spliterator = name.spliterator();
    Spliterator<String> spliterator1 = spliterator.trySplit();
    while (spliterator.tryAdvance(System.out::println)) ;
    System.out.println("=====================");
    while (spliterator1.tryAdvance(System.out::println)) ;


    //stream default 메소드로 제공된다.
    long k = name.stream().map(String::toUpperCase).filter(s -> s.startsWith("K")).count();
    System.out.println("k = " + k);
    Set<String> k1 = name.stream().map(String::toUpperCase).filter(s -> s.startsWith("K")).collect(Collectors.toSet());
    System.out.println("k1 = " + k1);
    
    //removeif
    name.removeIf(s -> s.startsWith("K"));
    name.forEach(System.out::println);
    
    //compareotor functional interface다
    Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
    name.sort(compareToIgnoreCase.reversed());
    
    
    
}
}
