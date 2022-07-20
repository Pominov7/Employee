package AccessLevel;
import Menu.Menu;
import java.io.Serial;
import java.util.List;
import Menu.MenuControl;
// Класс - Разработчик, наследуется от класса "Администратор", и реализует интерфейс "Меню"
public class Developer extends Admin implements Menu {

    @Serial
    private transient static final long serialVersionUID =-2926902666805314734L; // номер версии UID для класса
    // Конструктор без параметров
    public Developer() {
        super();
    }

    // Методы
    // 1. Метод "модифицировать работника"
    public boolean modifyWorkerDate(List<Employee> result) {
        if (result != null) {
            if (result.size() == 0) {
                System.out.println("Нет совпадений!");
                return false;
            }
            showOrderBy(result); // показываем сотрудников до модификации
            MenuControl menu = new MenuControl();
            Menu.printEnterModifyColumn(); // модифицировать колонку
            menu.printColumnsMenu();       // печатаем колонки меню
            if (menu.columnsChoice == 1) {
                System.out.println("Невозможно редактировать идентификатор даже для разработчика!");
                return false;
            } else if (menu.columnsChoice == 2) {
                Menu.printEnterFullName();  // редактируем ФИО работника
                String fullName = scanner.nextLine();
                for (Employee worker : result)
                    worker.fullName = fullName;
            } else if (menu.columnsChoice == 3) {
                Menu.printEnterBirthDayDate(); // редактируем дату рождения работника
                String birthDayDate = scanner.nextLine();
                for (Employee worker : result)
                    worker.birthDayDate = tryParseDate(birthDayDate); // проверка корректности ввода даты
            } else if (menu.columnsChoice == 4) {
                Menu.printEnterSex(); // редактируем пол работника
                String sex = scanner.nextLine();
                for (Employee worker : result)
                    worker.sex = sex;
            } else if (menu.columnsChoice == 5) {
                Menu.printEnterTelephoneNumber(); // редактируем номер телефона работника
                String telephoneNumber = scanner.nextLine();
                for (Employee worker : result)
                    worker.telephoneNumber = telephoneNumber;
            } else if (menu.columnsChoice == 6) {
                Menu.printEnterJobTitle(); // редактируем должность работника
                String jobTitle = scanner.nextLine();
                for (Employee worker : result)
                    worker.jobTitle = jobTitle;
            } else if (menu.columnsChoice == 7) {
                Menu.printEnterDepartment(); // редактируем отдел, к которому приписан работника
                String department = scanner.nextLine();
                for (Employee worker : result)
                    worker.department = department;
            } else if (menu.columnsChoice == 8) {
                Menu.printEnterEmploymentDate(); // редактируем дату трудоустройства работника
                String employmentDate = scanner.nextLine();
                for (Employee worker : result)
                    worker.employmentDate = tryParseDate(employmentDate); // проверка корректности ввода даты
            } else if (menu.columnsChoice == 9) {
                Menu.printEnterSalary(); // редактируем зарплату работника
                int salary = scanner.nextInt();
                scanner.nextLine();
                for (Employee worker : result)
                    worker.salary = salary;
            } else if (menu.columnsChoice == 10) {
                Menu.printEnterChief(); // редактируем начальника, которому подчиняется работник
                String chief = scanner.nextLine();
                for (Employee worker : result)
                    worker.chief = chief;
            } else if (menu.columnsChoice == 11) {
                return false;
            } else {
                System.out.println("Неправильный выбор!");
                return false;
            }
        } else {
            return false;
        }

        showOrderBy(result); // показываем сотрудников после модификации
        rebuildFile();       // записываем файл
        return true;
    }
}
