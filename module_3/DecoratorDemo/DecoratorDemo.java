package module_3.DecoratorDemo;

public class DecoratorDemo {
  public static void main(String[] args) {
    Human human = new Person("Cody");
    human.introduceYourself();

    Human human2 = new NicePerson(new Person("John"));
    human2.introduceYourself();
  }
}

interface Human {
  void introduceYourself();
}

class Person implements Human {
  private final String name;

  Person(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public void introduceYourself() {
    System.out.println("My name is " + name);
  }
}

class NicePerson implements Human {
  private final Human human;

  NicePerson(Human human) {
    this.human = human;
  }

  @Override
  public void introduceYourself() {
    System.out.print("Nice to meet you. ");
    human.introduceYourself();
  }
}