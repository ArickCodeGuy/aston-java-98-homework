package module_3.StrategyDemo;

public class StrategyDemo {
  public static void main(String[] args) {

  }
}

interface PaymentStrategy {
  void pay();
}

class VisaPaymentStrategy implements PaymentStrategy {
  @Override
  public void pay() {
    System.out.println("Visa payment strategy");
  }
}

class DefaultPaymentStrategy implements PaymentStrategy {
  @Override
  public void pay() {
    System.out.println("Default payment strategy");
  }
}