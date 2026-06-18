package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader {

    public Profile getDataFromFile(File file) {
        String name = null;
        Integer age = null;
        String email = null;
        Long phone = null;

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Name: "))  name = line.substring(6);
                if (line.startsWith("Age: "))   age = Integer.valueOf(line.substring(5));
                if (line.startsWith("Email: ")) email = line.substring(7);
                if (line.startsWith("Phone: ")) phone = Long.valueOf(line.substring(7));
            }
        } catch (java.io.FileNotFoundException e) {
            // Этот блок сработает, ТОЛЬКО если файла нет на диске
            System.out.println("Файл не найден! Проверьте путь: " + e.getMessage());
        } catch (IOException e) {
            // Этот блок сработает на любые другие ошибки чтения (например, файл заблокирован)
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return new Profile(name, age, email, phone);
        }

    }

