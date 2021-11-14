Univerzitet u Sarajevu

Elektrotehnički fakultet Sarajevo

Predmet: Razvoj programskih rješenja 2021/2022

# Tutorijal 5
## Priprema za tutorijal
* Kliknite na pozivnicu: https://classroom.github.com/a/lsGTAsla - importujte vaš projekat na repozitorij koji je ovom pozivnicom kreiran.
* Na osnovu class dijagrama isprogramirati preostale klase
* Upoznati se sa funkcionalnim programiranjem i streamovima u Javi

![Class dijagram](/class_diagram.png?raw=true "Class dijagram")

## Zadatak 1
Trenutno klasa Racun ima privatni atribut odobrenjePrekoracenja tipa boolean, odnosno prekoračenje može biti odobreno ili ne. Izmijenite klasu tako da je ovaj atribut tipa Double i predstavlja iznos odobrenog prekoračenja. Ovu izmjenu napravite tako da promijenite minimalan broj linija koda i da svi testovi prolaze bez izmjene.

Zatim dodajte testove za situaciju kada korisnik pokušava podići iznos veći od odobrenog prekoračenja.

## Zadatak 2
U pripremljeni projekat dodati novi paket ba.unsa.etf.rpr.tutorijal5.util i kreirati pomoćnu klasu Kredit, te u istom paketu kreirati funkcionalni interface KreditnaSposobnost.
KreditnaSposobnost sadrži funkciju provjeri koja prima Racun i vraća iznos kreditne sposobnosti korisnika banke.

Klasa Kredit ima pomoćnu metodu dajKreditnuSposobnost koja prima funkciju KreditnaSposobnost i Korisnika, te na osnovu računa korisnika vrši poziv dobivene funkcije.

## Zadatak 3
Koristeći isključivo znanje o streamovima napisati drugu pomoćnu funkciju u klasi Kredit pod nazivom bezPrekoracenja koja na osnovu primljene liste korisnika vrši ispis svih korisnika koji nemaju prekoračenje računa (tj. imaju iznos računa veći ili jednak nuli).

## Zadatak 4
Koristeći isključivo znanje o streamovima napisati treću pomoćnu funkciju u klasi Kredit pod nazivom odobriPrekoracenje koja na osnovu primljene liste korisnika vrši odobrenje prekoračenja u iznosu od 1000, ako je stanje računa korisnika veće ili jednako 10 000.
## Zadatak 5
Kreirati JUnit5 unit testove za klasu Kredit. 
