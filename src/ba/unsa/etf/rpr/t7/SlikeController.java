package ba.unsa.etf.rpr.t7;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchFeed;
import at.mukprojects.giphy4j.exception.GiphyException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;


public class SlikeController {
    KorisniciModel model = KorisniciModel.getInstance();
    public Button btnCancel;
    public Button btnOk;
    public TextField fldPretraga = null;
    public Button btnOdabranaSlika = null;
    public TilePane tilePaneSlike;
    public ScrollPane scrollPane;

    private String odabranaSlikaURL = "resources/img/face-smile.png";

    final private String apiKey="ZbscMMjQmLP76J2zsaqKzGKKHiM7vKUO";

    public SlikeController(KorisniciModel model) {
        this.model = model;
    }

    public void cancelSlikeAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private ImageView dajImageView() {
        File file = new File("resources/img/loading.gif");
        return new ImageView(new Image(file.toURI().toString()));
    }

    public void searchSlikeAction(ActionEvent actionEvent) {
        ImageView image = dajImageView();

        tilePaneSlike.getChildren().clear();
        new Thread(() -> {
            Giphy giphy = new Giphy(apiKey);
            Platform.runLater(() -> tilePaneSlike.getChildren().add(image));
            AtomicReference<SearchFeed> feed = new AtomicReference<>(new SearchFeed());
            try {
                feed.set(giphy.search(fldPretraga.getText(), 25, 0));
            } catch (GiphyException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < feed.get().getDataList().size(); i++) {
                Platform.runLater(() -> {
                    if(!tilePaneSlike.getChildren().contains(image))
                        tilePaneSlike.getChildren().add(image);
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String imageURL = "https://i.giphy.com/media/" + feed.get().getDataList().get(i).getId() + "/giphy_s.gif";
                Button button = new Button();
                button.setMaxHeight(135);
                button.setMaxWidth(135);
                button.setMinHeight(135);
                button.setMaxWidth(135);
                ImageView view = new ImageView(new Image(imageURL, true));
                view.setFitHeight(128);
                view.setFitWidth(128);
                view.setPreserveRatio(false);
                button.setGraphic(view);
                button.setOnAction(actionEvent1 -> {
                    btnOdabranaSlika = button;
                    odabranaSlikaURL = imageURL;
                });
                Platform.runLater(() -> {
                    tilePaneSlike.getChildren().remove(image);
                    tilePaneSlike.getChildren().add(button);
                });
            }
        }).start();
    }

    public void okSlikeAction(ActionEvent actionEvent){
        //Ako nije selektovana slika dobijamo upozorenje
        if(btnOdabranaSlika == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nijedna slika nije izabrana");
            alert.setHeaderText("Niste izabrali sliku koju zelite");
            alert.setContentText("Unesite a zatim izaberite sliku ili klinite na cancel");
            alert.showAndWait();
        } else {
            Korisnik k = model.getTrenutniKorisnik();
            k.setSlika(odabranaSlikaURL);
            model.izmijeniKorisnika();
            Stage stage = (Stage) btnOk.getScene().getWindow();
            stage.close();
        }
    }
}
