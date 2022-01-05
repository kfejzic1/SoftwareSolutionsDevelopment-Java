package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private Connection conn;
    private PreparedStatement dajSveGradove, dajDrzavu, dajGrad, dajGlavniGradDrzave, obrisiDrzavu, dajIdDrzave, obrisiGradoveIzDrzave;

    public void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("baza.sql"));
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

    public GeografijaDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            dajSveGradove = conn.prepareStatement("SELECT * FROM grad ORDER BY broj_stanovnika DESC");
            dajDrzavu = conn.prepareStatement("SELECT * FROM drzava d WHERE id=?");
            dajGrad = conn.prepareStatement("SELECT * FROM grad WHERE id=?");
            dajGlavniGradDrzave = conn.prepareStatement("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g, drzava d WHERE d.naziv=? AND d.id=g.id");
            obrisiDrzavu = conn.prepareStatement("DELETE FROM drzava WHERE id=?");
            dajIdDrzave = conn.prepareStatement("SELECT id FROM drzava WHERE naziv=?");
            obrisiGradoveIzDrzave = conn.prepareStatement("DELETE FROM grad WHERE drzava=?");
        } catch(SQLException e) {
            regenerisiBazu();
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
                dajSveGradove = conn.prepareStatement("SELECT * FROM grad ORDER BY broj_stanovnika DESC");
                dajDrzavu = conn.prepareStatement("SELECT * FROM drzava WHERE id=?");
                dajGrad = conn.prepareStatement("SELECT g.naziv, g.broj_stanovnika FROM grad g, drzava d WHERE g.id=? AND d.glavni_grad=?");
                dajGlavniGradDrzave = conn.prepareStatement("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g, drzava d WHERE d.naziv=? AND d.id=g.id");
                obrisiDrzavu = conn.prepareStatement("DELETE FROM drzava WHERE id=?");
                dajIdDrzave = conn.prepareStatement("SELECT id FROM drzava WHERE naziv=?");
                obrisiGradoveIzDrzave = conn.prepareStatement("DELETE FROM grad WHERE drzava=?");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static GeografijaDAO getInstance() {
        if(instance == null) instance = new GeografijaDAO();
        return instance;
    }

    public static void removeInstance() {
        if(instance != null) {
            try {
                instance.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Drzava dajDrzavuIzResultSeta(ResultSet rs, Grad grad) throws SQLException {
        return new Drzava(rs.getInt(1), rs.getString(2), grad);
    }

    private Drzava dajDrzavu(int idDrzave, Grad grad) {
        try {
            dajDrzavu.setInt(1, idDrzave);
            ResultSet rs = dajDrzavu.executeQuery();
            if(!rs.next()) return null;
            if(rs.getInt(3) == grad.getId()) return dajDrzavuIzResultSeta(rs, grad);

            dajGrad.setInt(1, rs.getInt(3));
            ResultSet rs1 = dajGrad.executeQuery();
            Grad noviGrad = dajGradIzResultSeta(rs1);

            return dajDrzavuIzResultSeta(rs, noviGrad);
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Grad dajGradIzResultSeta(ResultSet set) throws SQLException {
        Grad grad = new Grad(set.getInt(1), set.getString(2), set.getInt(3));
        grad.setDrzava(dajDrzavu(set.getInt(4), grad));

        return grad;
    }

    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> rezultat = new ArrayList<>();

        try {
            ResultSet set = dajSveGradove.executeQuery();

            while(set.next()) {
                rezultat.add(dajGradIzResultSeta(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rezultat;
    }

    public Grad glavniGrad(String drzava) {
        try {
            dajGlavniGradDrzave.setString(1, drzava);
            ResultSet gradSet = dajGlavniGradDrzave.executeQuery();

            if(!gradSet.next())
                return null;

            Grad grad = new Grad(gradSet.getInt(1), gradSet.getString(2), gradSet.getInt(3));

            dajDrzavu.setInt(1,gradSet.getInt(4));
            ResultSet drzavaSet = dajDrzavu.executeQuery();
            drzavaSet.next();

            Drzava drzava1 = new Drzava(drzavaSet.getInt(1), drzavaSet.getString(2));
            drzava1.setGlavniGrad(grad);
            grad.setDrzava(drzava1);

            return grad;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void obrisiDrzavu(String drzava) {
        try {
            dajIdDrzave.setString(1, drzava);
            ResultSet rs = dajIdDrzave.executeQuery();

            if(!rs.next()) {
                System.out.println("Ne postoji drzava");
                return;
            }
            int idDrzave = rs.getInt(1);

            obrisiDrzavu.setInt(1, idDrzave);
            obrisiDrzavu.executeUpdate();

            obrisiGradoveIzDrzave.setInt(1, idDrzave);
            obrisiGradoveIzDrzave.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
