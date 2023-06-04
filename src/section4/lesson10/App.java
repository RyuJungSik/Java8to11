package section4.lesson10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {
public static void main(String[] args) {
    List<OnlineClass> springClasses = new ArrayList<>();
    springClasses.add(new OnlineClass(1, "spring boot", true));
    springClasses.add(new OnlineClass(5, "rest api development", false));
    
    
    //findfirst는 Optional로 리턴한다.
    Optional<OnlineClass> spring = springClasses.stream()
                                           .filter(oc -> oc.getTitle().startsWith("spring"))
                                           .findFirst();
    
    Optional<OnlineClass> jpa = springClasses.stream()
                                           .filter(oc -> oc.getTitle().startsWith("jpa"))
                                           .findFirst();
    
    //isPresent로 있는지 없는지 검사할 수 있다.
    boolean present = spring.isPresent();
    System.out.println("present = " + present);
    
    
    //없는 경우 false 를 반환한다.
    boolean present2 = jpa.isPresent();
    System.out.println("present2 = " + present2);
    
    //IsEmpty도 가능하다 IsPresent 의 반대
    boolean empty = jpa.isEmpty();
    System.out.println("empty = " + empty);
    
    //get으로 값을 받아올 수 있다.
    OnlineClass onlineClass = spring.get();
    System.out.println("onlineClass.getTitle() = " + onlineClass.getTitle());
    
    //값이 없는데 get을 하면 런타임 이셉션이 발생한다.
//    OnlineClass onlineClass1 = jpa.get();
//    System.out.println("onlineClass1 = " + onlineClass1);
    
    //그러므로 값이 있는지 확인 후 꺼내는 것이 좋다.
    //있을때 무엇을 하는 경우는 isPresent를 사용한다.
    spring.ifPresent(oc -> System.out.println(oc.getTitle()));
    
    //return 값을 참조해서 다른 작업들을 해야해서 onlineClass타입으로 받아야한다.?
    //orElse()를 통해서 있으면 꺼내오고 없으면 해당 명령을 수행해라(매개변수안에 인스턴스를 넘겨주는것이다.)
    OnlineClass onlineClass2 = jpa.orElse(createNewClass());
    System.out.println("onlineClass2.getTitle() = " + onlineClass2.getTitle());
    
    //orElse사용시 값이 있든 없든 해당 매개변수의 객체를 생성해버린다.
    //이를 대체하기 위해서는
    //orElseGet을 사용하면 매개변수에 Supplier을 넣는데
    //있는경우에는 Supplier을 실행하지 않고 없으면 호출한다.
    OnlineClass onlineClass3 = jpa.orElseGet(App::createNewClass);
    System.out.println("onlineClass3.getTitle() = " + onlineClass3.getTitle());
    
    //orElseThrow를 통해서 만들지 않고 없을 시 에러를 던질 수 있다.
    //매개변수 안에 supplier 로 지정된 에러를 던질 수 있다.
//    OnlineClass onlineClass4 = jpa.orElseThrow(() -> {
//        return new IllegalArgumentException();
//    });
    
    //값이 있는 경우 필터링하는 방법
    Optional<OnlineClass> onlineClass5 = spring.filter(oc -> oc.getId() > 10);
    System.out.println("onlineClass5.isEmpty() = " + onlineClass5.isEmpty());
    
    
    //stream 의 결과도 optional로 나온다.
    Optional<Integer> integer = spring.map(oc -> oc.getId());
    System.out.println("integer = " + integer);
    
    //f
    
    
}

private static OnlineClass createNewClass() {
    return new OnlineClass(10, "newClass", false);
}
}
