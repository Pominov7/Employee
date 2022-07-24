package Menu;

// Интерфейс - Меню
public interface Menu {

    // Методы печати в консоль
    static void printEnterLogin() {
        System.out.print("Введите логин: ");
    }

    static void printEnterPassword() {
        System.out.print("Введите пароль: ");
    }

    static void printEnterFullName() {
        System.out.print("Введите ФИО: ");
    }

    static void printEnterBirthDayDate() {
        System.out.print("Введите дату рождения: ");
    }

    static void printEnterSex() {
        System.out.print("Введите пол: ");
    }

    static void printEnterTelephoneNumber() {
        System.out.print("Введите номер телефона: ");
    }

    static void printEnterJobTitle() {
        System.out.print("Введите наименование должности: ");
    }

    static void printEnterDepartment() {
        System.out.print("Введите название отдела: ");
    }

    static void printEnterEmploymentDate() {
        System.out.print("Введите дату приема на работу: ");
    }

    static void printEnterSalary() {
        System.out.print("Введите зарплату: ");
    }

    static void printEnterChief() {
        System.out.print("Введите начальника: ");
    }

    static void printEnterEnterID() {
        System.out.print("Введите идентификатор сорудника: ");
    }

    static void printEnterOrderByColumn() {
        System.out.println("Выберите поле: ");
    }

    static void printEnterModifyColumn() {
        System.out.println("Изменить: ");
    }

    static void printEnterLoadProgram() {
        System.out.println("Загрузить последнее сохранение программы? ");
        System.out.println("1 - Нет, работать со списком сотрудников из базы данных ");
        System.out.println("2 - Да, загрузить последнее сохранение ");
    }


    // Метод "показать меню для "Пользователя"
    static void printUserMenu() {
        System.out.printf("\n%s\n%s\n%s\n%s\n%s\n%s\n7.Выход%n",
                "1.Показать полную информацию организации",
                "2.Поиск по заданному параметру ",
                "3.Показать информацию об отделе",
                "4.Показать информацию о зарплате",
                "5.ТОП-10 самых дорогих сотрудников по зарплате",
                "6.ТОП-10 самых преданных сотрудников по количеству лет работы в организации"
        );
    }

    // Метод "показать меню для "Администратора"
    static void printAdminMenu() {
        System.out.printf("\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n9.Выход%n",
                "1.Показать полную информацию организации",
                "2.Поиск по заданному параметру ",
                "3.Показать информацию об отделе",
                "4.Показать информацию о зарплате",
                "5.ТОП-10 самых дорогих сотрудников по зарплате",
                "6.ТОП-10 самых преданных сотрудников по количеству лет работы в организации",
                "7.Добавить работника",
                "8.Удалить работника"
        );
    }

    // Метод "показать меню для "Разработчика"
    static void printDeveloperMenu() {
        System.out.printf("\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n11.Выход%n",
                "1.Показать полную информацию организации",
                "2.Поиск по заданному параметру ",
                "3.Показать информацию об отделе",
                "4.Показать информацию о зарплате",
                "5.ТОП-10 самых дорогих сотрудников по зарплате",
                "6.ТОП-10 самых преданных сотрудников по количеству лет работы в организации",
                "7.Добавить работника",
                "8.Удалить работника",
                "9.Изменить информацию",
                "10.Сохранить данные"
        );
    }

    // Метод "показать колонки меню"
    static void printColumnsMenu() {
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n11.Назад\n%n",
                "1.id",
                "2.ФИО",
                "3.Дата рождения",
                "4.Пол",
                "5.Номер телефона",
                "6.Должность",
                "7.Отдел",
                "8.Дата трудоустройства",
                "9.Зарплата",
                "10.Начальник"
        );
    }

    // Метод "отрисовка шапки таблицы"
    static void printHat() {
        System.out.printf("%-12s %-19s %-19s %-7s %-18s %-18s %-12s %-22s %-15s %-20s%n",
                "ID",
                "ФИО",
                "Дата рождения",
                "Пол",
                "Номер телефона",
                "Должность",
                "Отдел",
                "Дата трудоустройства",
                "Зарплата",
                "Начальник"
        );
    }

    // Метод "Показать выбор"
    static void printChoice() {
        System.out.print("Выбор: ");
    }
}
