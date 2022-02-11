package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class IspitGlavnaZadatak2Test {
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
    public void testDodajDrzavuNajveciGrad(FxRobot robot) {
        Grad london = dao.nadjiGrad("London");
        Grad pariz = dao.nadjiGrad("Pariz");

        // Otvaranje forme za dodavanje
        robot.clickOn("#btnDodajDrzavu");

        // Čekamo da dijalog postane vidljiv
        robot.lookup("#fieldNaziv").tryQuery().isPresent();

        // Sakrivamo glavni prozor da nam ne smeta
        Platform.runLater(() -> theStage.hide());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Unosimo podatke
        robot.clickOn("#fieldNaziv");
        robot.write("Bosna i Hercegovina");
        robot.clickOn("#choiceGrad");
        robot.clickOn("Pariz");
        robot.clickOn("#choiceNajveci");
        robot.clickOn("London");

        // Klik na dugme Ok
        robot.clickOn("#btnOk");

        // Vraćamo glavni prozor
        Platform.runLater(() -> theStage.show());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Da li je BiH dodana u bazu?
        GeografijaDAO dao = GeografijaDAO.getInstance();
        assertEquals(4, dao.drzave().size());

        assertNotNull(dao.nadjiDrzavu("Bosna i Hercegovina"));

        Drzava bih = null;
        for(Drzava drzava : dao.drzave())
            if (drzava.getNaziv().equals("Bosna i Hercegovina")) {
                bih = drzava;
                break;
            }
        assertEquals("Bosna i Hercegovina", bih.getNaziv());
        assertEquals("Pariz", bih.getGlavniGrad().getNaziv());
        assertEquals(pariz.getId(), bih.getGlavniGrad().getId());
        assertEquals("London", bih.getNajveciGrad().getNaziv());
        assertEquals(london.getId(), bih.getNajveciGrad().getId());
    }


    @Test
    public void testIzmijeniDrzavuNajveciGrad(FxRobot robot) {
        Grad graz = dao.nadjiGrad("Graz");

        // Mijenjamo državu za grad Beč
        robot.clickOn("Beč");
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

        // Trebalo bi da je Beč glavni i najveći grad
        ChoiceBox<Grad> choiceNajveci = robot.lookup("#choiceNajveci").queryAs(ChoiceBox.class);
        assertEquals("Beč", choiceNajveci.getValue().getNaziv());

        // Postavljamo Graz za najveći grad
        robot.clickOn("#choiceNajveci");
        robot.clickOn("Graz");

        // Klik na dugme Ok
        robot.clickOn("#btnOk");

        // Vraćamo glavni prozor
        Platform.runLater(() -> theStage.show());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Da li je najveći grad promijenjen u bazi?
        Drzava austrija = dao.nadjiDrzavu("Austrija");
        assertEquals("Graz", austrija.getNajveciGrad().getNaziv());
        assertEquals(graz.getId(), austrija.getNajveciGrad().getId());
    }


    @Test
    public void testIzmijeniDrzavu2NajveciGrad(FxRobot robot) {
        Grad graz = dao.nadjiGrad("Graz");

        // Mijenjamo državu za grad Beč
        robot.clickOn("Beč");
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

        // Trebalo bi da je Beč glavni i najveći grad
        ChoiceBox<Grad> choiceNajveci = robot.lookup("#choiceNajveci").queryAs(ChoiceBox.class);
        assertEquals("Beč", choiceNajveci.getValue().getNaziv());

        // Postavljamo Graz za najveći grad
        robot.clickOn("#choiceNajveci");
        robot.clickOn("Graz");

        // Klik na dugme Ok
        robot.clickOn("#btnOk");

        // Vraćamo glavni prozor
        Platform.runLater(() -> theStage.show());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Sada klikamo na Graz
        robot.clickOn("Graz");
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

        // Da li je Graz selektovan?
        choiceNajveci = robot.lookup("#choiceNajveci").queryAs(ChoiceBox.class);
        assertEquals("Graz", choiceNajveci.getValue().getNaziv());

        // Selektujemo Manchester
        // Postavljamo Graz za najveći grad
        robot.clickOn("#choiceNajveci");
        robot.clickOn("Manchester");

        // Klik na dugme Cancel
        robot.clickOn("#btnCancel");


        // Vraćamo glavni prozor
        Platform.runLater(() -> theStage.show());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Opet klikamo na Beč
        robot.clickOn("Beč");
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

        // Graz je i dalje najveći jer smo kliknuli na Cancel
        choiceNajveci = robot.lookup("#choiceNajveci").queryAs(ChoiceBox.class);
        assertEquals("Graz", choiceNajveci.getValue().getNaziv());
    }
}
