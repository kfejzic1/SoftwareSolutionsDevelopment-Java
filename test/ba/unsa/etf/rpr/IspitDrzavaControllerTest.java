/*package ba.unsa.etf.rpr;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class IspitDrzavaControllerTest {
    Stage theStage;
    DrzavaController ctrl;
    GeografijaDAO dao;

    @Start
    public void start (Stage stage) throws Exception {
        dao = GeografijaDAO.getInstance();
        dao.vratiBazuNaDefault();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/drzava.fxml"));
        ctrl = new DrzavaController(null, dao.gradovi());
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Država");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
        theStage = stage;
    }

    @Test
    public void testPoljaPostoje(FxRobot robot) {
        ChoiceBox choiceNajveci = robot.lookup("#choiceNajveci").queryAs(ChoiceBox.class);
        assertNotNull(choiceNajveci);
    }

    @Test
    public void testVracanjeDrzave(FxRobot robot) {
        Grad london = dao.nadjiGrad("London");

        // Upisujemo državu
        robot.clickOn("#fieldNaziv");
        robot.write("Bosna i Hercegovina");
        robot.clickOn("#choiceGrad");
        robot.clickOn("Pariz");
        robot.clickOn("#choiceNajveci");
        robot.clickOn("London");

        // Klik na dugme ok
        robot.clickOn("#btnOk");

        Drzava bih = ctrl.getDrzava();
        assertEquals("London", bih.getNajveciGrad().getNaziv());
        assertEquals(london.getId(), bih.getNajveciGrad().getId());
    }

    @Test
    public void testDefaultGrad(FxRobot robot) {
        Grad bech = dao.nadjiGrad("Beč");

        // Ako nije izabran najveći grad, po defaultu glavni grad je najveći
        robot.clickOn("#fieldNaziv");
        robot.write("Bosna i Hercegovina");
        robot.clickOn("#choiceGrad");
        robot.clickOn("Beč");

        // Klik na dugme ok
        robot.clickOn("#btnOk");

        Drzava bih = ctrl.getDrzava();
        assertEquals("Beč", bih.getNajveciGrad().getNaziv());
        assertEquals(bech.getId(), bih.getNajveciGrad().getId());
        assertEquals("Beč", bih.getGlavniGrad().getNaziv());
        assertEquals(bech.getId(), bih.getGlavniGrad().getId());
    }
}*/