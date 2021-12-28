package ba.unsa.etf.rpr.t7;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class KorisniciModel {
    private ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
    private SimpleObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty<>();

    private static KorisniciModel instanca = null;  //možda nije potrebno

    private Connection conn;
    private PreparedStatement stmt;

    public KorisniciModel() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:korisnici.db");
        } catch(SQLException e) {
            System.out.println("Neuspješno čitanje iz baze: " + e.getMessage());
        }
        if (trenutniKorisnik == null) trenutniKorisnik = new SimpleObjectProperty<>();
    }

    public void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("korisnik.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.length() > 1 && sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ne postoji SQL datoteka... nastavljam sa praznom bazom");
        }
    }

    public void napuni() {
        // Ako je lista već bila napunjena, praznimo je
        // Na taj način se metoda napuni() može pozivati više puta u testovima
        korisnici.clear();

        try {
            stmt = conn.prepareStatement("SELECT Ime, Prezime, Email, Username, Password FROM korisnik");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Korisnik k = new Korisnik(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                korisnici.add(k);
                if (trenutniKorisnik == null) trenutniKorisnik = new SimpleObjectProperty<>(k);
            }
        } catch(SQLException e) {
            regenerisiBazu();
            try {
                stmt = conn.prepareStatement("SELECT Ime, Prezime, Email, Username, Password FROM korisnik");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Korisnik k = new Korisnik(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    korisnici.add(k);
                    if (trenutniKorisnik == null) trenutniKorisnik = new SimpleObjectProperty<>(k);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (trenutniKorisnik == null) trenutniKorisnik = new SimpleObjectProperty<>();
    }

    public void vratiNaDefault() {
        // Dodali smo metodu vratiNaDefault koja trenutno ne radi ništa, a kada prebacite Model na DAO onda
        // implementirajte ovu metodu
        // Razlog za ovo je da polazni testovi ne bi padali nakon što dodate bazu
    }

    public void diskonektuj() {

    }

    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
    }

    public void setTrenutniKorisnik(int i) {
        this.trenutniKorisnik.set(korisnici.get(i));
    }
}
