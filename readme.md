# 더자바,  java8 -백기선 강의

## 1. 함수형 인터페이스와 람다

### 함수형 인터페이스와 람다표현식 소개

- 함수형 인터페이스
  - 추상메서드가 딱 한개만 있는 인터페이스이다.
  - @FunctionalInterface을 정의해준다.

    ```java
    @FunctionalInterface
    public interface RunSomething {
    int doIt(int number);
    
    }
    ```

- 자바8부터 인터페이스에 스태틱메서드, 디폴트메서드가 정의 가능해진다.
- 자바8이전 익명 내부 클래스 사용

```java
RunSomething runSomething = new RunSomething() {
    
        @Override
        public int doIt(int number) {
            return number + 10;
        };
    };
```

- 함수형 인터페이스는 위의 익명 내부 클래스를 람다 표현식으로 사용가능

```java
RunSomething runSomething2 = (number) -> {
        return number + 10;
    };

System.out.println(runSomething2.doIt(1));
```

- **람다표현식 장점**
  - 코드를 줄일 수 있다.
  - 메소드 매개변수, 리턴 타입, 변수로 만들어 사용할 수도 있다.
  - 식에 개발자의 의도가 명확히 드러나 가독성이 높아진다.
  - 함수를 만드는 과정없이 한번에 처리할 수 있어 생산성이 높아진다
- 람다표현식은 함수를 정의한것 같지만 특수한 형태의 오브젝트를 정의한것이다.
- 자바에서 함수형 프로그래밍
  - 함수를 First class object로 사용가능
  - 순수함수 사용해야 한다.
  - 고차함수로 사용한다.
  - 불변성을 사용한다.

### 자바에서 제공하는 함수형 인터페이스

- 자바에서 제공하는 함수형 인터페이스는 보통 java.util.function 패키지에 있다.
- Function<T, R>, BiFunction<T, U, R>,  Consumer<T>, Supplier<T>,  Predicate<T>, UnaryOperator<T>, BinaryOperator<T> 등이 있다.
- Function<T, R>
  - T타입을받아서 R타입을 리턴하는 함수인터페이스
  - R apply(T t)
  - 함수 조합용 메소드, andThen, compose

    ```java
    import java.util.function.Function;
    
    public class Plus10 implements Function<Integer, Integer> {
    
    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
    }
    
    ```

    ```java
    Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1));
    ```

  - 위 처럼 자바에서 제공하는 함수형 인터페이스를 구현해서 사용 가능하고
  - 람다 표현식으로도 바로 적용 가능하다.

    ```java
    Function<Integer, Integer> plus11 = (i) -> i + 11;
    System.out.println(plus11.apply(1));
    ```

  - 함수형 인터페이스에 추상메서드는 한개지만 디폴트 혹은 스택틱 메서드는 여러개 정의 가능하며,
  - Fuction<T,R>에 추가적으로 andThen, compose이 사용가능하다.

    ```java
    Function<Integer, Integer> plus11 = (i) -> i + 11;
    Function<Integer, Integer> multiply2 = (i) -> i * 2;
    
    //고차 함수, compose사용
    Function<Integer, Integer> multiply2AndPlus11 = plus11.compose(multiply2);
    System.out.println(multiply2AndPlus11.apply(2));
        
    //andthen사용
    plus11.andThen(multiply2);
    System.out.println(plus11.andThen(multiply2).apply(2));
    
    ```

- Consumer<T>
  - T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
  - void Accept(T t)

    ```java
    //Consumer 사용
    Consumer<Integer> printT = (i) -> System.out.println(i);
    printT.accept(10);
    ```

- Supplier<T>
  - T 타입의 값을 제공하는 함수 인터페이스
  - T get()

    ```java
    //Supplier 사용
    Supplier<Integer> get10 = () -> 10;
    System.out.println(get10.get());
    ```

- Predicate<T>
  - T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
  - boolean test(T t)

    ```java
    //predicate 사용
    Predicate<String> startWithKim = (s) -> s.startsWith("Kim");
    Predicate<Integer> isEven = (i) -> i % 2 == 0;
    ```

  - 조합용으로 And, Or, Negate가 사용하다
- UnaryOperator<T>,
  - Function<T, T>의 경우로 Function의 특수한 경우

### 람다 표현식

- 인자 타입 생략가능하다. 제네릭을 통해서 컴파일러가 추론을 한다.
- **변수 캡쳐**
  - 로컬 변수 캡쳐로 final이거나 effective final인 경우 참조할 수 있다.

    ```java
    	private void run() {
        
        //아래 세가지 모두 파이널변수만 참고가능하지만 자바 8부턴 사실상 final도 가능하다
        //이펙티브 파이널이라하며 나중에 변경하는 값이 있으면 각각의 클래스에서 참조하지 못한다.
        final int baseNumber = 10;
        
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
    }
    ```

  - effcecive final은 Java 8 이상부터 final 키워드를 사용하지 않은 변수를 사실상 final로 취급하고 익명클래스와 구현체 또는 람다에서 참조할 수 있다. 단 해당 변수는 변경을 하지않아야 effective final을 유지가능하다.
- 쉐도잉
  - 쉐도잉은 동일한 변수 명일시 해당 스코프의 변수를 참조하는것을 의미한다.
  - 로컬클래스와 익명클래스는 새로운 스코프를 만들들어서 쉐도윙을 하지만
  - 람다는 쉐도잉을 허용하지 않는다.

### 메소드 레퍼런스

- 메소드 레퍼런스는 람다 표현식보다 좀더 간결하게 사용할 수 있게해준다.
- **스태틱 메소드를 참조하는 경우**

    ```java
    UnaryOperator<String> hi = (s) -> "hi" + s;
    ```

  - 위의 코드의 람다표현식을

    ```java
    //    메소드 레퍼런스 사용 스태틱 메소드
    UnaryOperator<String> hi2 = Greeting::hi;
    ```

  - Greeting의 스태틱메소드를 참조한 메소드 레퍼런스로 사용하여 표현가능하다.
- **특정 객체의 인스턴스 메소드를 참조하는 경우에는**

    ```java
    //    메소드 레퍼런스 사용 인스턴스의 메소드 사용
    Greeting greeting = new Greeting();
    UnaryOperator<String> hello = greeting::hello;
    ```

  - 위의 코드처럼 객체생성 후 메소드 레퍼런스를 사용가능하다.
- **생성자도 참조가능하다.**
  - 인자받지 않는 경우, 혹은 인자 받는 경우

    ```java
    //생성자도 람다 그리고 메소드 레퍼런스 사용가능
    Supplier<Greeting> newGreeting = Greeting::new;
    Greeting greeting1 = newGreeting.get();
    
    //인자 받는 생성자의 경우
    //입력값을 받는 생성자
    Function<String, Greeting> kimGreeting = Greeting::new;
    Greeting kim = kimGreeting.apply("chulsu");
    ```

- **임의 객체의 인스턴스 메소드 참조**

    ```java
    Arrays.sort(names, String::compareToIgnoreCase);
    ```


## 2. 인터페이스의 변화

## 인터페이스 기본 메소드와 스태틱 메소드

- **디폴트 메소드**

    ```java
    public interface Foo {
    default void printNameUppserCase(){
     System.out.println(getName().toUpperCase());
    };
    }
    ```

  - 인터페이스에 메소드 추가 시 해당 인터페이스를 구현받는 모든 클래스는 메소드를 오버라이드 해야한다.
  - 디폴트 메소드추가 시 구현한 클래스 변경없이 새 기능을 추가할 수 있다.
  - 디폴트 메소드 추가 시 @implSpec를 통해 문서화 필요하다.
  - 인터페이스를 상속받는 인터페이스에서 다시 추상메소드로 변경할 수 있다.
  - 인터페이스의 디폴트 메소드를 구현받는 클래스에서 오버라이드로 재정의도 가능하다.
- **스태틱 메소드**
  - 해당 타입 관련 헬퍼 또는 유틸리티 메소드를 제공할때 인터페이스에 스태틱 메소드를 제공할 수 있다.

    ```java
    public interface Foo {
    static void printAnything() {
     System.out.println("Foo");
    }
    }
    ```


### 자바8 API의 기본 메소드와 스태틱 메소드

- **자바8의 API의 주요 디폴트 메소드**
  - literable 인터페이스의 디폴트 메소드
    - forEach() → 각각의 엘리먼트들을 차례로 순회가능하다.
    - spliterator() → 리스트를 나눠서 탐색하는 디폴트 메서드이다.
  - Colloection 인터페이스의 디폴트 메소드
    - stream() → 엘리먼트들을 스트림으로 변환해서 함수적으로 처리가능하다

      ```java
      long k = name.stream().map(String::toUpperCase).filter(s -> s.startsWith("K")).count();
      ```

    - removeIf(Predicate) → 조건에 맞는 값을 제거한다.
  - Comparator 인터페이스의 디폴트 메소드
    - sort(Comparator)에서 자주 사용됨
      - reversed(), thenComparing()

        ```java
        name.sort(compareToIgnoreCase.reversed());
        ```

  - Comparator 인터페이스의 스태틱 메소드
    - static reverseOrder()
    - static naturalOrder()
    - static nullFirst()
    - static nullLast()
    - static comparing()
- **인터페이스 디폴트 메소드와 스태틱메소드 제공의 장점**
  - 기존 → 인터페이스에 추상 클래스를 구현받고, 그 클래스를 상속받는 클래스를 만드는 구조
  - 현재 → 구현한 추상클래스 없이 바로 인터페이스에 디폴트 메소드 만들고 바로 그 인터페이스를 구현하는 클래스 만듬
  - 기존은 상속을 받으면 다른 클래스에 상속을 중복으로 받지 못한다.
  - 현재는 인터페이스의 구현을 받고 상속도 자유롭다.

## 3. Stream

### Stream 소개

- **Stream이란?**
  - 연속된 데이터를 처리하는 오퍼레이션의 모음이다.
  - 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
  - 무제한도 가능한다.
  - 손쉽게 병렬 처리 가능하다.
- **Stream은 데이터 소스를 변경하지 않는다.**

    ```java
    Stream<String> stringStream = names.stream().map(String::toUpperCase);
        
    names.forEach(System.out::println);
    ```

  - names의 데이터가 변경되지는 않는다.
- **중개 오퍼레이션은 근본적으로 lazy하다.**

    ```java
    names.stream().map((s) -> {
            System.out.println("s = " + s);
            return s.toUpperCase();
        });
    ```

  - 위의 코드는 종료 오퍼레이션을 만나지 않아서 println은 실행되지 않는다.
- **스트림 파이프라인이란?**
  - 0개 또는 여러개의 중개 오퍼레이션과 종료 오퍼레이션으로 구성된다.

    ```java
    List<String> collect = names.stream().map((s) -> {
            System.out.println("s = " + s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
    ```

  - 위 코드는 collection 이라는 종료 오퍼레이션을 만나서 println이 실행된다.
- **Stream은 손쉽게 병렬 처리 가능하다.**

    ```java
    List<String> collect1 = names.parallelStream().map(String::toLowerCase).collect(Collectors.toList());
    collect1.forEach(System.out::println);
    ```

  - 스레드를 만드는데 비용이 들어서 데이터가 적을 시 병렬처리가 늦어질 수 있다.
- **중개 오퍼레이션**
  - Stream 을 리턴한다.
  - filter, map, limit, skip, sorted
- **종료 오퍼레이션**
  - Stream을 리턴하지 않는다.
  - collection, allMatch, count, forEach, min, max

### Stream API

- **필터 기능**
  - filter(Predicate)

    ```java
    springClasses.stream()
    .filter(oc -> oc.getTitle().startsWith("spring"))
    .forEach(oc -> System.out.println(oc.getId()));
    ```

- **변경 기능**
  - Map(Function), flatMap(Function)

    ```java
    springClasses.stream()
    .map(OnlineClass::getTitle)
    .forEach(System.out::println);
    ```

- **생성 기능**
  - generate(Supplier), Iterate(T seed, UnaryOperator)

    ```java
    System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
    Stream.iterate(10, i -> i + 1)
    .skip(10)
    .limit(10)
    .forEach(System.out::println);
    ```

- **제한 기능**
  - limit(long), skip(long)
- **스트림에 있는 데이터가 특정 조건을 만족하는지 확인**
  - anyMatch(), allMatch(), nonMatch()

    ```java
    boolean test = 
    javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
    ```

- 개수 세기 → count()
- 데이터 하나로 합치기 → reduce(), collection(), sum(), max()

## 4. Optional

### Optional 소개

- **기존 null을 처리하는 방법**
  - if 문으로 일일히 null을 체크해줬다.
  - 하지만 체크를 잊을 시 NullPointerException을 발생시킨다.
- **메소드 작업중 상황에 따라 값을 제대로 리턴할 수 없는 경우 가능한 방법**
  - 예외를 던진다.
  - null을 리턴한다 → 사용시 유의해야 한다.
  - Optional을 리턴한다.
- **Optional이란?**
  - 오직 값 한개가 들어있을 수도 없을 수도 있는 컨테이너.
  - null 처리에 유리하다.
- **Optional 사용법**
  - 리턴값으로만 사용해야한다.
  - 프리미티브 타입용 Optional이 있다. OptionalInt, OptionalLong
  - Collection, Map, Stream Array, Optional은 Opiontal로 감싸지 말아야한다.

### Optional API

- **Optional은 Optional.of(),  Optional.ofNullable(), Optional.empty()로 생성가능하다.**

    ```java
    public Optional<Progress> getProgress() {
        
        //ofNullable은 널일수 있다.
        //Of 면 null일 수 없다. 널일 시 예외 발생시킴
        return Optional.ofNullable(progress);
    }
    ```

- **Stream()의 API인 findFirst() 등을 통해서 Optional을 리턴 받을 수 있다.**

    ```java
    Optional<OnlineClass> spring = 
    springClasses.stream()
    .filter(oc -> oc.getTitle().startsWith("spring"))
    .findFirst();
    ```

- **Optional에 값이 있는지 없는지 확인기능**
  - isPresent(), isEmpty()

    ```java
    //isPresent로 있는지 없는지 검사할 수 있다.
    boolean present = spring.isPresent();
    System.out.println("present = " + present);
    
    //없는 경우 false 를 반환한다.
    boolean present2 = jpa.isPresent();
    System.out.println("present2 = " + present2);
    
    //IsEmpty도 가능하다 IsPresent 의 반대
    boolean empty = jpa.isEmpty();
    System.out.println("empty = " + empty);
    ```

- **Optional에 있는 값 가져오기**
  - get() → 값을 가져오고 nul이면 예외를 발생시킨다.
  - orElse(T) → 값이 있으면 값을 가져오고 없으면 매개변수 값을 리턴한다.
  - orElseGet(Supplier) → 값이 있으면 가져오고 없으면 ~~를 하라 이다.
  - orElseThrow → 값이 있으면 가져오고 없는 경우 에러를 던진다.
- **ifPresent() → 값이 있는경우 해당 값으로 ~~ 를 하라**

    ```java
    spring.ifPresent(oc -> System.out.println(oc.getTitle()));
    ```

- Optional.filter(Predicate) → 안에 들어있는 값을 걸러내서 Optional을 리턴한다.

## 6. Date와 Time API

### Date와 Time API 소개

- **자바8 이전의 날짜 시간 API는 다소 불편한게 많았다.**
  - java.util.Date클래스는 mutable하기 때문에 thread safe하지 않다.
  - Date클래스인데 시간까지 다룬다.
  - 버그 발생 여지가 많다.  → 타입 안정성, 월 시작 숫자
- 자바8 이후 새롭게 Date-Time API를 제공한다.
- **자바8 Date-Time의 특징은**
  - 기계 시간과 인간 시간을 나눈다.
  - 기계 시간은 1970년부터 현재까지의 타임스탬프를 표현한다.
  - 인간 시간은 년원일시분초로 표현한다.
  - 타임스탬프는 Instant를 사용한다.
  - 특정날짜 - LocalDatem, 특정 시간 - LocalTime, 특정일시 - LocalDateTime를 사용한다.
  - 기간 표현시 Duration- 시간 기반, Period - 날짜 기반을 사용한다.
  - DateTimeFormatter를 사용해서 일시를 특정한 문자열로 포매팅할 수 있다.

### Date와 Time API

- 현재를 기계 시간으로 표현하는법
  - java.time.Instant의 API를 사용한다. Instant.now() → 기준시를 나타낸다.
  - Instant.atZone()을 통해 기준 지역을 설정가능하다.

    ```java
    ZoneId zone = ZoneId.systemDefault();
    ZonedDateTime zonedDateTime = instant.atZone(zone);
    System.out.println("zonedDateTime = " + zonedDateTime);
    ```

- 현재를 인간 시간으로 표현하는법
  - LocalDateTime.now() → 현재 지역에 해당하는 일시를 리턴한다.
  - ZoneDateTime.now(특정 존) → 특정 존의 현재시간을 알 수 있다.
- 기간을 표현하는 방법 →
  - Period (사람 시간용)→ Period.between(today, 특정날) 을통해서 기간을 알  수 있다.
  - Duration(기  간시용간)  → Duration.between(now, plus)을 통해서 기간을 알 수 있다.
- 포매팅 기능
  - LocalDateTime 의 포맷팅 → DateTimeFomatter으로 포맷을 지정하고 format 함수로 설정할 수 있다.

    ```java
    LocalDateTime now3 = LocalDateTime.now();
    DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    System.out.println("now.format(MMddyyyy) = " + now.format(MMddyyyy));
    ```

- 파싱 기능
  - LocalDate.parse(날자, 포맷)으로 원하는 포맷으로 파싱이 가능하다.

    ```java
    LocalDate parse = LocalDate.parse("07/15/1982", MMddyyyy);
    System.out.println("parse = " + parse);
    ```

- 레거시 API지원
  - date.toInstant()로 레거시 Date타입을 Instant 타입으로 변경할 수 있다.
  - Date.from(instant)로 Instant타입을 Date로 변경가능하다.
  - GregorianCalendar.toInstant().atZone()로 ZonedDateTime으로 변겨이 가능하고
  - GregorianCalendar.toInstant().atZone().toLocalDateTime()로 LocalDateTime 타입으로 변경이 가능하다.
  - GregorianCalendar.from(ZoneDateTime)으로 ZoneDateTime에서 레거시 GregorianCalendar타입으로변경 가능하다.