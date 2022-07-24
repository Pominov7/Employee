package SaveAndDownload;

import Сompany.EmployeeControl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

// Класс десериализации объекта
public class Deserialization {

    // 2. Метод десериализации объекта
    public static EmployeeControl makeDeserialization() {
        try (FileInputStream fis = new FileInputStream("Company.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (EmployeeControl) ois.readObject();
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Deserialization exception: " + ex);
        }
        return null;
    }
}