package section5.lesson11;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {
public static void main(String[] args) throws InterruptedException {
    Date date = new Date();
    Calendar calendar = new GregorianCalendar();
    SimpleDateFormat dateFormat = new SimpleDateFormat();
    
    //
    long time = date.getTime();
    System.out.println("date = " + date);
    System.out.println("time = " + time); //1970 년 기준으로 밀리세컨을 반환해준다.
    
    Thread.sleep(1000 * 3);
    
    //3초가 지난 날짜가 들어간다.
    Date after3Seconds = new Date();
    System.out.println("after3Seconds = " + after3Seconds);
    
    //3초 이전의 값을 다시 세팅해준다.
    //mutable 하다 즉 thread환경에 안전하지 않다.
    after3Seconds.setTime(time);
    System.out.println("after3Seconds = " + after3Seconds);
    
    //멀티 스레드 환경에 안전하지 않다는것은??
    Calendar kimBirthday = new GregorianCalendar(1999, Calendar.APRIL, 15); //type safe하지 않다. int에 뭐가 들어올지 모르니
    System.out.println("kimBirthday.getTime() = " + kimBirthday.getTime());
    
    
}
}
