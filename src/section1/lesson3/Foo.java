package section1.lesson3;

import java.util.function.*;

public class Foo {
public static void main(String[] args) {
    
    Supplier<Integer> get10 = () -> 10;
    
    BinaryOperator<Integer> sum = (a, b) -> a + b; //타입 제네릭으로 추론이 되기때문에 생략가능
    
    //변수 캡쳐
    Foo foo = new Foo();
    foo.run();
}

private void run() {
    
    //아래 세가지 모두 파이널변수만 참고가능하지만 자바 8부턴 사실상 final도 가능하다
    //이펙티브 파이널이라하며 나중에 변경하는 값이 있으면 각각의 클래스에서 참조하지 못한다.
    int baseNumber = 10;
    
    //로컬클래스
    class LocalClass {
        void printBaseNumber() {
            System.out.println("baseNumber = " + baseNumber);
        }
    }
    
    //익명클래스
    Consumer<Integer> integerConsumer = new Consumer<Integer>() {
        @Override
        public void accept(Integer integer) {
            System.out.println("baseNumber = " + baseNumber);
        }
    };
    
    
    //람다 클래스
    IntConsumer printInt = (i) -> System.out.println(i + baseNumber);
    
    
    //로컬, 익명에는 쉐도잉이 발생하지만 람다는 발생하지 않는다.
    class LocalClass2 {
        void printBaseNumber() {
            int baseNumber = 99;
            System.out.println("baseNumber = " + baseNumber); //99
        }
    }
    
    //익명클래스
    Consumer<Integer> integerConsumer2 = new Consumer<Integer>() {
        @Override
        public void accept(Integer baseNumber) {
            System.out.println("baseNumber = " + baseNumber); //10이 아닌 해당 스코프의 값을 가리킴
        }
    };
    
    IntConsumer printInt2 = (i) -> {
        //해당 스코프에는 baseNumber 변수를 정의할 수 없다.
        System.out.println(i + baseNumber);
    };
}
}
