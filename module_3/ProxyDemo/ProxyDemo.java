package module_3.ProxyDemo;

public class ProxyDemo {
  public static void main(String[] args) {
    TV tv = new SamsungRemoteController(new SamsungTV());

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
    System.out.println("Samsung is now turned on");
  }
}

class SamsungRemoteController implements TV {
  private boolean isTvTurnedOn;
  private final SamsungTV samsungTV;

  SamsungRemoteController(SamsungTV samsungTV) {
    this.samsungTV = samsungTV;
  }

  @Override
  public void turnOn() {
    if (isTvTurnedOn) {
      System.out.println("Samsung TV is already turned on");
    } else {
      System.out.println("Turning on TV");
      samsungTV.turnOn();
      isTvTurnedOn = true;
    }
  }
}