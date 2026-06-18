package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FileReader {
    private static final Logger logger = Logger.getLogger(FileReader.class.getName());
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
        }  catch (IOException e) {
            // Вместо System.out используем правильный логгер, который просит SonarQube!
            logger.log(Level.SEVERE, "Ошибка при чтении файла", e);
        }
        return new Profile(name, age, email, phone);
        }

    }

