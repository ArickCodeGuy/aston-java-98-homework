package module_4.LiveLock;

public class LiveLock {
  public static void main(String[] args) {
    Executor e1 = new Executor("e1");
    Executor e2 = new Executor("e2");
    ExecutorManager em = new ExecutorManager();

    Thread t1 = new Thread(() -> e1.execute(em, e2));
    Thread t2 = new Thread(() -> e2.execute(em, e1));

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
    }

    // Never reached
    System.out.println("Program finished");
  }
}

class ExecutorManager {
  private Executor activeExecutor;

  public synchronized void setActiveExecutor(Executor executor) {
    activeExecutor = executor;
  }

  public synchronized Executor getActiveExecutor() {
    return activeExecutor;
  }
}

class Executor {
  public String name;
  public boolean done = false;

  Executor(String name) {
    this.name = name;
  }

  public void execute(ExecutorManager em, Executor other) {
    em.setActiveExecutor(this);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }

    while (!done) {
      // Wait for other to finish
      if (em.getActiveExecutor().name != name)
        continue;

      // Do something
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }

      // Check if other is finished
      if (other.done == false) {
        // Delegate
        em.setActiveExecutor(other);
        System.out.println("Executor: " + name + ". Other is not finished: " + other.name + ". Delegating...");
        continue;
      }

      done = true;
    }
  }
}