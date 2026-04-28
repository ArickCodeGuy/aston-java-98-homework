package module_4;

import java.util.ArrayList;
import java.util.List;

public class LiveLock {
  static List<Integer> lock1 = new ArrayList<>();
  static List<Integer> lock2 = new ArrayList<>();

  public static void main(String[] args) {
    Executor e1 = new Executor("e1");
    Executor e2 = new Executor("e2");

    Thread t1 = new Thread(() -> e1.execute(e2));

    Thread t2 = new Thread(() -> e2.execute(e1));

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

class Executor {
  public String name;
  public boolean flag = true;

  Executor(String name) {
    this.name = name;
  }

  public void execute(Executor other) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }

    while (flag) {
      if (other.flag) {
        System.out.println("Executor " + name + ". Waiting for other executor " + other.name);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        continue;
      }

      flag = false;
    }
  }
}