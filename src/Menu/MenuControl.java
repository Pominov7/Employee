package Menu;

import java.util.Scanner;

// Класс - Управление меню, реализует интерфейс "Меню"
public class MenuControl implements Menu {

    // Поля класса
    public Scanner scanner;
    public int choice;         // выбор пункта
    public int columnsChoice;  // выбор колонки

    // Конструктор без параметров
    public MenuControl() {
        scanner = new Scanner(System.in);
        choice = 0;
        columnsChoice = 0;
    }

    // Методы
    // 1. Метод печати меню в соответствии с введенной ролью
    public void printMenu(String role) {
        if (role.equals("user")) {                 // если введен "user"
            Menu.printUserMenu();                  // отрисовываем меню для пользователя
        } else if (role.equals("admin")) {         // если введен "admin"
            Menu.printAdminMenu();                 // отрисовываем меню для администратора
        } else if (role.equals("developer")) {     // если введен "developer"
            Menu.printDeveloperMenu();             // отрисовываем меню для разработчика
        } else {
            System.out.println("У пользователя нет роли!");
        }
        System.out.println("");
        Menu.printChoice();
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            choice = 0;
        }

        scanner.nextLine();

    }

    // 2. Метод печати колонок меню
    public void printColumnsMenu() {
        Menu.printColumnsMenu();
        Menu.printChoice();
        try {
            columnsChoice = scanner.nextInt();
        } catch (Exception e) {
            columnsChoice = 0;
        }
        scanner.nextLine();
    }
}
