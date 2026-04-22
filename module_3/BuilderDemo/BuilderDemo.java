package module_3.BuilderDemo;

/**
 * Предположим мы имеем что-то вроде paint.
 * У нас есть выбор фигур и выбрав фигуру мы можем за 2 клика эту фигуру создать
 * 
 * Имплементация нас не должна интересовать. Я - пользователь - должен сделать 2
 * клика и получить фигуру
 */
public class BuilderDemo {
  public static void main(String[] args) {
    Circle circle = new CircleBuilder()
        .firstClick(new Point(0, 0))
        .secondClick(new Point(1, 1))
        .getShape();
    circle.draw();

    Rectangle rectangle = new RectangleBuilder()
        .firstClick(new Point(5, 5))
        .secondClick(new Point(10, 10))
        .getShape();
    rectangle.draw();
  }
}

class Point {
  private double x;
  private double y;

  Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public String toString() {
    return "{ x: " + x + ", y: " + y + " }";
  }
}

interface ShapeBuilder {
  ShapeBuilder firstClick(Point point);

  ShapeBuilder secondClick(Point point);

  Shape getShape();
}

interface Shape {
  void draw();
}

class Circle implements Shape {
  private Point center;
  private double radius;

  Circle() {
  }

  @Override
  public void draw() {
    System.out.println("Drawing circle with center: " + center + ". And radius: " + radius);
  }

  public void setCenter(Point center) {
    this.center = center;
  }

  public Point getCenter() {
    return center;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public double getRadius() {
    return radius;
  }
}

class CircleBuilder implements ShapeBuilder {
  private Circle circle = new Circle();

  CircleBuilder() {
  }

  @Override
  public CircleBuilder firstClick(Point position) {
    circle.setCenter(position);
    return this;
  }

  @Override
  public CircleBuilder secondClick(Point point) {
    double x1 = circle.getCenter().getX(), y1 = circle.getCenter().getY();
    double x2 = point.getX(), y2 = point.getY();
    double a = Math.abs(x2 - x1), b = Math.abs(y2 - y1);
    double c = Math.sqrt(a * a + b * b);

    circle.setRadius(c);
    return this;
  }

  @Override
  public Circle getShape() {
    return circle;
  }
}

class Rectangle implements Shape {
  private Point position1;
  private Point position2;

  Rectangle() {
  }

  @Override
  public void draw() {
    System.out.println("Drawing rectangle with position1: " + position1 + ". And position2: " + position2);
  }

  public void setPosition1(Point position1) {
    this.position1 = position1;
  }

  public void setPosition2(Point position2) {
    this.position2 = position2;
  }

  public Point getPosition1() {
    return position1;
  }

  public Point getPosition2() {
    return position2;
  }
}

class RectangleBuilder implements ShapeBuilder {
  private Rectangle rectangle = new Rectangle();

  RectangleBuilder() {
  }

  @Override
  public RectangleBuilder firstClick(Point position) {
    rectangle.setPosition1(position);
    return this;
  }

  @Override
  public RectangleBuilder secondClick(Point point) {
    rectangle.setPosition2(point);
    return this;
  }

  @Override
  public Rectangle getShape() {
    return rectangle;
  }
}