package section1.lesson4;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
public static void main(String[] args) {
    
    //기존 람다
    Function<Integer, String> intToString = (i) -> "number";
    
    
    UnaryOperator<String> hi = (s) -> "hi" + s;
    
    //    메소드 레퍼런스 사용 스태틱 메소드
    UnaryOperator<String> hi2 = Greeting::hi;
    
    
    //    메소드 레퍼런스 사용 인스턴스의 메소드 사용
    Greeting greeting = new Greeting();
    UnaryOperator<String> hello = greeting::hello;
    System.out.println(hello.apply("aaa"));
    
    
    //생성자도 람다 그리고 메소드 레퍼런스 사용가능
    Supplier<Greeting> newGreeting = Greeting::new;
    Greeting greeting1 = newGreeting.get();
    
        //입력값을 받는 생성자
        Function<String, Greeting> kimGreeting = Greeting::new;
        Greeting kim = kimGreeting.apply("chulsu");
    System.out.println(kim.getName());
    
    //임의이 갯수 인스턴스 참조
    String[] names = {"chulsi", "whiteship", "toby"};
    Arrays.sort(names, String::compareToIgnoreCase);
    System.out.println(Arrays.toString(names));
    
}
}
