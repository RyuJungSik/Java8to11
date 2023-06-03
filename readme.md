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