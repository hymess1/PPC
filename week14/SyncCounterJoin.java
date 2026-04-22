public class SyncCounterJoin {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            t.start();
            t.join();
        }

        System.out.println("Final count: " + counter.getCount());
    }
}