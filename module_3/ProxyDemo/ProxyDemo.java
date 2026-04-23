package module_3.ProxyDemo;

public class ProxyDemo {
  public static void main(String[] args) {
    Hawk hawk = new Hawk();
    HawkProxy hawkProxy = new HawkProxy(hawk);

    hawkProxy.fly();
  }
}

interface Bird {
  void fly();
}

class Hawk implements Bird {
  @Override
  public void fly() {
    System.out.println("Hawk: fly");
  }
}

class HawkProxy implements Bird {
  private Hawk hawk;

  HawkProxy(Hawk hawk) {
    this.hawk = hawk;
  }

  @Override
  public void fly() {
    System.out.println("Proxy: fly");
    hawk.fly();
  }
}