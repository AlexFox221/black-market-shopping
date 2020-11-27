package cz.uhk.fim.pro2.shopping.model;

import cz.uhk.fim.pro2.shopping.utils.DataGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class Marketplace {
    private ObservableList<Child> offerList;

    public Marketplace() {
        this.offerList = FXCollections.observableArrayList();
        this.offerList.addAll(DataGenerator.generateOffers(8));

//        // cast kodu pro vygenerovani CSV souboru s detmi
//        CSVHandler.generateCsv("children", this.offerList);
//        // cast kodu pro rozparsovani CSV souboru a vypis deti do konzole
//        for (Child child : CSVHandler.parseCsv("children")) {
//            System.out.println(child);
//        }
//
//        // cast kodu pro vygenerovani JSON souboru s detmi
//        JSONHandler.generateJson("children", this.offerList);
//        // cast kodu pro rozparsovani JSON souboru a vypis deti do konzole
//        for (Child child : JSONHandler.parseJson("children")) {
//            System.out.println(child);
//        }

//        // priklad pouziti metody filter
//        int minAge = 0;
//        int maxAge = 14;
//        double minPrice = 0;
//        double maxPrice = 2000;
//        GenderType gender = GenderType.MALE;
//
//        filter(minAge, maxAge, minPrice, maxPrice, gender);

    }

    /**
     * Metoda pro filtrovani zbozi dle parametru
     * @param minPrice minimalni cena
     * @param maxPrice maximalni cena
     * @param minAge minimalni vek
     * @param maxAge maximalni vek
     * @param gender pohlavi
     */
    public void filter(int minAge, int maxAge, double minPrice, double maxPrice, GenderType gender) {
        List<Child> filteredList = this.offerList
                .stream()
                .filter(child ->
                        child.getAge() >= minAge &&
                        child.getAge() <= maxAge &&
                        child.getPrice() >= minPrice &&
                        child.getPrice() <= maxPrice &&
                        child.getGender().equals(gender)
                )
                .collect(Collectors.toList());

        System.out.println("=======");
        for (Child c : filteredList) {
            System.out.println(c);
        }
        System.out.println("=======");
    }

    /**
     * Metoda pridat nabidku zpet do listu po odebrani z kosiku
     * @param child dite
     */
    public void addOffer(Child child) {
        // TODO [assignment_final] pridani nabidky do listu
    }

    /**
     * Metoda odebere nabidku z listu po pridani do kosiku podle indexu
     * @param index index nabidky
     */
    public void removeOffer(int index) {
        // TODO [assignment_final] odebrani prvku z listu dle indexu
    }

    /**
     * Metoda odebere nabidku z listu po pridani do kosiku podle reference
     * @param child reference na konkretni nabidku
     */
    public void removeOffer(Child child) {
        // TODO [assignment_final] odebrani prvku z listu dle reference
    }

    /**
     * Metoda pro vraceni konkretni nabidky
     * @param index index v listu
     * @return nabidka/dite
     */
    public Child getOfferDetail(int index) {
        // TODO [assignment_final] vraceni vybrane nabidky podle indexu
        return null;
    }

    public ObservableList<Child> getOfferList() {
        return offerList;
    }

    public void setOfferList(ObservableList<Child> offerList) {
        this.offerList = offerList;
    }
}
