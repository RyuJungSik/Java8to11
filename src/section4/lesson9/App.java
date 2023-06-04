package section4.lesson9;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {
public static void main(String[] args) {
    List<OnlineClass> springClasses = new ArrayList<>();
    springClasses.add(new OnlineClass(1, "spring boot", true));
    springClasses.add(new OnlineClass(2, "spring data jpa", true));
    springClasses.add(new OnlineClass(3, "spring mvc", false));
    springClasses.add(new OnlineClass(4, "spring core", false));
    springClasses.add(new OnlineClass(5, "rest api development", false));
    
    
    OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
    
    //null pointerException 이 뜰거이다.
//    Duration studyDuration = spring_boot.getProgress().getStudyDuration();
//    System.out.println(studyDuration);
    
    //기존 null 처리 if으로 함
    //하지만 단점은 널체크를 깜박할 수 있어서 에러를 발생할 확률이 높음
    //문제 1 널을 리턴하는거 자체가 문제임, 그리고 if로는 깜박할 수 있음
    //널일 시 할수 있는 방법은 에러를 throw로 에러를 던지는것
    Optional<Progress> progress = spring_boot.getProgress();
    System.out.println("progress = " + progress);


}
}
