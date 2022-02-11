package ba.unsa.etf.rpr;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class IspitDrzavaControllerSetTest {
    Stage theStage;
    DrzavaController ctrl;
    Drzava globalnaDrzava;
    GeografijaDAO dao;

    @Start
    public void start (Stage stage) throws Exception {
        dao = GeografijaDAO.getInstance();
        dao.vratiBazuNaDefault();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/drzava.fxml"));

        Drzava d = new Drzava(100, "Irska", dao.nadjiGrad("London"), dao.nadjiGrad("Manchester"));
        globalnaDrzava = d;

        ctrl = new DrzavaController(d, dao.gradovi());
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
    public void testIspravneVrijednosti(FxRobot robot) {
        // Da li su popunjene odgovarajuće vrijednosti
        TextField fieldNaziv = robot.lookup("#fieldNaziv").queryAs(TextField.class);
        assertEquals("Irska", fieldNaziv.getText());
        ChoiceBox<Grad> choiceGrad = robot.lookup("#choiceGrad").queryAs(ChoiceBox.class);
        assertEquals("London", choiceGrad.getValue().getNaziv());
        ChoiceBox<Grad> choiceNajveci = robot.lookup("#choiceNajveci").queryAs(ChoiceBox.class);
        assertEquals("Manchester", choiceNajveci.getValue().getNaziv());
    }

    @Test
    public void testPromjenaNajveceg(FxRobot robot) {
        Grad bech = dao.nadjiGrad("Beč");
        Grad pariz = dao.nadjiGrad("Pariz");

        robot.clickOn("#choiceGrad");
        robot.clickOn("Pariz");
        robot.clickOn("#choiceNajveci");
        robot.clickOn("Beč");

        // Klik na dugme ok
        robot.clickOn("#btnOk");

        Drzava bih = ctrl.getDrzava();
        assertEquals("Beč", bih.getNajveciGrad().getNaziv());
        assertEquals(bech.getId(), bih.getNajveciGrad().getId());
        assertEquals("Pariz", bih.getGlavniGrad().getNaziv());
        assertEquals(pariz.getId(), bih.getGlavniGrad().getId());
    }


}