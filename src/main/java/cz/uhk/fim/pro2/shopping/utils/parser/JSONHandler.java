package cz.uhk.fim.pro2.shopping.utils.parser;

import com.google.gson.Gson;
import cz.uhk.fim.pro2.shopping.model.Child;
import cz.uhk.fim.pro2.shopping.utils.Constants;
import cz.uhk.fim.pro2.shopping.utils.FileUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Utility trida pro praci s CSV soubory
 */
public class JSONHandler {

    /**
     * Metoda pro vygenerovani JSON formatu
     * @param objects list deti, ktere chceme zapsat do retezce
     */
    public static void generateJson(String filename, List<Child> objects) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        Gson gson = new Gson();
        // vytvoreni retezce v JSON formatu z predaneho/ych objektu
        String jsonData = gson.toJson(objects);
        FileUtils.writeToFile(jsonData.getBytes(), filename, Constants.EXTENSION_JSON);
    }

    /**
     * Metoda pro parsovani JSON souboru se seznamem deti
     * @param filename nazev a cesta k souboru
     * @return list deti
     */
    public static List<Child> parseJson(String filename) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        Gson gson = new Gson();
        // nacteni dat z JSON souboru
        String jsonData = FileUtils.readFromFile(filename, Constants.EXTENSION_JSON);
        // rozparsovani objektu z JSON retezce do pole deti
        //   - podle toho, jaka data chci rozparsovat (jaky objekt), podle toho musi odpovidat i 2. argument metody fromJson()
        Child[] childList = gson.fromJson(jsonData, Child[].class);
        return List.of(childList);
    }
}
