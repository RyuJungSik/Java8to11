package section2.lesson5;

public interface Bar extends Foo {
//Bar에서는 Foo가 제공하는 default 메소드를 제공하고 싶지 않다.
//추상 메소드로 선언해주면된다.
//단 아래처럼 구현하면 bar을 구현하는 모든 클래스에 해당 메소드를 구현해햐한다.
void printNameUpperCase();
}
