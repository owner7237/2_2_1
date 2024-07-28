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


        Car car1 = new Car("Pogger", 10);
        User user1 = new User("Юзер", "Lastname1", "user1@mail.ru");
        user1.setCar(car1);
        userService.add(user1);

        Car car2 = new Car("Jobber", 20);
        User user2 = new User("Vivo", "Lastname2", "user2@mail.ru");
        user2.setCar(car2);
        userService.add(user2);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar());
            System.out.println();
        }

        System.out.println(userService.getUserByCar("Pogger", 10));

        context.close();
    }
}
