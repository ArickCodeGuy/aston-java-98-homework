package module_4.ThreadsDemo;

// LiveLock copy
public class ThreadsDemo {
  public static void main(String[] args) {
    Executor e1 = new Executor("2");
    Executor e2 = new Executor("1");
    ExecutorManager em = new ExecutorManager();

    Thread t1 = new Thread(() -> e1.execute(em, e2));
    Thread t2 = new Thread(() -> e2.execute(em, e1));

    t1.start();
    t2.start();
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
        System.out.println(name);
        continue;
      }

      done = true;
    }
  }
}