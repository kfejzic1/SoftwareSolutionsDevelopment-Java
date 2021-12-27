package ba.unsa.etf.rpr.tutorijal8;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {
    public static ArrayList<Grad> ucitajGradove() {
        ArrayList<Grad> gradovi = new ArrayList<>();

        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileReader("mjerenja.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Datoteka ne postoji ili se ne može otvoriti");
        }

        try {
            int i=0;

            boolean prviPut = true;
            String naziv = "";
            ulaz.useDelimiter("(\\r\\n)|,"); //Kako procitati
            double[] vrijednostTemperature = new double[1000];

            while(ulaz.hasNext()) {
                if(ulaz.hasNextDouble()) {
                    //Pronadjena double vrijednost
                    if(i==999)  //Provjeri da li je uneseno 1000 vrijednosti
                        continue;
                    vrijednostTemperature[i] = ulaz.nextDouble();
                    i++;
                } else {
                    //Nije pronadjena double vrijednost vec naziv
                    if(!prviPut) {
                        gradovi.add(new Grad(naziv, vrijednostTemperature));

                        vrijednostTemperature = new double[1000];
                    }

                    naziv = ulaz.next();
                    i=0;
                    prviPut = false;
                }
            }

            gradovi.add(new Grad(naziv, vrijednostTemperature));
        } catch (Exception e) {
            System.out.println(e);
        }

        ulaz.close();
        return gradovi;
    }

    public static void main(String[] args) {
        ArrayList<Grad> gradovi = ucitajGradove();
        System.out.println(gradovi.size());

        for(int i=0; i<gradovi.size(); i++)
            System.out.println(gradovi.get(i).getNaziv());
	// write your code here
    }

    public static UN ucitajXml(ArrayList<Object> objects) {
        return new UN();
    }
}
