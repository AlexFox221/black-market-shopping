package cz.uhk.fim.pro2.shopping.ui;

import cz.uhk.fim.pro2.shopping.model.Child;
import cz.uhk.fim.pro2.shopping.model.GenderType;
import cz.uhk.fim.pro2.shopping.model.Marketplace;
import cz.uhk.fim.pro2.shopping.model.ShoppingCart;
import cz.uhk.fim.pro2.shopping.utils.DataGenerator;
import cz.uhk.fim.pro2.shopping.utils.FileUtils;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Label lblDisplayName;
    @FXML
    public Label lblPersonalId;
    @FXML
    public Label lblAge;
    @FXML
    public Label lblWeight;
    @FXML
    public ImageView imgSex;
    @FXML
    public Label lblSex;
    @FXML
    public Slider sldWeight;
    @FXML
    public Label lblNationality;
    @FXML
    public CheckBox checkboxTrueRace;
    @FXML
    public ImageView imgVirginity;
    @FXML
    public Label lblVirginity;
    @FXML
    public DatePicker dtBirthDate;
    @FXML
    public Label lblPrice;
    @FXML
    public TextArea txtDescription;
    @FXML
    private ImageView imgChildProfile;

    @FXML
    public TableView<Child> offerTable;
    @FXML
    public TableColumn<Child, String> colId;
    @FXML
    public TableColumn<Child, String> colName;
    @FXML
    public TableColumn<Child, Integer> colAge;
    @FXML
    public TableColumn<Child, GenderType> colGender;
    @FXML
    public TableColumn<Child, Double> colPrice;
    @FXML
    public Tab tabDetail;




    // TODO [assignment2] pridat/provazat komponentu pro obrazek ditete v karte detailu
    //  *tip* pozor na spravny import u ImageView
    // TODO [assignment2] pridat/provazat komponentu pro obrazek pohlavi v karte detailu
    // TODO [assignment2] pridat/provazat komponentu pro obrazek atributu virginity v karte detailu (id v ui: imgVirginity)

    // reference na nakupni kosik
    private ShoppingCart cart;
    // reference na marketplace
    private Marketplace marketplace;
    // reference na aktualni zobrazene dite
    private Child currentChild;

    /**
     * OnClick Listener tlacitka "Pridat do kosiku"
     */
    public void addChildToShoppingCart() {
        System.out.println(this.offerTable.getSelectionModel().getSelectedItem());
        // TODO [assignment_final] pridani aktualniho vyberu do kosiku
        //  - pri pridani prvku do kosiku aktualizuji hodnotu "budgetu" v UI
    }

    /**
     * OnClick Listener tlacitka "Nacteni marketu"
     */
    public void loadMarketplace() {
        for (Child child : this.marketplace.getOfferList()) {
            System.out.println(child);
        }
        // TODO [assignment_final] pregenerovani vsech nabidek (nove nabidky)
    }

    /**
     * OnClick Listener tlacitka "Ulozeni nabidky"
     */
    public void saveOffers() {
        System.out.println(this.currentChild);
        // TODO [assignment_final] ulozeni aktualnich nabidek do souboru (vyberte si nazev souboru a format jaky uznate za vhodny - CSV nebo JSON)
    }

    /**
     * OnClick Listener tlacitka "Vymazani filtru"
     */
    public void clearFilters() {
        // TODO [assignment_final] vycisteni aktualne nastavenych filtru
    }

    /**
     * Inicializacni metoda pro tabulku nabidek
     */
    private void initTableView() {
        // nastaveni sloupcu pro zobrazeni spravne hodnoty a korektniho datoveho typu
        this.colId.setCellValueFactory(cellData -> cellData.getValue().getPersonalIdProperty());
        this.colName.setCellValueFactory(cellData -> cellData.getValue().getDisplayNameProperty());
        this.colAge.setCellValueFactory(cellData -> cellData.getValue().getAgeProperty().asObject());
        this.colGender.setCellValueFactory(cellData -> cellData.getValue().getGenderProperty());
        this.colPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());

        // nastaveni listu prvku tabulce
        this.offerTable.setItems(this.marketplace.getOfferList());

        // listener pro zjisteni, ktery prvek tabulky uzivatel oznacil a naplneni aktualni reference na vybrane dite
        this.offerTable.getSelectionModel().getSelectedCells().addListener(new ListChangeListener<TablePosition>() {

            @Override
            public void onChanged(Change<? extends TablePosition> change) {
                ObservableList selectedCells = offerTable.getSelectionModel().getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                currentChild = marketplace.getOfferList().get(tablePosition.getRow());
                System.out.println(currentChild);
                updateUi();
            }
        });
    }

    private void initMarketplace() {
        this.marketplace = new Marketplace();
    }


    // TODO [assignment_final] pridejte implementaci filtru (propojte s metodou 'filter' v tride Marketplace)
    //  idealni chovani - jakmile zadam nejake hodnoty do UI s libovolnym filtrem, automaticky se mi aktualizuje nabidka v tabulce
    //  dostacujici chovani - pridam si do UI tlacitko pro "vyhledani vysledku dle fitru" (nazev necham na vas), ktere vyvola aktualizaci nabidky v tabulce

    private void updateUi() {
        this.tabDetail.setDisable(false);
        this.lblDisplayName.setText(this.currentChild.getDisplayName());
        this.lblPersonalId.setText(String.format("(%s)", this.currentChild.getPersonalId()));
        this.lblPrice.setText(String.format("%.2f", this.currentChild.getPrice()));
        this.lblAge.setText(String.valueOf(this.currentChild.getAge()));
        this.dtBirthDate.setValue(this.currentChild
                .getBirthDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        this.lblWeight.setText(String.format("%.1f kg", this.currentChild.getWeight()));
        this.sldWeight.setValue(this.currentChild.getWeight());
        this.checkboxTrueRace.setSelected(!this.currentChild.isRace());
        this.imgChildProfile.setImage(this.currentChild.getAvatar());
        if (this.currentChild.getGender().equals(GenderType.MALE)){
            this.imgSex.setImage(FileUtils.loadImage("assets/image/gender/boy.png"));
            this.lblSex.setText("Male");
        }
        else {
            this.imgSex.setImage(FileUtils.loadImage("assets/image/gender/girl.png"));
            this.lblSex.setText("Female");
        }
        if (this.currentChild.isVirginity()){
        this.imgVirginity.setImage(FileUtils.loadImage("assets/image/virginity/virgin.png"));
        this.lblVirginity.setText("Virgin");
        }
        else {
            this.imgVirginity.setImage(FileUtils.loadImage("assets/image/virginity/not-virgin.png"));
            this.lblVirginity.setText("Not Virgin");
        }

        // TODO [assignment2] nastavit obrazek/avatar ditete v karte detailu
        // TODO [assignment2] nastavit spravny obrazek pohlavi v karte detailu
        // TODO [assignment2] nastavit spravny obrazek virginity atribut v v karte detailu
    }

    private void initUi() {
        initTableView();
        tabDetail.setDisable(true);
        sldWeight.setDisable(true);
        checkboxTrueRace.setDisable(true);
        dtBirthDate.setDisable(true);
    }

    private void initCart() {
        this.cart = new ShoppingCart();
        this.cart.setVat(0.21);
    }

    // TODO [assignment_final] implementace UI kosiku:
    //  UI bude obsahovat:
    //  - tabulku se vsemi prvky v kosiku (v tabulce budou vsechny atributy, ktere),
    //  - celkovy pocet prvku v kosiku,
    //  - celkovy soucet (bez DPH a s DPH),
    //  - ovladaci prvky pro: odebrani vybraneho prvku z kosiku (po odebrani se prvek vrati zpet do nabidek) a dokonceni objednavky (pokud bude celkovy soucet s DPH vetsi nez dostupny budget, nedovolte dokoncit objednavku)
    //  Dokoncena objednavka se ulozi do souboru, kosik se vyprazdni, zakaznikovi se odecte castka od jeho budgetu.
    //      Soubor s dokoncenou objednavkou: nazev si vyberte, format CSV
    //      - do toho stejneho souboru uvedte (v uvodu) informace o objednavce - id a username zakaznika, celkovy soucet bez a s DPH + dalsimi poplatky

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCart();
        initMarketplace();
        initUi();
        // TODO [assignment_final] inicializujte zakaznika (bud manualne nebo pomoci nejakeho dialogu), nastavte mu nejake vychozi hodnoty - id, username, heslo, kosik, adresu a budget
    }
}
