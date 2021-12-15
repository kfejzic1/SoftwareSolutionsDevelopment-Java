package ba.unsa.etf.rpr.tutorijal06;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    boolean daLiJeDoubleInteger(Double broj){
        return (broj == Math.floor(broj)) && !Double.isInfinite(broj);
    }

    @FXML
    private Label display;
    private boolean start = true;   //Postaje true ako je kliknuta bilo koja tipka
    private boolean decimalKlik = false;    //Postaje true ako je kliknut decimalni zarez
    private String operacija;
    private Double prviBroj;

    @FXML
    public void dajBroj(ActionEvent actionEvent) {
        if(start){
            display.setText("");
            start = false;
        }

        String broj = ((Button)actionEvent.getSource()).getText();

        if(display.getText().equals("0") && broj.equals("0")){
            start = false;
            return;
        }

        //Ako se unese broj razlicit od 0, a 0 je na ekranu, potrebno je obrisati ekran
        if(!broj.equals("0") && display.getText().equals("0"))
            display.setText("");

        display.setText(display.getText()+broj);
    }

    @FXML
    public void decimalnaTacka(ActionEvent actionEvent) {
        if(!decimalKlik && !start){
            String decimalniDioBroja = ((Button)actionEvent.getSource()).getText();
            display.setText(display.getText()+decimalniDioBroja);
            decimalKlik = true;
        }
    }

    @FXML
    public void dajOperaciju(ActionEvent actionEvent) {
        operacija = ((Button)actionEvent.getSource()).getText();
        prviBroj = Double.parseDouble(display.getText());
        display.setText("0");
        decimalKlik = false;
        start = true;
    }

    @FXML
    public void znakJednakosti(ActionEvent actionEvent) {
        Double drugiBroj = Double.parseDouble(display.getText());
        double rezultat = 0.;

        switch (operacija){
            case "+":
                rezultat = prviBroj+drugiBroj;
                break;
            case "-":
                rezultat = prviBroj-drugiBroj;
                break;
            case "x":
                rezultat = prviBroj*drugiBroj;
                break;
            case "/":
                rezultat = prviBroj/drugiBroj;
                break;
            case "%":
                if(daLiJeDoubleInteger(prviBroj) && daLiJeDoubleInteger(drugiBroj)){
                    rezultat = (double) (prviBroj.intValue() % drugiBroj.intValue());
                    display.setText(Integer.toString((int) rezultat));
                }else
                    display.setText("Nemoguće");
                break;
            default:
        }
        start = true;
        decimalKlik = false;

        if(!operacija.equals("%")){
            display.setText(Double.toString(rezultat));
        }
    }
}
