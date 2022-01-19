package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class Main extends Application {
    private static GeografijaDAO data = GeografijaDAO.getInstance();
    public static Locale defaultJezik = new Locale("bs");
    public static ResourceBundle bundle = ResourceBundle.getBundle("Translation", defaultJezik);

    public static String ispisiGradove() {
        ArrayList<Grad> gradovi = GeografijaDAO.getInstance().gradovi();
        String rezultat = "";
        for (int i = 0; i < gradovi.size(); i++) {
            Grad g = gradovi.get(i);
            rezultat = rezultat + g.getNaziv() + " (";
            if (g.getDrzava() != null) {
                rezultat = rezultat + g.getDrzava().getNaziv();
            } else {
                rezultat = rezultat + "nema drzave";
            }
            rezultat = rezultat + ") - " + g.getBrojStanovnika() + "\n";
        }

        return rezultat;
    }

    public static void glavniGrad() {
        System.out.println("Unesite ime drzave: ");
        Scanner ulaz = new Scanner(System.in);
        String drzava = ulaz.next();
        Grad grad = GeografijaDAO.getInstance().glavniGrad(drzava);

        if(grad == null) {
            System.out.println("Nepostojeća država");
            return;
        }

        System.out.println("Glavni grad države " + drzava + " je " + grad.getNaziv() + ".");
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        GlavnaController ctrl = new GlavnaController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"), bundle);
        loader.setController(ctrl);
        Parent root = loader.load();

        primaryStage.setTitle(bundle.getString("titleApp"));
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        primaryStage.show();
        primaryStage.setMinWidth(550);
        primaryStage.setMinHeight(500);
    }

    public static void main(String[] args) {
        //System.out.println("Gradovi su:\n" + ispisiGradove());
        launch(args);
    }
}
