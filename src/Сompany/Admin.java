package Сompany;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import Menu.Menu;

// Класс - Администратор, наследуется от класса "Пользователь", и реализует интерфейс "Меню"
public class Admin extends User implements Menu {

    // Поля класса
    @Serial
    private transient static final long serialVersionUID = 5436478208476617227L; // номер версии UID для класса

    // Конструктор без параметров
    public Admin() {
        super();    //текущий экземпляр родительского класса
        scanner = new Scanner(System.in); // сканер
    }

    // Методы
    // 1. Добавить работника
    public boolean addWorker() {

        super.sortByID(); // сортируем список сотрудников по id
        int id = super.getWorkerData().get(super.getWorkerData().size() - 1).id + 1; //«получить объект из
        // списка по индексу, размер списка минус единица (так как отсчёт с нуля),
        // возьмём у этого объекта I’d и прибавим к нему единицу», чтобы получился следующий по порядку id

        Menu.printEnterFullName(); // Введите ФИО:
        String fullName = scanner.nextLine().replace(";", ""); // заменяем ; на пробел
        Menu.printEnterBirthDayDate(); // Введите дату рождения:
        String birthDayDate = scanner.nextLine().replace(";", "");
        Menu.printEnterSex(); // Введите пол:
        String sex = scanner.nextLine().replace(";", "");
        Menu.printEnterTelephoneNumber();
        String telephoneNumber = scanner.nextLine().replace(";", "");
        Menu.printEnterJobTitle(); // Введите номер телефона:
        String jobTitle = scanner.nextLine().replace(";", "");
        Menu.printEnterDepartment(); // Введите название отдела
        String department = scanner.nextLine().replace(";", "");
        Menu.printEnterEmploymentDate(); //Введите дату приема на работу
        String employmentDate = scanner.nextLine().replace(";", "");
        Menu.printEnterSalary(); //Введите зарплату
        int salary;
        try {
            salary = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Некорректно введена заработная плата!");
            salary = 0;
        } finally {
            scanner.nextLine();
        }
        Menu.printEnterChief(); // Введите начальника
        String chief = scanner.nextLine().replace(";", "");
        // добавляем в список сотрудников нового сотрудника
        super.getWorkerData().add(new Employee(fullName, tryParseDate(birthDayDate), sex,
                telephoneNumber, jobTitle, department, tryParseDate(employmentDate), salary, chief, id));
        super.showFullInfo(); // показываем информацию о сотрудниках
        rebuildFile(); // записываем в файл
        return true;

    }

    // 2. Удалить работника
    public boolean removeWorker(List<Employee> result) {
        if (result != null) {
            if (result.size() == 0) {
                System.out.println("Сотрудник не найден");
                return false;
            }
            for (Employee worker : result)
                super.getWorkerData().remove(worker);

        } else {
            return false;
        }

        super.showFullInfo(); // показываем информацию о сотрудниках
        rebuildFile(); //записываем в файл
        return true;
    }

    // 3. Метод записи в файл информации о сотрудниках
    public void rebuildFile() {
        int counter = 0; // счётчик
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try (FileWriter writer = new FileWriter("src/workerData.txt", true)) {
            //Запись информации о сотрудниках
            // "StandardOpenOption.TRUNCATE_EXISTING" Если файл уже существует и открыт для записи, его длина обрезается до 0, а
            // если файл открыт только для чтения, этот параметр игнорируется
            Files.newBufferedWriter(Path.of("src/workerData.txt"), new StandardOpenOption[]{StandardOpenOption.TRUNCATE_EXISTING});
            for (Employee worker : super.getWorkerData()) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                        worker.fullName,
                        sdf.format(worker.birthDayDate),
                        worker.sex,
                        worker.telephoneNumber,
                        worker.jobTitle,
                        worker.department,
                        sdf.format(worker.employmentDate),
                        worker.salary,
                        worker.chief,
                        worker.id
                ));
                counter++;
                if (counter == super.getWorkerData().size()) { // если счётчик = длине списка
                    continue; // возвращаемся в начало цикла
                } else {
                    writer.write("\n"); // запись с новой строки
                }
            }
            writer.flush(); // данные в файл записываются из буфера

        } catch (IOException ex) {
            System.out.println("Ошибка доступа к файлу с данными о работниках");
        }
    }

}
