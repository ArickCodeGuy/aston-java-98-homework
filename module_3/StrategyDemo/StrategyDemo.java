package module_3.StrategyDemo;

public class StrategyDemo {
  public static void main(String[] args) {
    Person p = new Person("Cody");

    p.move();

    p.setMovementStrategy(new UpMovementStrategy());

    p.move();
    p.move();
    p.move();
  }
}

interface MovementStrategy {
  Point move();
}

class DefaultMovementStrategy implements MovementStrategy {
  @Override
  public Point move() {
    System.out.println("DefaultMovementStrategy: no movement");
    return new Point();
  }
}

class UpMovementStrategy implements MovementStrategy {
  @Override
  public Point move() {
    System.out.println("UpMovementStrategy: move up");
    return new Point(0, -1);
  }
}

class Point {
  public int x = 0;
  public int y = 0;

  Point() {
  }

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "{ x: " + x + ", y: " + y + " }";
  }
}

class Person {
  private final String name;
  private Point position = new Point();
  private MovementStrategy movementStrategy = new DefaultMovementStrategy();

  Person(String name) {
    this.name = name;
  }

  public void setMovementStrategy(MovementStrategy movementStrategy) {
    this.movementStrategy = movementStrategy;
  }

  public void move() {
    Point direction = movementStrategy.move();
    this.position.x += direction.x;
    this.position.y += direction.y;

    System.out.println("Person with name: " + name + ". Moved to position: " + position);
  }
}