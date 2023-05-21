package section1.lesson2;


import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Foo {
public static void main(String[] args) {
    //클래스 버전
    Plus10 plus10 = new Plus10();
    System.out.println(plus10.apply(1));
    
    //클래스 구현없이 람다 사용(자바에서 제공하는 함수형 인터페이스)
    Function<Integer, Integer> plus11 = (i) -> i + 11;
    System.out.println(plus11.apply(1));
    
    
    //함수 조합
    Function<Integer, Integer> multiply2 = (i) -> i * 2;
    
    
    //고차 함수, compose사용
    Function<Integer, Integer> multiply2AndPlus11 = plus11.compose(multiply2);
    System.out.println(multiply2AndPlus11.apply(2));
    
    //andthen사용
    plus11.andThen(multiply2);
    System.out.println(plus11.andThen(multiply2).apply(2));
    
    //Consumer 사용
    Consumer<Integer> printT = (i) -> System.out.println(i);
    printT.accept(10);
    
    //Supplier 사용
    Supplier<Integer> get10 = () -> 10;
    System.out.println(get10.get());
    
    //predicate 사용
    Predicate<String> startWithKim = (s) -> s.startsWith("Kim");
    Predicate<Integer> isEven = (i) -> i % 2 == 0;
    
    //Function함수 인터페이스의 UnaryOperator
    //입력값의 타입과 결과값의 타입이 같은경우 사용가능
    Function<Integer, Integer> plus12 = (i) -> i + 11;
    
    
}

}
