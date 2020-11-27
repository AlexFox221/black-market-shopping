package cz.uhk.fim.pro2.shopping.utils.parser;

import cz.uhk.fim.pro2.shopping.model.Child;
import cz.uhk.fim.pro2.shopping.model.GenderType;
import cz.uhk.fim.pro2.shopping.utils.Constants;
import cz.uhk.fim.pro2.shopping.utils.FileUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility trida pro praci s CSV soubory
 */
public class CSVHandler {

    /**
     * Metoda pro vygenerovani retezce v CSV formatu
     * @param childList list deti, ktere chceme zapsat do retezce
     */
    public static void generateCsv(String filename, List<Child> childList) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        for (Child child : childList) {
            sb.append(
                    String.format("%s;%s;%.2f;%s;%d;%s;%b;%.1f;%b;%s;%d;%d;%d;%s%n",
                            child.getPersonalId(),
                            child.getDisplayName(),
                            child.getPrice(),
                            sdf.format(child.getBirthDate()),
                            child.getAge(),
                            child.getGender(),
                            child.isVirginity(),
                            child.getWeight(),
                            child.isRace(),
                            child.getNationality(),
                            child.getSkinTone(),
                            child.getEyeColor(),
                            child.getHairColor(),
                            child.getAvatar() != null ? child.getAvatar().getUrl() : '-'
                    )
            );
        }
        FileUtils.writeToFile(sb.toString().getBytes(), filename, Constants.EXTENSION_CSV);
    }

    /**
     * Metoda pro parsovani CSV souboru se seznamem deti
     * @param filename nazev a cesta k souboru
     * @return list deti
     */
    public static List<Child> parseCsv(String filename) {
        List<Child> childList = new ArrayList<>();
        String data = FileUtils.readFromFile(filename, Constants.EXTENSION_CSV);
        String[] lines = data.split("\n");

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);

        for (String line : lines) {
            String[] values = line.split(";");
            try {
                childList.add(new Child(
                        values[0],
                        values[1],
                        Double.parseDouble(values[2].replace(",",".")),
                        sdf.parse(values[3]),
                        GenderType.valueOf(values[5]),
                        Boolean.parseBoolean(values[6]),
                        Double.parseDouble(values[7].replace(",",".")),
                        Boolean.parseBoolean(values[8]),
                        values[9],
                        Integer.parseInt(values[10]),
                        Integer.parseInt(values[11]),
                        Integer.parseInt(values[12]),
                        values[13].equals("-") ? null : new Image(values[13]) {
                        }
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return childList;
    }
}
