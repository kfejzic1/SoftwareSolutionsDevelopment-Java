package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
public class IspitGlavnaTest {
    Stage theStage;
    GlavnaController ctrl;
    GeografijaDAO dao = GeografijaDAO.getInstance();

    @Start
    public void start (Stage stage) throws Exception {
        dao.vratiBazuNaDefault();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"));
        ctrl = new GlavnaController();
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Gradovi svijeta");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();

        stage.toFront();

        theStage = stage;
    }

    @BeforeEach
    public void resetujBazu() throws SQLException {
        dao.vratiBazuNaDefault();
    }

    @AfterEach
    public void zatvoriProzor(FxRobot robot) {
        if (robot.lookup("#btnCancel").tryQuery().isPresent())
            robot.clickOn("#btnCancel");
    }


    @Test
    public void testPostojiDugme(FxRobot robot) {
        Button izmijeniDugme = robot.lookup("#btnIzmijeniDrzavu").queryAs(Button.class);
        assertNotNull(izmijeniDugme);
        assertEquals("Izmijeni državu", izmijeniDugme.getText());
    }


    @Test
    public void testFormaPopunjena(FxRobot robot) {
        // Da li je forma za izmjenu države korektno popunjena
        robot.clickOn("Pariz");
        // Klikamo na dugme
        robot.clickOn("Izmijeni državu");
        // Čekamo da dijalog postane vidljiv
        robot.lookup("#fieldNaziv").tryQuery().isPresent();

        // Da li je forma ispravno popunjena?
        TextField fieldNaziv = robot.lookup("#fieldNaziv").queryAs(TextField.class);
        assertEquals("Francuska", fieldNaziv.getText());
        // Da li je glavni grad Pariz?
        ChoiceBox<Grad> choiceGrad = robot.lookup("#choiceGrad").queryAs(ChoiceBox.class);
        assertEquals("Pariz", choiceGrad.getValue().getNaziv());
    }



    @Test
    public void testIzmjenaDrzaveNaziv(FxRobot robot) {
        // Selektujemo grad Manchester
        robot.clickOn("Manchester");
        // Klikamo na dugme
        robot.clickOn("Izmijeni državu");
        // Čekamo da dijalog postane vidljiv
        robot.lookup("#fieldNaziv").tryQuery().isPresent();

        // Klikamo na polje za ime
        robot.clickOn("#fieldNaziv");
        // Dopisujemo aaaa na kraj imena države Velika Britanija
        robot.press(KeyCode.END).release(KeyCode.END);
        robot.write("aaaa");
        // Potvrda izmjene
        robot.clickOn("Ok");

        // Sada selektujemo grad London i editujemo državu
        robot.clickOn("London");
        robot.clickOn("Izmijeni državu");
        robot.lookup("#fieldNaziv").tryQuery().isPresent();

        // Uzimamo sadržaj polja "Naziv"
        TextField naziv = robot.lookup("#fieldNaziv").queryAs(TextField.class);
        assertEquals("Velika Britanijaaaaa", naziv.getText());

        // Dodajemo par slova b
        robot.clickOn("#fieldNaziv");
        robot.press(KeyCode.END).release(KeyCode.END);
        robot.write("bbbb");

        // Cancel dugme ne bi trebalo izmijeniti ime države
        robot.clickOn("Cancel");

        // Ponovo klikamo na Manchester
        robot.clickOn("Manchester");
        robot.clickOn("Izmijeni državu");
        robot.lookup("#fieldNaziv").tryQuery().isPresent();

        // Država je i dalje "Velika Britanijaaaaa"
        naziv = robot.lookup("#fieldNaziv").queryAs(TextField.class);
        assertEquals("Velika Britanijaaaaa", naziv.getText());


        // Provjeravamo ime države u DAO klasi
        boolean imaNova=false, imaStara=false, imaNajnovija=false;
        for(Drzava drzava : dao.drzave()) {
            if (drzava.getNaziv().equals("Velika Britanija"))
                imaStara = true;
            if (drzava.getNaziv().equals("Velika Britanijaaaaa"))
                imaNova = true;
            if (drzava.getNaziv().equals("Velika Britanijaaaaabbbb"))
                imaNajnovija = true;
        }
        assertFalse(imaStara);
        assertTrue(imaNova);
        assertFalse(imaNajnovija);
    }


    @Test
    public void testIzmjenaDrzaveGlavniGrad(FxRobot robot) {
        robot.clickOn("Manchester");
        robot.clickOn("Izmijeni državu");
        // Čekamo da dijalog postane vidljiv
        robot.lookup("#fieldNaziv").tryQuery().isPresent();

        // Sakrivamo glavni prozor da nam ne smeta
        Platform.runLater(() -> theStage.hide());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Postavljamo Manchester za glavni grad
        robot.clickOn("#choiceGrad");
        robot.clickOn("Manchester");
        // Potvrda izmjene
        robot.clickOn("Ok");

        Platform.runLater(() -> theStage.show());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Sada uzimamo grad London koji je također u Velikoj Britaniji
        robot.clickOn("London");
        robot.clickOn("Izmijeni državu");
        // Čekamo da dijalog postane vidljiv
        robot.lookup("#fieldNaziv").tryQuery().isPresent();

        Platform.runLater(() -> theStage.hide());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Da li je Manchester glavni
        ChoiceBox<Grad> choiceGrad = robot.lookup("#choiceGrad").queryAs(ChoiceBox.class);
        assertEquals("Manchester", choiceGrad.getValue().getNaziv());

        // Postavljamo London opet za glavni grad
        robot.clickOn("#choiceGrad");
        robot.clickOn("London");
        // Klikamo na Cancel, ovo se neće primijeniti
        robot.clickOn("#btnCancel");

        // Čekamo da se baza podataka ažurira
        Platform.runLater(() -> theStage.show());

        // Da li je Manchester sada glavni grad u bazi?
        Drzava velikaBritanija = dao.nadjiDrzavu("Velika Britanija");
        assertEquals("Manchester", velikaBritanija.getGlavniGrad().getNaziv());
    }
}

