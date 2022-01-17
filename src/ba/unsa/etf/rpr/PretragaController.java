package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;


public class PretragaController {
    @FXML
    TextField txtField;
    @FXML
    ListView<String> listViewFile;

    public void searchFile(String path, String parameter) {
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) return;

        for (File f : list) {
            if(f.isDirectory())
                searchFile(f.getAbsolutePath(), parameter);
            else {
                if(f.getAbsolutePath().contains(parameter)) {
                    Platform.runLater(() -> listViewFile.getItems().add(f.getAbsolutePath()));
                }
            }
        }
    }

    public void btnSearchAction (ActionEvent actionEvent) {
        String parametar = txtField.getText();
        new Thread(() -> {
            searchFile("c:\\\\", parametar);
        }).start();
    }
}
