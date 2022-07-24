package Сompany;

import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// Абстрактный класс - Управление сотрудниками
public abstract class EmployeeControl implements Serializable {

    // Поля класса
    protected ArrayList<Employee> workerData; // список сотрудников
    @Serial
    private transient static final long serialVersionUID = 1L; //версия сериализованных данных

    // Конструктор без параметров
    public EmployeeControl() {
        workerData = new ArrayList<>();                  // создается новый список сотрудников
        try (FileReader reader = new FileReader("src/workerData.txt")) { // читаем из файла
            BufferedReader buffer = new BufferedReader(reader);
            String line = buffer.readLine();            // считывание построчно
            while (line != null) {                     // если строка существует
                String[] data = line.split(";"); //массив строк; разделяем строку на подстроки, используя разделитель ;
                // добавляем сотрудника прочитанного из файла в список
                workerData.add(new Employee(
                        data[0],
                        tryParseDate(data[1]),
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        tryParseDate(data[6]),
                        tryParse(data[7]) == null ? 0 : tryParse(data[7]), // если строка не введена, то возвращаем 0
                        // если, введена, то парсим строку и преобразовываем в int
                        data[8],
                        Integer.parseInt(data[9])
                ));
                line = buffer.readLine(); // прочитаем каждую строку
            }
        } catch (IOException ex) {

            System.out.println("Ошибка доступа к файлу с данными о пользователях");
        }
    }

    // Геттер
    protected ArrayList<Employee> getWorkerData() {
        return workerData;
    }

    // Методы
    // 1. Метод проверки ввода числа
    protected static @Nullable Integer tryParse(String text) {
        try {
            return Integer.parseInt(text); // преобразуем строку в целое число и возвращаем int
        } catch (NumberFormatException e) { // если строка не может быть преобразована в тип int
            return null;
        }
    }

    // 2. Метод для проверки ввода даты
    public static @Nullable                                               //@Nullable(объект может быть = 0)
    Date tryParseDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); // задаём нужный формат для вывода даты
        try {
            return sdf.parse(date); // парсим строку и возвращаем дату в виде объекта класса Date
        } catch (Exception e) {    // если строка не может быть преобразована в тип int
            return null;
        }
    }

    // 3. Метод сортировки сотрудников по id
    protected void sortByID() {
        workerData.sort(Employee.COMPARE_BY_ID);
    }

    // 4. Метод сортировки сотрудников по зарплате
    protected void sortBySalary() {
        workerData.sort(Employee.COMPARE_BY_SALARY);
    }

    // 5. Метод сортировки сотрудников по дате приема на работу
    protected void sortByEmploymentDate() {
        workerData.sort(Employee.COMPARE_BY_EMPLOYMENT_DATE);
    }

}
