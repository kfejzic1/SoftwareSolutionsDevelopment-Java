package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Profesor> generisiProfesore(){
        Profesor p1 = new Profesor("Naida", "Mujić");
        Profesor p2 = new Profesor("Mirza", "Hamza");
        Profesor p3 = new Profesor("Dijana", "Dujak");
        Profesor p4 = new Profesor("Almasa", "Odžak");
        Profesor p5 = new Profesor("Vedran", "Ljubović");
        Profesor p6 = new Profesor("Željko", "Jurić");
        Profesor p7 = new Profesor("Dinko", "Osmanković");
        Profesor p8 = new Profesor("Samir", "Ribić");
        Profesor p9 = new Profesor("Amela", "Muratović-Ribić");
        Profesor p10 = new Profesor("Novica", "Nosović");
        Profesor p11 = new Profesor("Emir", "Buza");
        Profesor p12 = new Profesor("Haris", "Šupić");

        ArrayList<Profesor> profesori = new ArrayList<>();
        profesori.add(p1);
        profesori.add(p2);
        profesori.add(p3);
        profesori.add(p4);
        profesori.add(p5);
        profesori.add(p6);
        profesori.add(p7);
        profesori.add(p8);
        profesori.add(p9);
        profesori.add(p10);
        profesori.add(p11);
        profesori.add(p12);

        return profesori;
    }

    public static void main(String[] args){
        ArrayList<Profesor> profesori = generisiProfesore();

        Predmet p1 = new Predmet("Inženjerska matematika 1", 7, 120, profesori.get(0));
        Predmet p2 = new Predmet("Osnove elektrotehnike", 7, 50, profesori.get(1));
        Predmet p3 = new Predmet("Inženjerska fizika 1", 5, 130, profesori.get(2));
        Predmet p4 = new Predmet("Linearna algebra i geometrija", 5, 160, profesori.get(3));
        Predmet p5 = new Predmet("Uvod u programiranje", 6, 45, profesori.get(4));
        Predmet p6 = new Predmet("Inženjerska matematika 2", 7, 80, profesori.get(0));
        Predmet p7 = new Predmet("Tehnike programiranja", 6, 80, profesori.get(5));
        Predmet p8 = new Predmet("Matematička logika i teorija izračunljivosti", 7, 50, profesori.get(6));
        Predmet p9 = new Predmet("Operativni sistemi", 5, 60, profesori.get(7));
        Predmet p10 = new Predmet("Vjerovatnoća i statistika", 5, 120, profesori.get(8));
        Predmet p11 = new Predmet("Diskretna matematika", 5, 80, profesori.get(5));
        Predmet p12 = new Predmet("Logički dizajn", 5, 130, profesori.get(9));
        Predmet p13 = new Predmet("Razvoj programskih rješenja", 5, 80, profesori.get(4));
        Predmet p14 = new Predmet("Osnove baza podataka", 5, 100, profesori.get(10));
        Predmet p15 = new Predmet("Algoritmi i strukture podataka", 5, 140, profesori.get(11));
        Predmet p16 = new Predmet("Sistemsko programiranje", 5, 80, profesori.get(7));
        Predmet p17 = new Predmet("Numerički algoritmi", 5, 80, profesori.get(5));

        ArrayList<Predmet> obavezniPredmeti1 = new ArrayList<>();
        ArrayList<Predmet> obavezniPredmeti2 = new ArrayList<>();
        ArrayList<Predmet> obavezniPredmeti3 = new ArrayList<>();
        ArrayList<Predmet> izborniPredmeti2 = new ArrayList<>();
        ArrayList<Predmet> izborniPredmeti3 = new ArrayList<>();

        obavezniPredmeti1.add(p1);
        obavezniPredmeti1.add(p2);
        obavezniPredmeti1.add(p3);
        obavezniPredmeti1.add(p4);
        obavezniPredmeti1.add(p5);

        obavezniPredmeti2.add(p6);
        obavezniPredmeti2.add(p7);
        obavezniPredmeti2.add(p8);
        izborniPredmeti2.add(p9);
        izborniPredmeti2.add(p10);

        obavezniPredmeti3.add(p11);
        obavezniPredmeti3.add(p12);
        obavezniPredmeti3.add(p13);
        obavezniPredmeti3.add(p14);
        obavezniPredmeti3.add(p15);
        izborniPredmeti3.add(p16);
        izborniPredmeti3.add(p17);

        ArrayList<Semestar> semestri = new ArrayList<>();
        semestri.add(new Semestar(1, obavezniPredmeti1, new ArrayList<>(), Ciklusi.Bachelor));
        semestri.add(new Semestar(2, obavezniPredmeti2, izborniPredmeti2, Ciklusi.Bachelor));
        semestri.add(new Semestar(3, obavezniPredmeti3, izborniPredmeti3, Ciklusi.Bachelor));

        Fakultet fakultet = new Fakultet("Elektrotehnički fakultet Sarajevo", profesori, semestri);

        System.out.println("Dobrodošli na " + fakultet.getNaziv() + "\n");
        String nultiIndeks = "18000";

        for(;;){
            System.out.println("""
                    Odaberite jednu od opcija:
                    0 - izađi iz programa
                    1 - upisi novog studenta
                    2 - upisi ocjenu studentu
                    3 - prepis ocjena studenta
                    4 - profesori koji nemaju normu
                    5 - profesori koji rade preko norme
                    6 - ispisi sve profesore sortirane po normi
                    7 - ispisi sve profesore sortirane po broju studenata koji slušaju njegove predmete
                    8 - ispisi sve studente
                    """);
            Scanner ulaz = new Scanner(System.in);
            int opcija = ulaz.nextInt();

            if(opcija == 0)
                break;
            else if(opcija == 1){
                System.out.print("Unesite ime studenta: ");
                Scanner noviUlaz = new Scanner(System.in);
                String ime = noviUlaz.nextLine();
                System.out.print("Unesite prezime studenta: ");
                String prezime = noviUlaz.nextLine();

                Student noviStudent = new Student(ime, prezime, nultiIndeks);
                fakultet.dodajStudenta(noviStudent);
                int idNovogStudenta = fakultet.getStudenti().size()-1;

                nultiIndeks = Integer.toString(Integer.parseInt(nultiIndeks)+1);    //Povecava indeks za 1

                for(Semestar semestar : fakultet.getSemestri())
                    System.out.println(semestar);

                System.out.print("Odaberite semestar na koji će biti upisan student: ");
                int semestar = noviUlaz.nextInt();

                //Upisuje posljednjeg ubacenog studenta na zeljeni semestar
                fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(idNovogStudenta), fakultet.getSemestri().get(semestar-1));

                while(fakultet.getStudenti().get(idNovogStudenta).dajUkupanBrojECTSPoena()<30){ //Potrebno je imati tačno 30 ECTS poena u semestru
                    System.out.println("Odaberite izborni predmet: ");
                    for(int i = 0; i<fakultet.getSemestri().get(semestar-1).getIzborniPredmeti().size(); i++){
                        System.out.println(i+1 + " - " + fakultet.getSemestri().get(semestar-1).getIzborniPredmeti().get(i));
                    }

                    int izborniPredmet = noviUlaz.nextInt();
                    try{
                        fakultet.getStudenti().get(idNovogStudenta).upisiIzborniPredmet(fakultet.getSemestri().get(semestar-1).getIzborniPredmeti().get(izborniPredmet-1));
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            else if(opcija == 2){
                System.out.print("Unesite broj indeksa studenta kojem želite upisati ocjenu: ");
                Scanner noviUlaz = new Scanner(System.in);
                String brojIndeksa = noviUlaz.nextLine();

                try{
                    Student s = fakultet.dajStudentaSaBrojemIndeksa(brojIndeksa);
                    System.out.println("Odaberi predmet iz kojeg želite upisati ocjenu: ");
                    for (int i = 0; i<s.getUpisaniPredmeti().size(); i++){
                        if(!s.getOcjene().containsKey(s.getUpisaniPredmeti().get(i)))   //Provjerava da li je upisana ocjena iz predmeta, obilazimo exception
                            System.out.println(i + 1 + " - " + s.getUpisaniPredmeti().get(i));
                    }

                    int predmet = noviUlaz.nextInt();

                    System.out.print("Unesite ocjenu koju želite unijeti: ");
                    int ocjena = noviUlaz.nextInt();

                    try{
                        s.upisiOcjenuIzPredmeta(s.getUpisaniPredmeti().get(predmet-1), ocjena);
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(opcija == 3){
                System.out.print("Unesite broj indeksa studenta čiji prepis ocjena želite: ");
                Scanner noviUlaz = new Scanner(System.in);
                String brojIndeksa = noviUlaz.nextLine();

                try{
                    System.out.println(fakultet.dajPrepisOcjenaStudentaSaBrojemIndeksa(brojIndeksa));
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(opcija == 4){
                System.out.println("Profesori koji nemaju normu:\n" + fakultet.dajProfesoreKojiNemajuNormu());
            }
            else if(opcija == 5){
                System.out.println("Profesori koji rade preko norme:\n" + fakultet.dajProfesoreKojiRadePrekoNorme());
            }
            else if(opcija == 6){
                System.out.println(fakultet.dajProfesoreSortiranePoNormi());
            }
            else if(opcija == 7){
                System.out.println(fakultet.dajProfesoreSortiranePoBrojuStudenata());
            }
            else if(opcija == 8){
                for(Student s : fakultet.getStudenti())
                    System.out.println(s);
            }
        }
    }
}
