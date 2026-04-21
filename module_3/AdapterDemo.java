package module_3;

// Адаптер демо
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

interface Figure2D {
  double getArea();
}

class Circle implements Figure2D {
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

class Square implements Figure2D {
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

interface Figure2DAdapter<T extends Figure2D, K extends Figure2D> {
  K convert(T figure);
}

class CircleToSquareAdapter implements Figure2DAdapter<Circle, Square> {
  @Override
  public Square convert(Circle circle) {
    return new Square(circle.getRadius() * 2);
  }
}