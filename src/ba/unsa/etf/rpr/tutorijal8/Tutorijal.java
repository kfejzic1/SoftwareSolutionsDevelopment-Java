package ba.unsa.etf.rpr.tutorijal8;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    public static UN ucitajXml(ArrayList<Grad> gradovi) {
        Document xml = null;
        UN ujedinjeneNacije = new UN();

        try {
            DocumentBuilder ulaz = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xml = ulaz.parse(new File("drzave.xml"));

            Element rootElement = xml.getDocumentElement();
            NodeList drzave = rootElement.getChildNodes();

            for(int i=0; i<drzave.getLength(); i++) {
                Node trenutnaDrzava = drzave.item(i);

                if(trenutnaDrzava instanceof Element) {
                    Drzava novaDrzava = new Drzava();
                    NodeList podaciODrzavi = trenutnaDrzava.getChildNodes();

                    for(int j=0; j<podaciODrzavi.getLength(); j++) {
                        Node temp = podaciODrzavi.item(j);

                        if(temp instanceof Element) {
                            switch (temp.getNodeName()) {
                                case "naziv" -> novaDrzava.setNaziv(temp.getTextContent());
                                case "povrsina" -> {
                                    novaDrzava.setPovrsina(Double.parseDouble(temp.getTextContent()));
                                    novaDrzava.setJedinica(((Element) temp).getAttribute("jedinica"));
                                }
                                case "glavnigrad" -> {
                                    novaDrzava.setGlavniGrad(new Grad(temp.getTextContent().trim(), Integer.parseInt(((Element) temp).getAttribute("stanovnika"))));
                                    for(var x : gradovi) {
                                        if(x.getNaziv().equals(temp.getTextContent().trim()))
                                            x.setBrojStanovnika(Integer.parseInt(((Element) temp).getAttribute("stanovnika")));
                                    }
                                }
                            }
                        }
                    }
                    ujedinjeneNacije.getDrzave().add(novaDrzava);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ujedinjeneNacije;
    }

    public static void zapisiXml(UN ujedinjeneNacije) {
            try {
                XMLEncoder izlaz = new XMLEncoder(new FileOutputStream("un.xml"));
                izlaz.writeObject(ujedinjeneNacije);
                izlaz.close();
            } catch (Exception e) {
                System.out.println("Greška: " + e);
            }
    }

    public static void main(String[] args) {
        zapisiXml(ucitajXml(ucitajGradove()));
	// write your code here
    }
}
