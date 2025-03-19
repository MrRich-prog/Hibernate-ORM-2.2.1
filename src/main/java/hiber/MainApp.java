package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.cleanAllTable();

        Car car1 = new Car(1, "car1");
        User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
        userService.add(user1);

        Car car2 = new Car(2, "car2");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);
        userService.add(user2);

        Car car3 = new Car(3, "car3");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru", car3);
        userService.add(user3);

        Car car4 = new Car(4, "car4");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru", car4);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
            System.out.println();
        }

        System.out.println("Поиск пользователя по машине - 2 car2");
        System.out.println();
        System.out.println(userService.findUserByCar(2, "car2"));

        context.close();
    }
}