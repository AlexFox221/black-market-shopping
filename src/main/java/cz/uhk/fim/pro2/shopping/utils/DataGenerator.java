package cz.uhk.fim.pro2.shopping.utils;

import cz.uhk.fim.pro2.shopping.model.Child;
import cz.uhk.fim.pro2.shopping.model.GenderType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Utils trida, ktera bude slouzit pro generovani dat
 */
public class DataGenerator {
    /**
     * Metoda pro generovani nahodneho data narozeni
     * @return nahodny datum narozeni mezi 0 a 15 lety
     */
    public static Date randomBirthdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Random random = new Random();
        int day = random.nextInt(31) + 1;
        int month = random.nextInt(12) + 1;
        int age = random.nextInt(15) + 1;
        Date date = null;

        try {
            date = sdf.parse(
                    String.format("%s.%s.%s",
                            day < 10 ? "0" + day : String.valueOf(day),
                            month < 10 ? "0" + month : String.valueOf(month),
                            (Year.now().getValue() - age)
                    )
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    // F-ce pro dostavani rand. M. s Z. jmen
    public static String randomName(String sex) {
        String[] boyNames = {"Charles", "John", "Henry", "Adolf"};
        String[] girlNames = {"Jane", "Helen", "Kate", "Eva"};
        Random random = new Random();
        if (sex.equals("M")) {
            int idx = random.nextInt(boyNames.length);
            return (boyNames[idx]);
        } else {
            int idx = random.nextInt(girlNames.length);
            return (girlNames[idx]);
        }
    }

    // F-ce pro dostavani rand. M. a Z. ceny (M. cena ma vetsi max nez Z.)
    public static Double randomPrice(String sex) {
        double min = 100;
        double max;
        if (sex.equals("M")){
            max = 400000;
        }
        else {
            max = 200000;
        }
        return Math.random()*(max-min+1)+min;
    }

    // F-ce vypoctu vahy deti (M. vaha je vetshi nez Z.)
    public static Double randomWeight(String sex, int age) {
        // Dostavam vek dite, a pak overzuju spravnost vahy
        if (sex.equals("M")) {
            if (age >= 0 && age < 3) {
                double min = 2;
                double max = 10;
                return Math.random() * (max - min + 1) + min;
            } else if (age >= 3 && age < 7) {
                double min = 10;
                double max = 24;
                return Math.random() * (max - min + 1) + min;
            } else if (age >= 7 && age < 11) {
                double min = 20;
                double max = 40;
                return Math.random() * (max - min + 1) + min;
            } else if (age >= 11 && age < 15) {
                double min = 34;
                double max = 60;
                return Math.random() * (max - min + 1) + min;
            } else if (age >= 15 && age < 18) {
                double min = 44;
                double max = 82;
                return Math.random() * (max - min + 1) + min;
            }
        }
        else { //Stejnou vec provadim pro holky, ale jejich vaha je trochu mensi
            if (age >= 0 && age < 3) {
                double min = 2;
                double max = 8;
                return Math.random() * (max - min + 1) + min;
            } else if (age >= 3 && age < 7) {
                double min = 6;
                double max = 20;
                return Math.random() * (max - min + 1) + min;
            } else if (age >= 7 && age < 11) {
                double min = 18;
                double max = 36;
                return Math.random() * (max - min + 1) + min;
            } else if (age >= 11 && age < 15) {
                double min = 28;
                double max = 54;
                return Math.random() * (max - min + 1) + min;
            } else if (age >= 15 && age < 18) {
                double min = 34;
                double max = 64;
                return Math.random() * (max - min + 1) + min;
            }
        }
        // Kdyz vek neni spravny(>=18), tak to uz neni dite
        try {
            throw new Exception("Exception message");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // F-ce dostvani nationalitaies
    public static String randomNationality() {
        String[] nationalitaies = { "Czech", "American", "Afroamerican", "German"};
        Random random = new Random();

            int idx = random.nextInt(nationalitaies.length);
        return (nationalitaies[idx]);
    }

    // F-ce dostavani rand. avataru podle pohlavi
    public static Image getRandomAvatar(String sex){
        Random random = new Random();
        int min = 1;
        int boyMax = 23;
        int girlMax = 27;
        int value;
        if (sex.equals("M")){
            value = random.nextInt((boyMax - min) + 1) + min;
            return FileUtils.loadImage("assets/image/avatar/boy-"+value+".png");
        }
        else {
            value = random.nextInt((girlMax - min) + 1) + min;
            return FileUtils.loadImage("assets/image/avatar/girl-"+value+".png");
        }
    }

    /**
     * Metoda pro vygenerovani ditete
     * @return dite
     */
    private static Child generateChild(String sex) {
        // TODO [assignment2] doplnit generovani ditete:
        //  generovana data by mela davat smysl, v zakladu zohlednit pohlavi pro spravne prirazeni obrazku (boy-#.png | girl-#.png),
        //  u vahy a ceny zohlednit nejake limity (nesmysl je u vahy mit 5kg kdyz je dite stare 12 let nebo treba 400kg apod., cena by mohla zacinat na 100,-),
        //  cena i vaha by mela byt zohlednena i u pohlavi - muzi budou vetsinou tezsi, pokud budeme sexiste tak zeny mohou mit mensi hodnotu nez muzi apo

        Random random = new Random();

        // F-ce bude generovat ruzne instance podle pohlvai dite
        if (sex.equals("M")) {
            String name = randomName("M");
            Date birthdate = randomBirthdate();
            int age =  Period.between(birthdate
             .toInstant()
             .atZone(ZoneId.systemDefault())
             .toLocalDate(), LocalDate.now()).getYears();//Musel jsem vek vypocist takhle, protoze generatedChild.getAge() nemuzu zavolat
            return new Child(
                    String.valueOf(Math.abs(name.hashCode()+random.nextInt())), //Pouzivam novou f-ci pro randomni jmeno, a pak predpocitam rand. int aby Id bylo unique
                    name,                                  //name
                    randomPrice("M"),                 //price
                    birthdate,                            //birthdate
                    GenderType.MALE,                     //sex
                    random.nextBoolean(),               //virginity
                    randomWeight("M", age),        //weight
                    random.nextBoolean(),             //race
                    randomNationality(),             //Nationality
                    0x88aef9,
                    0xaa3d98,
                    0x55fe13,
                    getRandomAvatar("M"));
        }
        else {
            String name = randomName("Z");
            Date birthdate = randomBirthdate();
            int age =  Period.between(birthdate
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate(), LocalDate.now()).getYears();//Musel jsem vek vypocist takhle, protoze generatedChild.getAge() nemuzu zavolat
            return new Child(
                    String.valueOf(Math.abs(name.hashCode()+random.nextInt())), //Pouzivam novou f-ci pro randomni jmeno, a pak predpocitam rand. int aby Id bylo unique
                    name,                                  //name
                    randomPrice("Z"),                 //price
                    birthdate,                            //birthdate
                    GenderType.FEMALE,                   //sex
                    random.nextBoolean(),               //virginity
                    randomWeight("Z", age),        //weight
                    random.nextBoolean(),             //race
                    randomNationality(),             //Nationality
                    0x88aef9,
                    0xaa3d98,
                    0x55fe13,
                    getRandomAvatar("Z"));
            }
    }

    /**
     * Metoda pro vygenerovani seznamu nabidek
     * @param n pocet generovanych nabidek
     * @return seznam nabidek
     */
    public static ObservableList<Child> generateOffers(int n) {
        // TODO [assignment2] generovani poctu nabidek na zaklade vstupniho parametru - vyuziti metody generateChild()
        ObservableList<Child> listOfChildren = FXCollections.observableArrayList(); //Zalozil jsem novy list do ktereho budu posilat instance Child
        Random random = new Random();
        for (int i=0; i<n; i++){
            if (random.nextBoolean()){
                listOfChildren.add(generateChild("M"));
            }
            else {
                listOfChildren.add(generateChild("Z"));
            }
        }
        return listOfChildren;
    }
}
