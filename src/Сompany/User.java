package Сompany;

import Menu.Menu;
import java.io.Serial;
import java.util.*;
import java.util.stream.Collectors;

// Класс - Пользователь, наследуется от класса Управление доступом
public class User extends EmployeeControl {

    // Поля класса
    @Serial
    private transient static final long serialVersionUID = -4838735422331560443L;   // номер версии UID для класса
    protected static transient Scanner scanner = new Scanner(System.in); // сканер

    // Конструктор без параметров
    public User() {
        super();   //текущий экземпляр родительского класса
        scanner = new Scanner(System.in);  // сканер
    }

    // Методы
    // 1. Метод "показать полную информацию о сотрудниках"
    protected void showFullInfo() {
        Menu.printHat(); // вывод полей Пользователя на консоль
        for (Employee worker : super.getWorkerData()) {
            System.out.println(worker.toString());
        }
    }

    // 2. Метод "показать информацию об отделе"
    protected boolean showDepartmentInfo() {
        // фильтруем список сотрудников по должности "начальник отдела"
        List<Employee> result = super.getWorkerData().stream().filter(a ->
                Objects.equals(a.jobTitle, "Начальник отдела")).collect(Collectors.toList());// собираем отфильтрованных начальников в список
        if (result.size() == 0) { // если начальников нет, то выводим сообщение
            System.out.println("Нет должности 'Начальник отдела'!");
            // группировка сотрудников по отделу
            Map<String, List<Employee>> departmentGroup = super.getWorkerData().stream().collect(Collectors.groupingBy(a -> a.department));
            System.out.println("отдел");
            for (Map.Entry<String, List<Employee>> entry : departmentGroup.entrySet()) {
                System.out.println(entry.getKey());
            }
            return false;
        }
        System.out.printf("%-40s %-40s%n", "Отдел:", "Начальник отдела:");
        for (Employee worker : result) {
            System.out.printf("%-40s %-40s%n", worker.department, worker.fullName);
        }
        return true;
    }

    // 3. Метод "показать среднюю зарплату по организации и по отделам"
    protected boolean showSalaryInfo() {
        int averageSalary = 0; // средняя зарплата
        int count = 0; // счётчик
        for (Employee worker : super.getWorkerData()) {
            averageSalary += worker.salary;
            count++;
        }
        System.out.printf("%-40s %-40s%n", "Отдел:", "Средняя зарплата:");
        System.out.printf("%-40s %-40s%n", "Организация:", averageSalary / count);
        // группировка сотрудников по отделу
        Map<String, List<Employee>> departmentGroup = super.getWorkerData().stream().collect(Collectors.groupingBy(a -> a.department));
        // проходимся по мапу
        for (Map.Entry<String, List<Employee>> entry : departmentGroup.entrySet()) {
            averageSalary = 0;
            count = 0;
            for (Employee worker : entry.getValue()) {
                averageSalary += worker.salary;
                count++;
            }
            System.out.printf("%-40s %-40s%n", entry.getKey(), averageSalary / count);
        }
        return true;
    }

    // 4. Метод "показать ТОП-10 самых дорогих сотрудников по зарплате"
    protected boolean showTopTenSalary() {
        Menu.printHat(); // Метод "отрисовка шапки таблицы"
        super.sortBySalary(); // сортируем работников по зарплате от большего к меньшему
        for (int i = 0; i < 10; i++) {
            System.out.println(super.getWorkerData().get(i));
        }
        super.sortByID();
        return true;
    }

    // 5. Метод "показать ТОП-10 самых преданных сотрудников по количеству лет работы в организации"
    protected boolean showTopTenEmploymentDate() {
        Menu.printHat();// Метод "отрисовка шапки таблицы"
        super.sortByEmploymentDate();  // сортируем сотрудников по дате приема на работу
        for (int i = 0; i < 10; i++) {
            System.out.println(super.getWorkerData().get(i));
        }
        super.sortByID();
        return true;
    }

    // 6. Метод "показать  результат поиска сотрудников по заданным параметрам"
    protected boolean showOrderBy(List<Employee> result) {
        if (result != null) {
            if (result.size() == 0) {
                System.out.println("Нет совпадений!");
                return false;
            }
            Menu.printHat(); // Метод "отрисовка шапки таблицы"
            for (Employee worker : result)
                System.out.println(worker.toString());
        } else {
            return false;
        }

        return true;
    }

    // 7. Метод "поиск сотрудников по заданным параметрам"
    public List<Employee> orderBy(int choice) {
        List<Employee> result;
        if (choice == 1) {
            Menu.printEnterEnterID(); // выбор "1" по идентификатору сотрудника
            int id;
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Неправильный идентификатор!");
                return null;
            } finally { // гарантированно будет считывать следующую строку
                scanner.nextLine();
            }
            // сравниваем введенный id с существующими в списке сотрудников
            result = super.getWorkerData().stream().filter(a -> Objects.equals(a.id, id)).collect(Collectors.toList());
        } else if (choice == 2) {
            Menu.printEnterFullName(); // выбор "2" по ФИО сотрудника
            String fullName = scanner.nextLine();
            result = super.getWorkerData().stream().filter(a -> Objects.equals(a.fullName, fullName)).collect(Collectors.toList());
        } else if (choice == 3) {
            Menu.printEnterBirthDayDate(); // выбор "3" по дате рождения сотрудника
            String birthDayDate = scanner.nextLine();
            // преобразуем строку в дату
            Date date = tryParseDate(birthDayDate);
            result = super.getWorkerData().stream().filter(a -> Objects.equals(a.birthDayDate, date)).collect(Collectors.toList());
        } else if (choice == 4) {
            Menu.printEnterSex(); // выбор "4" по полу сотрудника
            String sex = scanner.nextLine();
            result = super.getWorkerData().stream().filter(a -> Objects.equals(a.sex, sex)).collect(Collectors.toList());
        } else if (choice == 5) {
            Menu.printEnterTelephoneNumber(); // выбор "5" по номеру телефона сотрудника
            String telephoneNumber = scanner.nextLine();
            result = super.getWorkerData().stream().filter(a -> Objects.equals(a.telephoneNumber, telephoneNumber)).collect(Collectors.toList());
        } else if (choice == 6) {
            Menu.printEnterJobTitle();// выбор "6" по должности сотрудника
            String jobTitle = scanner.nextLine();
            result = super.getWorkerData().stream().filter(a -> Objects.equals(a.jobTitle, jobTitle)).collect(Collectors.toList());
        } else if (choice == 7) {
            Menu.printEnterDepartment(); // выбор "7" по отделу сотрудника
            String department = scanner.nextLine();
            result = super.getWorkerData().stream().filter(a -> Objects.equals(a.department, department)).collect(Collectors.toList());
        } else if (choice == 8) {
            Menu.printEnterEmploymentDate(); // выбор "8" по дате трудоустройства сотрудника
            String employmentDate = scanner.nextLine();
            // преобразуем строку в дату
            Date date = tryParseDate(employmentDate);
            result = super.getWorkerData().stream().filter(a -> Objects.equals(a.employmentDate, date)).collect(Collectors.toList());
        } else if (choice == 9) {
            Menu.printEnterSalary(); // выбор "9" по зарплате сотрудника
            int salary = scanner.nextInt();
            result = super.getWorkerData().stream().filter(a -> Objects.equals(a.salary, salary)).collect(Collectors.toList());
        } else if (choice == 10) {
            Menu.printEnterChief(); // выбор "10" по начальнику отдела, в котором работает сотрудник
            String chief = scanner.nextLine();
            result = super.getWorkerData().stream().filter(a -> Objects.equals(a.chief, chief)).collect(Collectors.toList());
        } else if (choice == 11) {
            return null;
        } else {
            System.out.println("Неправильный выбор!");
            return null;
        }

        return result;
    }
}
