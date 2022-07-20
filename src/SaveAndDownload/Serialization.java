package SaveAndDownload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

// Класс сериализации объекта
public class Serialization {

    // 1. Метод сериализации объекта
    public static void makeSerialization( Object obj) {
        try (FileOutputStream fos = new FileOutputStream("Company.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            System.out.println("Данные успешно сохранены ");
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        }
    }

}