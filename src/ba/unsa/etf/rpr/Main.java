package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
//        StudentiModel model = new StudentiModel();
//        model.napuni();
//
//        KontrolerForma ctrl = new KontrolerForma(model);
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/zadaca3.fxml"));
//        loader.setController(ctrl);
//        Parent root = loader.load();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/zadaca3.fxml"));

        primaryStage.setTitle("Studenti");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        primaryStage.show();

        primaryStage.setMinWidth(590);
        primaryStage.setMinHeight(450);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
