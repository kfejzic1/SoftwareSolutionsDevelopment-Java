package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private Connection conn;
    private PreparedStatement dajSveGradove;

    public GeografijaDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            dajSveGradove = conn.prepareStatement("SELECT * FROM grad ORDER BY broj_stanovnika DESC");
        } catch(SQLException e) {
            System.out.println("Greška u čitanju baze!");
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

    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> rezultat = new ArrayList<>();

        try {
            ResultSet result = dajSveGradove.executeQuery();
            while(result.next()) {
                Grad novi = new Grad(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4));
                rezultat.add(novi);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return rezultat;
    }
}
