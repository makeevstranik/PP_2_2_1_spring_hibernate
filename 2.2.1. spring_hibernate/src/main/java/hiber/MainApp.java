package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car1 = new Car("mazda", 23162);
      Car car2 = new Car("ford", 34254);
      User user5 = new User("User5", "Lastname5", "user5@mail.ru", car1);
      userService.add(user5);
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", car2));

      System.out.println("User5 from DB: " + userService.getUser(5L));
      List<User> users = userService.listUsers();

      users.forEach(System.out::println);



      context.close();
   }
}
