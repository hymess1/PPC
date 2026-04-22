class SumTask implements Runnable {
    int result;

    @Override
    public void run() {
        result = 0;
        for (int i = 1; i <= 100; i++) {
            result += i;
        }
    }
}

public class SumRunnable {
    public static void main(String[] args) throws InterruptedException {
        SumTask task = new SumTask();
        Thread t = new Thread(task);
        t.start();
        t.join();
        System.out.println("Sum = " + task.result);
    }
}