package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryManager {
    private final String directoryPath; // Шлях до директорії

    public DirectoryManager(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public static void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("Директорію створено: " + directoryPath);
        }
    }

    // Перевірка розміру директорії
    public void checkDirectorySize() {
        File dir = new File(directoryPath);
        long totalSize = getDirectorySize(dir); // Отримуємо загальний розмір директорії
        if (totalSize > 100 * 1024 * 1024) { // Перевірка, чи перевищує розмір 100 MB
            deleteOldFiles(dir); // Видаляємо старі файли, якщо потрібно
        }
    }

    // Видалення старих файлів
    private void deleteOldFiles(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (getDirectorySize(dir) < 50 * 1024 * 1024) break; // Перевірка, чи зменшився розмір до 50 MB
                file.delete(); // Видаляємо файл
            }
        }
    }

    // Рекурсивний підрахунок розміру файлів у директорії
    public long getDirectorySize(File dir) {
        long size = 0;
        for (File file : dir.listFiles()) {
            size += file.isDirectory() ? getDirectorySize(file) : file.length(); // Додаємо розмір файлу або піддиректорії
        }
        return size; // Повертаємо загальний розмір
    }

    // Перевірка наявності піддиректорій
    public List<File> getSubdirectories() {
        File[] files = new File(directoryPath).listFiles(File::isDirectory);
        return files != null ? List.of(files) : new ArrayList<>(); // Повертаємо список піддиректорій
    }
}
