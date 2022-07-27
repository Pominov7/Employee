package LoginTool;

import Menu.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Класс - "Вход в систему"
public class LoginTool {

    // Поля класса
    private ArrayList<LoginTool> userData; // список содержащий логин и пароль
    private String login;                  // логин
    private String password;               // пароль
    public String role = "default";        // роль

    // Конструкторы
    // 1. Конструктор без параметров
    public LoginTool() {
        userData = new ArrayList<>(); // создаём новый список роль/логин/пароль
        try (FileReader reader = new FileReader("src/userData.txt")) { // читаем из файла
            BufferedReader buffer = new BufferedReader(reader);
            // считывание построчно
            String line = buffer.readLine();
            while (line != null) {
                String[] data = line.split(";"); // разделяем строку на подстроки, используя разделитель ;
                // добавляем роль, логин, пароль прочитанные из файла в список
                userData.add(new LoginTool(data[0], data[1], data[2]));
                line = buffer.readLine(); // прочитаем каждую строку
            }
        } catch (IOException ex) {
            System.out.println("Ошибка доступа к файлу с данными о пользователях");
        }
    }

    // 2. Конструктор с параметрами
    public LoginTool(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    // Геттеры
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // 3. Метод "Вход в систему"
    public LoginTool signIn() {
        Scanner scanner = new Scanner(System.in);
        Menu.printEnterLogin();              // "Введите логин"
        String login = scanner.nextLine();
        Menu.printEnterPassword();           // "Введите пароль"
        String password = scanner.nextLine();
        for (LoginTool user : this.userData) { // проходим по списку логин/пароль/роль
            // Метод trim() — удаляет пробелы в начале и конце строки, и возвращает строку
            if (user.login.equals(login.trim()) && user.password.equals(password.trim())) {
                return user; // если логин и пароль совпадают, то пользователь входит в систему
            }
        }
        return null;
    }

}
