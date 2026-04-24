package module_3.ChainOfResponsibilityDemo;

import java.util.LinkedList;

public class ChainOfResponsibilityDemo {
  public static void main(String[] args) {
    User user1 = new User("ad", "123456789");
    User user2 = new User("Admin", "123");
    User user3 = new User("admin", "123456789");
    User user4 = new User("Admin", "123456789");

    AuthChain authChain = new AuthChain();
    authChain.pushValidator(new UserNameValidator());
    authChain.pushValidator(new UserPasswordValidator());

    System.out.println("Validating user1: " + user1);
    System.out.println(authChain.validate(user1));

    System.out.println("Validating user2: " + user2);
    System.out.println(authChain.validate(user2));

    System.out.println("Validating user3: " + user3);
    System.out.println(authChain.validate(user3));

    System.out.println("Validating user4: " + user4);
    System.out.println(authChain.validate(user4));
  }
}

class User {
  private String name;
  private String password;

  User(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "{ name: " + name + ", password: " + password + " }";
  }
}

interface Validator {
  boolean validate(User user);
}

class UserPasswordValidator implements Validator {
  @Override
  public boolean validate(User user) {
    if (user.getPassword().length() <= 8) {
      System.out.println("User password should be longer then 8 symbols");
      return false;
    }

    return true;
  }
}

class UserNameValidator implements Validator {
  @Override
  public boolean validate(User user) {
    if (user.getName().length() < 3) {
      System.out.println("User name should be longer then 3 symbols");
      return false;
    }

    if (!Character.isUpperCase(user.getName().charAt(0))) {
      System.out.println("First letter of name should be capital");
      return false;
    }

    return true;
  }
}

class AuthChain implements Validator {
  private LinkedList<Validator> chain = new LinkedList<>();

  public void pushValidator(Validator validator) {
    chain.add(validator);
  }

  @Override
  public boolean validate(User user) {
    for (Validator v : chain) {
      if (!v.validate(user))
        return false;
    }
    return true;
  }
}