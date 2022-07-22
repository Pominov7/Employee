package Company;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

// Класс Тест - Управление сотрудниками
class EmployeeControlTest {

    @Test
    void tryParse() {
        String text = "12";
        Integer num = Integer.parseInt(text);
        assertEquals(12, num);

    }

    @Test
    void tryParseDate() throws ParseException {
        String date = "31.10.1987";
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.parse(date);
        assertEquals("31.10.1987", date);

    }
}