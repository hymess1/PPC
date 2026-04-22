public class SyncCounterNoJoin {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
        }

        for (Thread t : threads) t.start();

        System.out.println("Final count: " + counter.getCount());
    }
}