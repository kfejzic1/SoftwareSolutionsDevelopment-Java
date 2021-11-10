package ba.unsa.etf.rpr;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static List<Pitanje> pitanja;
    private static Kviz kviz;

    private static void popuniPitanja(){
        Pitanje pitanje1 = new Pitanje("Koji je glavni grad Portugala?", 1);
        pitanje1.dodajOdgovor("a", "Berlin", false);
        pitanje1.dodajOdgovor("b", "Lisabon", true);
        pitanje1.dodajOdgovor("c", "Rejkjavik", false);
        pitanje1.dodajOdgovor("d", "Beograd", false);

        Pitanje pitanje2 = new Pitanje("Kojim slovom se u matematici označava skup cijelih brojeva?", 1);
        pitanje2.dodajOdgovor("a", "Z", true);
        pitanje2.dodajOdgovor("b", "N", false);
        pitanje2.dodajOdgovor("c", "R", false);

        Pitanje pitanje3 = new Pitanje("Koji fudbaleri su nastupali za Brazilsku reprezentaciju?", 3);
        pitanje3.dodajOdgovor("a", "Ronaldinho", true);
        pitanje3.dodajOdgovor("b", "C. Ronaldo", false);
        pitanje3.dodajOdgovor("c", "Gervinho", false);
        pitanje3.dodajOdgovor("d", "Cafú", true);

        Pitanje pitanje4 = new Pitanje("U kojoj se državi nalazi istoimeni slatkovodno jezero, najveće u Srednjoj Americi?", 2);
        pitanje4.dodajOdgovor("a", "Hondurasu", false);
        pitanje4.dodajOdgovor("b", "Nikaragvi", true);
        pitanje4.dodajOdgovor("c", "Panami", false);

        Pitanje pitanje5 = new Pitanje("Poznato umjetničko djelo \"Zvjezdana noć\" naslikao je:", 2);
        pitanje5.dodajOdgovor("a", "Pablo Picasso", false);
        pitanje5.dodajOdgovor("b", "Salvador Dali", false);
        pitanje5.dodajOdgovor("c", "Vincent van Gogh", true);

        Pitanje pitanje6 = new Pitanje("Koje su zemlje Beneluxa?", 2);
        pitanje6.dodajOdgovor("a", "Lihtenštajn", false);
        pitanje6.dodajOdgovor("b", "Belgija", true);
        pitanje6.dodajOdgovor("c", "Holandija", true);
        pitanje6.dodajOdgovor("d", "Danska", false);
        pitanje6.dodajOdgovor("e", "Luksemburg", true);

        Pitanje pitanje7 = new Pitanje("Koja od navedenih svjetskih čuda spadaju u antičke?", 3);
        pitanje7.dodajOdgovor("a", "Koloseum u Rimu", false);
        pitanje7.dodajOdgovor("b", "Keopsova piramida", true);
        pitanje7.dodajOdgovor("c", "Kineski zid", false);
        pitanje7.dodajOdgovor("d", "Zeusov kip u Olimpiji", true);
        pitanje7.dodajOdgovor("e", "Machu Picchu", false);

        Pitanje pitanje8 = new Pitanje("Gdje su se prvo počele koristiti papirne novčanice?", 2);
        pitanje8.dodajOdgovor("a", "Egipat", false);
        pitanje8.dodajOdgovor("b", "Indija", false);
        pitanje8.dodajOdgovor("c", "Portugal", false);
        pitanje8.dodajOdgovor("d", "Kina", true);

        Pitanje pitanje9 = new Pitanje("Koja reprezentacija je osvojila Svjetsko prvenstvo u fudbalu 2010. godine?", 2);
        pitanje9.dodajOdgovor("a", "Holandija", false);
        pitanje9.dodajOdgovor("b", "Engleska", false);
        pitanje9.dodajOdgovor("c", "Španija", true);
        pitanje9.dodajOdgovor("d", "Italija", false);

        Pitanje pitanje10 = new Pitanje("Ko je rekao: \"Mislim, dakle postojim (jesam)\" (Cogito ergo sum)?", 2);
        pitanje10.dodajOdgovor("a", "Pitagora", false);
        pitanje10.dodajOdgovor("b", "René Descartes", true);
        pitanje10.dodajOdgovor("c", "Isaac Newton", false);
        pitanje10.dodajOdgovor("d", "Nikola Tesla", false);

        pitanja = new ArrayList<>();
        pitanja.add(pitanje1);
        pitanja.add(pitanje2);
        pitanja.add(pitanje3);
        pitanja.add(pitanje4);
        pitanja.add(pitanje5);
        pitanja.add(pitanje6);
        pitanja.add(pitanje7);
        pitanja.add(pitanje8);
        pitanja.add(pitanje9);
        pitanja.add(pitanje10);
    }

    public static void igrajKviz(Kviz kvizZaIgranje){
        popuniPitanja();

        kvizZaIgranje = new Kviz("Kviz opsteg znanja", Kviz.SistemBodovanja.BINARNO);
        pitanja.forEach(kvizZaIgranje::dodajPitanje);

        Map<Pitanje, ArrayList<String>> zaokruzeniOdgovori = new HashMap<>();
        for(int i=0; i< pitanja.size(); i++){
            ArrayList<String> odgovori = new ArrayList<>();
            System.out.println(pitanja.get(i).toString());
            System.out.println("Vaši odgovori (za kraj unosa unesite tačku):");

            for(int j = 0; j < pitanja.get(i).dajListuOdgovora().size(); j++){
                //Omogućava unos odgovora onoliko puta koliko i postoji odgovora u pitanju

                Scanner ulaz = new Scanner(System.in);
                String odgovor = ulaz.nextLine();
                if(odgovor.equals(".")){
                    break;
                }
                odgovori.add(odgovor);
            }

            zaokruzeniOdgovori.put(pitanja.get(i), odgovori);
            try{
                kvizZaIgranje.predajKviz(zaokruzeniOdgovori);
            }catch(Exception e){
                System.out.println(e);
                System.out.println("Unesite ispravne oznake odgovora!");
                i = i - 1;
            }
        }

        RezultatKviza rezultat = kvizZaIgranje.predajKviz(zaokruzeniOdgovori);
        System.out.println("Kviz je gotov!");
        System.out.println(rezultat);
        System.out.println(kvizZaIgranje);
    }

    public static void main(String[] args) {
        igrajKviz(kviz);
    }
}
