package cz.uhk.fim.pro2.shopping.utils;

import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    /**
     * Metoda pro nacteni obrazku ze souboru
     * @param imageName nazev souboru
     * @return obrazek
     */
    public static Image loadImage(String imageName) { //Zmenil jsem awt.Image na javafx.scene.image, protoze obrazek avatar vyzduje javafx.scene.image
        /*
         (jen pro awt.Image)
         String path = getResource(imageName).getFile();
         Image image = new ImageIcon(path).getImage();
         Jestli budeme pustit applikace pres jar. file tak (jen pro awt.Image):
         URL url = FileUtils.class.getResource(imageName);
         java.awt.Image image = new ImageIcon(url).getImage();
         */
        return new Image(imageName);
    }

    /**
     * Metoda pro zapis dat do souboru
     * @param content data
     * @param filename nazev souboru
     * @param extension koncovka souboru
     */
    public static void writeToFile(byte[] content, String filename, String extension) {
        try {
            Files.write(
                    Paths.get(String.format("%s.%s", filename, extension)),
                    content
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metoda pro nacteni obsahu souboru
     * @param filename cesta a nazev souboru
     * @return data v rezetci
     */
    public static String readFromFile(String filename, String extension) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(String.format("%s.%s", filename, extension))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


}
