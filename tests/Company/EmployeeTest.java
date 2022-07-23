package Company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Сompany.Employee;
import Сompany.EmployeeControl;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class EmployeeTest {

    ArrayList<Employee> employees = new ArrayList<>(); // Тестируемый список сотрудников

    @BeforeEach
    // Метод добавления сотрудников в список(срабатывает для каждого метода)
    public void addListEmployee() {
        employees.add(new Employee("Иванов Олег Петрович", EmployeeControl.tryParseDate("04.05.1989"),
                "Мужской", "8(920)920-43-22", "Программист", "IT",
                EmployeeControl.tryParseDate("12.01.1995"), 120000, "Гринвич Петр Иванович", 25));
        employees.add(new Employee("Алешин Игорь Николаевич", EmployeeControl.tryParseDate("03.01.2005"),
                "Мужской", "8(913)945-22-50", "Администратор", "IT",
                EmployeeControl.tryParseDate("31.10.1972"), 80000, "Гринвич Петр Иванович", 12));
        employees.add(new Employee("Пак Альберт Сергеевич", EmployeeControl.tryParseDate("25.01.1970"),
                "Мужской", "8(913)945-22-50", "Агент", "IT",
                EmployeeControl.tryParseDate("20.08.2000"), 50000, "Гринвич Петр Иванович", 30));
    }

    @Test
    void getId() {
        employees.sort(Employee.COMPARE_BY_ID);
        assertEquals(12, employees.get(0).getId());
    }

    @Test
    void getSalary() {
        employees.sort(Employee.COMPARE_BY_SALARY);
        assertEquals(120000, employees.get(0).getSalary());
    }

    @Test
    void getEmploymentDate() {
        employees.sort(Employee.COMPARE_BY_EMPLOYMENT_DATE);
        assertEquals(EmployeeControl.tryParseDate("31.10.1972"), employees.get(0).getEmploymentDate());
    }
}