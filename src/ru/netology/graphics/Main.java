package ru.netology.graphics;

import ru.netology.graphics.image.Converter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;
import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

//         Или то же, но с сохранением в файл:
//        PrintWriter fileWriter = new PrintWriter(new File("converted-image.txt"));
//        converter.setMaxWidth(200);
//        converter.setMaxHeight(300);
//        fileWriter.write(converter.convert("https://funpick.ru/wp-content/uploads/2017/10/Malenkie-8.jpg"));
//        fileWriter.close();

    }
}
