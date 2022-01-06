package ba.unsa.etf.rpr.t7;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

public class KorisniciModel {
    private static KorisniciModel instance = null;
    private ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
    private SimpleObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty<>();

    private Connection conn;
    private PreparedStatement izmjenaUpit, dodajUpit, obrisiUpit, dajNoviId;

    public KorisniciModel() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:korisnici.db");
            izmjenaUpit = conn.prepareStatement("UPDATE korisnik SET Ime=?, Prezime=?, Email=?, Username=?, Password=?, slika=? WHERE Id=?");
            dodajUpit = conn.prepareStatement("INSERT INTO korisnik VALUES (?,?,?,?,?,?,?)");
            dajNoviId = conn.prepareStatement("SELECT MAX(Id)+1 FROM korisnik");
            obrisiUpit = conn.prepareStatement("DELETE FROM korisnik WHERE Id=?");
        } catch(SQLException e) {
            regenerisiBazu();
            try {
                izmjenaUpit = conn.prepareStatement("UPDATE korisnik SET Ime=?, Prezime=?, Email=?, Username=?, Password=?, slika=? WHERE Id=?");
                dodajUpit = conn.prepareStatement("INSERT INTO korisnik VALUES (?,?,?,?,?,?,?)");
                dajNoviId = conn.prepareStatement("SELECT MAX(Id)+1 FROM korisnik");
                obrisiUpit = conn.prepareStatement("DELETE FROM korisnik WHERE Id=?");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (trenutniKorisnik == null) trenutniKorisnik = new SimpleObjectProperty<>();
    }

    public static KorisniciModel getInstance() {
        if (instance == null) instance = new KorisniciModel();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null) {
            try {
                instance.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("korisnik.sql"));
            String sqlUpit = "";
            while(ulaz.hasNext()){
                sqlUpit+=ulaz.nextLine();
                if(sqlUpit.length()>1 && sqlUpit.charAt(sqlUpit.length()-1)==';'){
                    try {
                        Statement stmt=conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ne postoji SQL datoteka, nastavljam sa praznom bazom");
            e.printStackTrace();
        }
    }

    public void napuni() {
        // Ako je lista već bila napunjena, praznimo je
        // Na taj način se metoda napuni() može pozivati više puta u testovima

        korisnici.clear();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM korisnik");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Korisnik k = new Korisnik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                korisnici.add(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        trenutniKorisnik.set(null);
    }

    public void vratiNaDefault() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM korisnik");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        regenerisiBazu();
    }

    public void diskonektuj() {
        try {
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
        if(this.getTrenutniKorisnik() != null)
            izmijeniKorisnika();
        this.trenutniKorisnik.set(trenutniKorisnik);
    }

    public void setTrenutniKorisnik(int i) {
        if(this.getTrenutniKorisnik() != null)
            izmijeniKorisnika();
        this.trenutniKorisnik.set(korisnici.get(i));
    }

    public void izmijeniKorisnika() {
        try {
            izmjenaUpit.setString(1, this.getTrenutniKorisnik().getIme());
            izmjenaUpit.setString(2, this.getTrenutniKorisnik().getPrezime());
            izmjenaUpit.setString(3, this.getTrenutniKorisnik().getEmail());
            izmjenaUpit.setString(4, this.getTrenutniKorisnik().getUsername());
            izmjenaUpit.setString(5, this.getTrenutniKorisnik().getPassword());
            izmjenaUpit.setString(6, this.getTrenutniKorisnik().getSlika());
            izmjenaUpit.setInt(7, this.getTrenutniKorisnik().getId());
            izmjenaUpit.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dodajKorisnika() {
        try {
            dodajUpit.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void obrisiKorisnika() {
        try {
            obrisiUpit.setInt(1, this.getTrenutniKorisnik().getId());
            obrisiUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void zapisiDatoteku(File file) {
        if(file == null)
            return;
        String content = "";

        for (Korisnik korisnik : korisnici) {
            content += korisnik.noviToString();
        }

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}