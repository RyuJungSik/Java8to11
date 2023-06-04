package section6.lesson13;

public class App {
public static void main(String[] args) throws InterruptedException {
    
    //스레드 만드는 첫번째 방법 오버라이드
//    MyThread myThread = new MyThread();
//    myThread.start();
//    System.out.println("Hello  " + Thread.currentThread().getName());
    
    //Runnable
    Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("Thread = " + Thread.currentThread().getName());
        }
    });
//    thread2.start();
//    System.out.println("Hello  " + Thread.currentThread().getName());
    
    
    //람다로 변경 가능
    //sleep 기능
    Thread thread = new Thread(() -> {
        
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
        System.out.println("Thread = " + Thread.currentThread().getName());
        
        
    });
    thread.start();
    System.out.println("Hello  " + Thread.currentThread().getName());
    Thread.sleep(3000L);
    
    //스레드를 기달리도록 다 기달리고 호출된다. join을 통해서 한다.
    thread.join();
    System.out.println(thread2 + "is finished");
    
    
}

static class MyThread extends Thread {
    
    @Override
    public void run() {
        System.out.println("Thread = " + Thread.currentThread().getName());
        
    }
    
}


}
