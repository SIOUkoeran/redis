package queue;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue {
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

    public static void publish(String data) {
        try {
            System.out.println("DATA INSERT WAITING QUEUE");
            queue.put(data);
        } catch (InterruptedException e) {
            throw new RuntimeException("CAN'T PUT COMMAND QUEUE");
        }
    }

    public static boolean isEmpty() {
        return queue.isEmpty();
    }

    public static String get() {
        return queue.poll();
    }
}
