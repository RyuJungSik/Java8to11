package section2.lesson5;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
public static void main(String[] args) {
    Foo foo = new DefaultFoo("kim");
    foo.printName();
    foo.printNameUppserCase();
    
    //static 메소드를 쓸수도 있다.
    Foo.printAnything();
}
}
