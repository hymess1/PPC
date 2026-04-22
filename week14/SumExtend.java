class SumThread extends Thread {
    int result;

    @Override
    public void run() {
        result = 0;
        for (int i = 1; i <= 100; i++) {
            result += i;
        }
    }
}

public class SumExtend {
    public static void main(String[] args) throws InterruptedException {
        SumThread t = new SumThread();
        t.start();
        t.join();
        System.out.println("Sum = " + t.result);
    }
}