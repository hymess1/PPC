import java.util.Random;

public class SumOfSquares {
    static long[] array;
    static long firstHalfSum = 0;
    static long secondHalfSum = 0;

    public static void main(String[] args) throws InterruptedException {
        int n = Integer.parseInt(args[0]);
        array = new long[n];

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(100) + 1;
        }

        // Non-threaded
        long startTime = System.nanoTime();
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i] * array[i];
        }
        long endTime = System.nanoTime();
        long nonThreadedTime = endTime - startTime;
        System.out.println("Non-threaded sum: " + sum);
        System.out.println("Non-threaded time: " + nonThreadedTime + " ns");

        // Threaded
        Thread firstHalf = new Thread(() -> {
            for (int i = 0; i < array.length / 2; i++) {
                firstHalfSum += array[i] * array[i];
            }
        });

        Thread secondHalf = new Thread(() -> {
            for (int i = array.length / 2; i < array.length; i++) {
                secondHalfSum += array[i] * array[i];
            }
        });

        long threadedStart = System.nanoTime();
        firstHalf.start();
        secondHalf.start();
        firstHalf.join();
        secondHalf.join();
        long threadedEnd = System.nanoTime();
        long threadedTime = threadedEnd - threadedStart;

        System.out.println("Threaded sum: " + (firstHalfSum + secondHalfSum));
        System.out.println("Threaded time: " + threadedTime + " ns");
    }
}