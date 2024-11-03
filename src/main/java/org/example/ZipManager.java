package org.example;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.model.enums.CompressionMethod;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ZipManager {
    // Метод для отримання дати в форматі для імені архіву
    public static String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(new Date());
    }

    // Створення ZIP-архіву з паролем
    public static void zipFilesWithPassword(List<File> files, String zipFileName, String password) throws Exception {
        ZipFile zipFile = new ZipFile(zipFileName);

        // Встановлюємо параметри для ZIP-архіву
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        zipParameters.setEncryptFiles(true); // Вказуємо, що файли будуть зашифровані

        // Додаємо файли до ZIP-архіву з параметрами
        zipFile.addFiles(files, zipParameters);

        // Встановлюємо пароль для шифрування після додавання файлів
        zipFile.setPassword(password.toCharArray()); // Устанавливаем пароль здесь
    }
}
