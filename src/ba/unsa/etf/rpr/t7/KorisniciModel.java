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

    private Connection conn;
    private PreparedStatement stmt, izmjenaUpit;

    public KorisniciModel() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:korisnici.db");
            izmjenaUpit = conn.prepareStatement("UPDATE korisnik SET Ime=?, Prezime=?, Email=?, Username=?, Password=? WHERE Id=?");
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
                        Statement st = conn.createStatement();
                        st.execute(sqlUpit);
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
            stmt = conn.prepareStatement("SELECT * FROM korisnik");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Korisnik k = new Korisnik(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                k.setId(rs.getInt(1));
                korisnici.add(k);
                if (trenutniKorisnik == null) trenutniKorisnik = new SimpleObjectProperty<>(k);
            }
        } catch(SQLException e) {
            regenerisiBazu();
            try {
                stmt = conn.prepareStatement("SELECT * FROM korisnik");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Korisnik k = new Korisnik(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                    k.setId(rs.getInt(1));
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
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM korisnik");
            regenerisiBazu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void diskonektuj() {
        //Potrebno update baze prije zatvaranja
        try {
            for(Korisnik k : korisnici)
                izmijeniKorisnika(k);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void izmijeniKorisnika(Korisnik korisnik) {
        try {
            izmjenaUpit.setString(1, korisnik.getIme());
            izmjenaUpit.setString(2, korisnik.getPrezime());
            izmjenaUpit.setString(3, korisnik.getEmail());
            izmjenaUpit.setString(4, korisnik.getUsername());
            izmjenaUpit.setString(5, korisnik.getPassword());
            izmjenaUpit.setInt(6, korisnik.getId());
            izmjenaUpit.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
