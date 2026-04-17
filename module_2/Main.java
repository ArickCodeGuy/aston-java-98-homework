package module_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    List<Book> books = readBooks();
    List<Student> students = readStudents(books);

    // Вывести в консоль каждого студента (переопределите toString)
    students.stream().forEach(s -> {
      System.out.println(s.toString());
      // Получить для каждого студента список книг
      List<Book> b = s.books;
    });

    // Получить книги ??
  }

  public static List<Book> readBooks() {
    List<Book> res = new ArrayList<>();
    try {
      String booksString = Files.readString(Path.of("module_2/books.txt"));
      for (String bookString : booksString.split("\r?\n")) {
        String[] s = bookString.split(" \\| ");

        long id = Long.parseLong(s[0]);
        String name = s[1];
        long pages = Long.parseLong(s[2]);
        int publish_year = Integer.parseInt(s[3]);

        Book book = new Book(id, name, pages, publish_year);
        res.add(book);
      }
    } catch (IOException e) {
      System.out.println("File books.txt not found");
    }

    return res;
  }

  public static List<Student> readStudents(List<Book> books) {
    HashMap<Long, Book> booksMap = new HashMap<>();
    for (Book b : books) {
      booksMap.put(b.id, b);
    }

    List<Student> res = new ArrayList<>();
    try {
      String fileString = Files.readString(Path.of("module_2/students.txt"));
      for (String studentAsString : fileString.split("\r?\n")) {
        String[] s = studentAsString.split(" \\| ");

        long id = Long.parseLong(s[0]);
        String name = s[1];

        String[] booksIds = s[2].substring(1, s[2].length() - 1).split(",\s");
        List<Book> studentBooks = new ArrayList<>();
        for (String bookId : booksIds) {
          long bookIdLong = Long.parseLong(bookId);
          if (!booksMap.containsKey(bookIdLong))
            continue;
          studentBooks.add(booksMap.get(bookIdLong));
        }

        res.add(new Student(id, name, studentBooks));
      }
    } catch (IOException e) {
      System.out.println("File students.txt not found");
    }

    return res;
  }
}

class Student {
  public final long id;
  public final String name;
  // Я бы лучше id книг хранил `List<Long>`
  public List<Book> books;

  public Student(long id, String name, List<Book> books) {
    this.id = id;
    this.name = name;
    this.books = books;
  }

  public String toString() {
    return id + " | " + name + " | " + books.toString();
  }
}

class Book {
  public final long id;
  public final String name;
  public final long pages;
  public final int publish_year;

  public Book(long id, String name, long pages, int publish_year) {
    this.id = id;
    this.name = name;
    this.pages = pages;
    this.publish_year = publish_year;
  }

  public String toString() {
    return id + " | " + name + " | " + pages + " | " + publish_year;
  }
}
