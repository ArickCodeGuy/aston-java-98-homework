package module_1;

public class Main {
  public static void main(String[] args) {
    MyChangeableClass mutable = new MyChangeableClass("Oritinal string");
    MyImmutableClass immutable = new MyImmutableClass(mutable);

    // Оригинальное значение
    System.out.println("Immutable field: "
        + immutable.getMyChangeableField());

    // Пытаемся поменять поле у ссылки
    mutable.myChangeableField = "Changed string 1";
    // Пытаемся поменять поле у иммутабельного объекта
    immutable.getMyChangeableClass().myChangeableField = "Changed string 2";

    // Поле изменить не получилось
    System.out.println("Immutable field: "
        + immutable.getMyChangeableField());
  }
}

// Мутабельный сласс
class MyChangeableClass {
  public String myChangeableField = "String";

  MyChangeableClass(String str) {
    this.myChangeableField = str;
  }

  MyChangeableClass(MyChangeableClass ref) {
    this.myChangeableField = ref.myChangeableField;
  }
}

// Иммутабельный класс - все поля private final
final class MyImmutableClass {
  private final MyChangeableClass myChangeableClass;

  public MyImmutableClass(MyChangeableClass ref) {
    // Сюда новую ссылку кладем, чтобы менять нельзя было
    this.myChangeableClass = new MyChangeableClass(ref);
  }

  public MyChangeableClass getMyChangeableClass() {
    return new MyChangeableClass(this.myChangeableClass);
  }

  public String getMyChangeableField() {
    return getMyChangeableClass().myChangeableField;
  }
}