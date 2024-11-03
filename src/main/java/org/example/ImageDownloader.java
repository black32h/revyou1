package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageDownloader {
    private final String directoryPath; // Шлях до директорії для збереження зображень

    public ImageDownloader(String directoryPath) {
        this.directoryPath = directoryPath; // Ініціалізація шляху до директорії
    }

    // Завантаження зображень із URL
    public List<File> downloadImages(List<String> imageUrls) throws IOException {
        List<File> downloadedFiles = new ArrayList<>(); // Список для збереження завантажених файлів
        DirectoryManager.createDirectoryIfNotExists(directoryPath); // Перевірка та створення директорії, якщо вона не існує
        for (String imageUrl : imageUrls) {
            URL url = new URL(imageUrl); // Створення URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // Встановлення методу запиту

            // Перевірка коду відповіді
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String fileName = "image_" + System.currentTimeMillis() + ".jpg"; // Генерація унікального імені файлу
                File file = new File(directoryPath, fileName); // Створення нового файлу
                try (InputStream in = connection.getInputStream();
                     OutputStream out = new FileOutputStream(file)) {
                    in.transferTo(out); // Завантаження зображення
                }
                downloadedFiles.add(file); // Додавання файлу до списку
            }
        }
        return downloadedFiles; // Повертаємо список завантажених файлів
    }
}
