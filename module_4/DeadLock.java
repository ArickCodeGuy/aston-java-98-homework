package module_4;

import java.util.ArrayList;
import java.util.List;

public class DeadLock {
  static List<Integer> lock1 = new ArrayList<>();
  static List<Integer> lock2 = new ArrayList<>();

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
      synchronized (lock1) {
        System.out.println("Thread 1 locks lock1");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        synchronized (lock2) {
          System.out.println("Thread 1 locks lock2");
        }
      }
    });

    Thread t2 = new Thread(() -> {
      synchronized (lock2) {
        System.out.println("Thread 2 locks lock2");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        synchronized (lock1) {
          System.out.println("Thread 2 locks lock1");
        }
      }
    });

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
    }

    // Никогда не выполнится
    System.out.println("Program finished");
  }
}
