package org.example;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String directoryPath = "downloaded_images"; // Шлях до директорії для збереження зображень
        String emailRecipient = "blackspetya@gmail.com"; // Email для отримання сповіщення

        // Ініціалізація сервісу для завантаження зображень
        ImageDownloader imageDownloader = new ImageDownloader(directoryPath);
        List<String> imageUrls = List.of(
                "https://p7.hiclipart.com/preview/124/426/559/tiger-flame-lion-tigon-tiger.jpg",
                "https://png.pngtree.com/png-vector/20230930/ourmid/pngtree-panda-png-with-ai-generated-png-image_10153558.png",
                "https://img.freepik.com/free-vector/cute-koala-cartoon-character_1308-132636.jpg"
        );

        // Завантаження зображень
        List<File> downloadedImages = imageDownloader.downloadImages(imageUrls);

        // Додавання водяного знака до кожного зображення
        for (File image : downloadedImages) {
            WatermarkUtil.addWatermark(image);
        }

        // Архівація, якщо завантажено більше 3 зображень
        if (downloadedImages.size() > 3) {
            ZipManager.zipFilesWithPassword(downloadedImages, "images_" + ZipManager.getFormattedDate() + ".zip", "your_password"); // Архівуємо зображення з паролем
        }

        // Перевірка розміру директорії та видалення старих файлів, якщо потрібно
        DirectoryManager directoryManager = new DirectoryManager(directoryPath);
        directoryManager.checkDirectorySize();

        // Архівація піддиректорій, якщо їх більше 10
        if (directoryManager.getSubdirectories().size() > 10) {
            for (File subDir : directoryManager.getSubdirectories()) {
                List<File> subDirFiles = List.of(subDir.listFiles());
                ZipManager.zipFilesWithPassword(subDirFiles, subDir.getName() + "_" + ZipManager.getFormattedDate() + ".zip", "your_password");
            }
        }

        // Надсилаємо електронний лист з інформацією про завантажені файли
        EmailService emailService = new EmailService();
        String subject = "Завантаження зображень завершено";
        String messageBody = "Зображення були успішно завантажені.";
        emailService.sendEmail(emailRecipient, subject, messageBody);
    }
}
