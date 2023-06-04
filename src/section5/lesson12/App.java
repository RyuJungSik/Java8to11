package section5.lesson12;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App {
public static void main(String[] args) {
    
    Instant instant = Instant.now();
    System.out.println("instant = " + instant); //기준시 UTC, GMT
    
    
    ZoneId zone = ZoneId.systemDefault();
    System.out.println("zone = " + zone);
    ZonedDateTime zonedDateTime = instant.atZone(zone);
    System.out.println("zonedDateTime = " + zonedDateTime);
    
    LocalDateTime now = LocalDateTime.now();
    System.out.println("now = " + now);
    LocalDateTime birthday = LocalDateTime.of(1995, Month.APRIL, 15, 0, 0, 0);
    System.out.println("birthday = " + birthday);
    
    ZonedDateTime nowInKorea = zonedDateTime.now(ZoneId.of("Asia/Seoul"));
    System.out.println("nowInKorea = " + nowInKorea);
    
    Instant nowInstant = Instant.now();
    ZonedDateTime zonedDateTime1 = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
    System.out.println("zonedDateTime1 = " + zonedDateTime1);
    
    
    //////////////////기간//////////////////
    
    LocalDate today = LocalDate.now();
    LocalDate thisYearBirthday = LocalDate.of(2024, Month.APRIL, 7);
    
    Period period = Period.between(today, thisYearBirthday);
    System.out.println("period = " + period);
    System.out.println("period.get(ChronoUnit.DAYS) = " + period.get(ChronoUnit.DAYS));
    
    
    Period until = today.until(thisYearBirthday);
    System.out.println("until = " + until);
    
    
    Instant now2 = Instant.now();
    Instant plus = now2.plus(10, ChronoUnit.SECONDS);
//    Duration between = Duration.between(now, plus);
//    System.out.println("between = " + between.getSeconds());
    
    
    //포맷팅
    LocalDateTime now3 = LocalDateTime.now();
    DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    System.out.println("now.format(MMddyyyy) = " + now.format(MMddyyyy));
    
    
    LocalDate parse = LocalDate.parse("07/15/1982", MMddyyyy);
    System.out.println("parse = " + parse);
    
    Date date = new Date();
    Instant instant2 = date.toInstant();
    Date newDate = Date.from(instant2);
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    ZonedDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
    GregorianCalendar from = GregorianCalendar.from(dateTime);
    
    //예전api에서 최근 api로 변경가능
    ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
    TimeZone timeZone = TimeZone.getTimeZone(zoneId);
    
    LocalDate now4 = LocalDate.now();
    now.plus(10, ChronoUnit.DAYS);
}
}
