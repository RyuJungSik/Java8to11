package section1.lesson1;

public class Foo {
public static void main(String[] args) {
    //익명 내부클래스
    RunSomething runSomething = new RunSomething() {
    
        @Override
        public int doIt(int number) {
            return number + 10;
        };
    };
    
    //람다 표현식
    //일급함수는 같은값을 넣으면 같은값이 나와야한다.
    RunSomething runSomething2 = (number) -> {
        return number + 10;
    };
    
    System.out.println(runSomething2.doIt(1));
    System.out.println(runSomething2.doIt(1));
    System.out.println(runSomething2.doIt(1));
    
    //일급함수가 아닌 함수
    RunSomething runSomething3 = new RunSomething() {
        int baseNumber = 10;
        @Override
        public int doIt(int number) {
            baseNumber++;
            return number + baseNumber;
        };
    };
    
    
    
}

}
