package hiber;

import hiber.config.AppConfig;
import hiber.model.*;
import hiber.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      String carModel1 = "GoodCar";
      String carModel2 = "BadCar";
      int carSeries1 = 325;

      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");

      user1.setCar(new Car(carModel1, carSeries1));
      user3.setCar(new Car("Car2", 999));

      userService.addUser(user1);
      userService.addUser(user2);
      userService.addUser(user3);

      List<User> users = userService.getListUsers();
      for (User user : users) {
         System.out.println(user);
         try {
            System.out.println("Car model = " + user.getCar().getModel());
            System.out.println("Car series = " + user.getCar().getSeries() + "\n");
         } catch (NullPointerException e) {
            System.out.println("User does not have a car" + "\n");
         }
      }

      System.out.println(carService.getCarOwner(carModel1, carSeries1));
      System.out.println(carService.getCarOwner(carModel2, carSeries1));

      context.close();
   }
}
