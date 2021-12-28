CREATE TABLE IF NOT EXISTS "korisnik" (
    "Id"	INTEGER,
	"Ime"	TEXT,
	"Prezime"	TEXT,
	"Email"	TEXT,
	"Username"	TEXT,
	"Password"	TEXT,
	PRIMARY KEY("Id")
);
INSERT INTO "korisnik" VALUES ('1','Vedran','Ljubović','vljubovic@etf.unsa.ba','vedranlj','test');
INSERT INTO "korisnik" VALUES ('2','Amra','Delić','adelic@etf.unsa.ba','amrad','test');
INSERT INTO "korisnik" VALUES ('3','Tarik','Sijerčić','tsijercic1@etf.unsa.ba','tariks','test');
INSERT INTO "korisnik" VALUES ('4','Rijad','Fejzić','rfejzic1@etf.unsa.ba','rijadf','test');