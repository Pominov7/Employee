package AccessLevel;
import java.util.Scanner;
import Menu.Menu;
import Menu.MenuControl;
import LoginTool.LoginTool;
import SaveAndDownload.Deserialization;
import SaveAndDownload.Serialization;

// Класс - "Главное приложение"
class AppMain implements Menu {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        // 1. Создаём управление меню
        MenuControl menu = new MenuControl();

        // 2. Создаём вход в систему
        LoginTool loginTool = new LoginTool();

        // 3. Запрос логина и пароля
        LoginTool currentUser = loginTool.signIn(); // текущий пользователь вводит логин и пароль
        if (currentUser == null) {
            System.out.println("Пользователь не найден!");
            // завершение программы (0 = программа завешается без ошибок)
            System.exit(0);
        }
        // если роль совпадает с "user"то ему предоставляется доступ к определенным пунктам меню
        if (currentUser.role.equals("user")) {
            User user = null; // создаём пользователя
            Menu.printEnterLoadProgram(); // выбор загрузки программы
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1 -> user = new User(); // если 1 то работаем с базой данных( из файла)
                    case 2 -> user = (User) Deserialization.makeDeserialization(); // если 2 , то десериализуем объект
                    default -> System.out.println("Некорректный ввод!");
                }
            }
            while (menu.choice != 7) {
                menu.printMenu(currentUser.role);
                if (menu.choice == 1) {
                    assert user != null;
                    user.showFullInfo();
                } else if (menu.choice == 2) {
                    Menu.printEnterOrderByColumn();
                    menu.printColumnsMenu();
                    assert user != null;
                    user.showOrderBy(user.orderBy(menu.columnsChoice));
                } else if (menu.choice == 3) {
                    assert user != null;
                    user.showDepartmentInfo();
                } else if (menu.choice == 4) {
                    assert user != null;
                    user.showSalaryInfo();
                } else if (menu.choice == 5) {
                    assert user != null;
                    user.showTopTenSalary();
                } else if (menu.choice == 6) {
                    assert user != null;
                    user.showTopTenEmploymentDate();
                }
            }
            // если роль совпадает с "admin"то ему предоставляется доступ к определенным пунктам меню
        } else if (currentUser.role.equals("admin")) {
            Admin admin = null;
            Menu.printEnterLoadProgram();
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1 -> admin = new Admin();
                    case 2 -> admin = (Admin) Deserialization.makeDeserialization();
                    default -> System.out.println("Некорректный ввод!");
                }
            }
            while (menu.choice != 9) {
                menu.printMenu(currentUser.role);
                if (menu.choice == 1) {
                    assert admin != null; //Assert позволяют отлавливать ошибки в программе во время ее исполнения.
                    admin.showFullInfo();
                } else if (menu.choice == 2) {
                    Menu.printEnterOrderByColumn();
                    menu.printColumnsMenu();
                    assert admin != null;
                    admin.showOrderBy(admin.orderBy(menu.columnsChoice));
                } else if (menu.choice == 3) {
                    assert admin != null;
                    admin.showDepartmentInfo();
                } else if (menu.choice == 4) {
                    assert admin != null;
                    admin.showSalaryInfo();
                } else if (menu.choice == 5) {
                    assert admin != null;
                    admin.showTopTenSalary();
                } else if (menu.choice == 6) {
                    assert admin != null;
                    admin.showTopTenEmploymentDate();
                } else if (menu.choice == 7) {
                    assert admin != null;
                    admin.addWorker();
                } else if (menu.choice == 8) {
                    assert admin != null;
                    admin.showFullInfo();
                    Menu.printEnterOrderByColumn();
                    menu.printColumnsMenu();
                    admin.removeWorker(admin.orderBy(menu.columnsChoice));
                }
            }

            // если роль совпадает с "developer"то ему предоставляется доступ к определенным пунктам меню
        } else if (currentUser.role.equals("developer")) {
            Developer developer = null;
            Menu.printEnterLoadProgram();
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1 -> developer = new Developer();
                    case 2 -> developer = (Developer) Deserialization.makeDeserialization();
                    default -> System.out.println("Некорректный ввод!");
                }
            }
            while (menu.choice != 11) {
                menu.printMenu(currentUser.role);
                if (menu.choice == 1) {
                    assert developer != null;
                    developer.showFullInfo();
                } else if (menu.choice == 2) {
                    Menu.printEnterOrderByColumn();
                    menu.printColumnsMenu();
                    assert developer != null;
                    developer.showOrderBy(developer.orderBy(menu.columnsChoice));
                } else if (menu.choice == 3) {
                    assert developer != null;
                    developer.showDepartmentInfo();
                } else if (menu.choice == 4) {
                    assert developer != null;
                    developer.showDepartmentInfo();
                } else if (menu.choice == 5) {
                    assert developer != null;
                    developer.showTopTenSalary();
                } else if (menu.choice == 6) {
                    assert developer != null;
                    developer.showTopTenEmploymentDate();
                } else if (menu.choice == 7) {
                    assert developer != null;
                    developer.addWorker();
                } else if (menu.choice == 8) {
                    assert developer != null;
                    developer.showFullInfo();
                    Menu.printEnterOrderByColumn();
                    menu.printColumnsMenu();
                    developer.removeWorker(developer.orderBy(menu.columnsChoice));
                } else if (menu.choice == 9) {
                    assert developer != null;
                    developer.showFullInfo();
                    Menu.printEnterOrderByColumn();
                    menu.printColumnsMenu();
                    developer.modifyWorkerDate(developer.orderBy(menu.columnsChoice));
                } else if (menu.choice == 10) {
                    Serialization.makeSerialization(developer);
                }
            }
        } else {
            System.out.println("У пользователя нет роли!");
        }

    }
}
