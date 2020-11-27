package cz.uhk.fim.pro2.shopping;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hlavni trida aplikace
 * - pro pouziti JavaFX je potreba dedit z tridy Application
 */
public class App extends Application {

    /**
     * Hlavni spousteci metoda programu
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metoda, ktera nacita UI z FXML souboru, nastavuje hlavni scenu obrazovky a zobrazuje ji
     * + je spoustena metodou "launch(args)" v metode main
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
