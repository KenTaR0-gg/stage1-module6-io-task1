package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class pars {

    public Profile getDataFromFile(File file) {
        // 1. Создаем переменные для хранения будущих данных профиля
        String name = null;
        Integer age = null;
        String email = null;
        Long phone = null;

        // 2. Читаем файл построчно
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Если строка пустая, пропускаем её от греха подальше
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Делим строку по двоеточию с пробелом ": "
                // parts[0] станет ключом (например, "name"), parts[1] — значением ("Anya")
                String[] parts = line.split(": ");

                if (parts.length >= 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    // Проверяем ключ и заполняем соответствующую переменную
                    switch (key) {
                        case "name":
                            name = value;
                            break;
                        case "age":
                            age = Integer.valueOf(value); // Конвертируем в Integer
                            break;
                        case "email":
                            email = value;
                            break;
                        case "phone":
                            phone = Long.valueOf(value); // Конвертируем в Long
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        // 3. Самый важный шаг: вызываем конструктор Profile с РЕАЛЬНЫМИ данными!
        // Именно это заставит ваш тест загореться зеленым цветом.
        return new Profile(name, age, email, phone);
    }
}