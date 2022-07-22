package LoginTool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Класс Тест - Вход в систему
class LoginToolTest {

    @Test
    void signIn() {
        LoginTool access = new LoginTool("user","user","user");
        assertEquals("user",access.getLogin());
        assertEquals("user",access.getPassword());
        assertEquals("user",access.getRole());
    }
}