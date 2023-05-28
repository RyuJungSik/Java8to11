package section2.lesson5;



public class DefaultFoo implements Foo {

String name;

public DefaultFoo(String name) {
    this.name = name;
}

//기본적으로 방식
@Override
public void printName() {
    System.out.println(this.name);
}

//인터페이스에 추가되면 컴파일 에러뜸

@Override
public String getName() {
    return this.name;
}

//default 메소드도 Override로 재정의가능하다.
@Override
public void printNameUppserCase(){
    System.out.println(this.name.toUpperCase());
}

}
