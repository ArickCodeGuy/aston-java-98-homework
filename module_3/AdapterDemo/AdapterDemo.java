package module_3.AdapterDemo;

public class AdapterDemo {
  public static void main(String[] args) {
    // Создаем круг
    Circle circle = new Circle(10);
    System.out.println("Area of circle: " + circle.getArea());

    // Создаем квадрат из круга
    Square square = new CircleToSquareAdapter().convert(circle);
    System.out.println("Area of square: " + square.getArea());
  }
}

interface Shape {
  double getArea();
}

class Circle implements Shape {
  private double radius;

  Circle(double radius) {
    this.radius = radius;
  }

  public double getRadius() {
    return this.radius;
  }

  @Override
  public double getArea() {
    return Math.PI * this.radius * this.radius;
  }
}

class Square implements Shape {
  private double side;

  Square(double side) {
    this.side = side;
  }

  public double getSide() {
    return this.side;
  }

  @Override
  public double getArea() {
    return this.side * this.side;
  }
}

interface ShapeAdapter<T extends Shape, K extends Shape> {
  K convert(T figure);
}

class CircleToSquareAdapter implements ShapeAdapter<Circle, Square> {
  @Override
  public Square convert(Circle circle) {
    return new Square(circle.getRadius() * 2);
  }
}