package module_3.ProxyDemo;

public class ProxyDemo {
  public static void main(String[] args) {
    TV tv = new TVRemoteController(new SamsungTV());

    tv.turnOn();
    tv.turnOn();
  }
}

interface TV {
  void turnOn();
}

class SamsungTV implements TV {
  @Override
  public void turnOn() {
    System.out.println("Samsung is now turned on.");
  }
}

class TVRemoteController implements TV {
  private boolean isTvTurnedOn;
  private final TV tv;

  TVRemoteController(TV tv) {
    this.tv = tv;
  }

  @Override
  public void turnOn() {
    if (isTvTurnedOn) {
      System.out.println("TV is already turned on.");
      return;
    }

    System.out.println("Turning on TV.");
    tv.turnOn();
    isTvTurnedOn = true;
  }
}