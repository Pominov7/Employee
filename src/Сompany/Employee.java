package Сompany;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

//Класс - Сотрудник
public class Employee implements Serializable {

    // Поля класса
    protected String fullName;          // ФИО сотрудника
    protected String sex;               // пол
    protected String telephoneNumber;   // номер телефона
    protected String jobTitle;          // должность
    protected String department;        // отдел
    protected String chief;             // начальник
    protected int id;                   // идентификатор сорудника (табельный номер)
    protected double salary;            // зарплата
    protected Date birthDayDate;        // дата рождения
    protected Date employmentDate;      // дата приема на работу

    // Конструктор с параметрами
    public Employee(String fullName, Date birthDayDate, String sex, String telephoneNumber,
                    String jobTitle, String department, Date employmentDate, double salary,
                    String chief, int id) {
        this.fullName = fullName;
        this.birthDayDate = birthDayDate;
        this.sex = sex;
        this.telephoneNumber = telephoneNumber;
        this.jobTitle = jobTitle;
        this.department = department;
        this.employmentDate = employmentDate;
        this.salary = salary;
        this.chief = chief;
        this.id = id;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    // Методы
    // 1. toString
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.format(birthDayDate);
        sdf.format(employmentDate);
        return String.format("%-4s %-28s %-16s %-8s %-17s %-18s %-20s %-17s %-10s %-15s%n",
                id, fullName, sdf.format(birthDayDate), sex, telephoneNumber, jobTitle, department,
                sdf.format(employmentDate), salary, chief
        );
    }

    // 2. Компаратор для сортировки сотрудников по id (от меньшего к большему)
    public static final Comparator<Employee> COMPARE_BY_ID = Comparator.comparingInt(Employee::getId);

    // 3. Компаратор для сортировки сотрудников по зарплате по убыванию
    public static final Comparator<Employee> COMPARE_BY_SALARY = (salaryMin, salaryMax) ->
            (int) (salaryMax.getSalary() - salaryMin.getSalary());

    // 4. Компаратор для сортировки сотрудников по дате приема на работу (от старых к новым)
    public static final Comparator<Employee> COMPARE_BY_EMPLOYMENT_DATE = Comparator.comparing(Employee::getEmploymentDate);
}
