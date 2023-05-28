package section2.lesson5;

public interface Foo {

void printName();

//나중에 생기는 기능
//void printNameUppserCase();

//default 키워드사용
/*
* @implSpec
*  이 구현체는 getName()으로 가저욘 문자열을 대문자로 바꿔 출력한다.
* */
default void printNameUppserCase(){
    System.out.println(getName().toUpperCase());
};

String getName();

static void printAnything() {
    System.out.println("Foo");
}


}
