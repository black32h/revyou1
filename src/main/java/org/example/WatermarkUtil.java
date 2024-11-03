package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WatermarkUtil {
    public static void addWatermark(File imageFile) throws IOException {
        BufferedImage originalImage = ImageIO.read(imageFile);
        Graphics2D g2d = (Graphics2D) originalImage.getGraphics();

        // Налаштування водяного знака
        String watermark = "Watermark"; // Ваш текст водяного знака
        g2d.setColor(Color.RED); // Колір водяного знака
        g2d.setFont(new Font("Arial", Font.BOLD, 64)); // Шрифт водяного знака
        g2d.drawString(watermark, originalImage.getWidth() / 4, originalImage.getHeight() / 2); // Позиціювання водяного знака

        // Збереження зображення з водяним знаком
        ImageIO.write(originalImage, "jpg", imageFile);
        g2d.dispose(); // Вивільнення ресурсів
    }
}
