BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "drzava" (
	"id"	INTEGER,
	"naziv"	TEXT,
	"glavni_grad"	INTEGER,
	FOREIGN KEY("glavni_grad") REFERENCES "grad"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "grad" (
	"id"	INTEGER,
	"naziv"	TEXT,
	"broj_stanovnika"	INTEGER,
	"drzava"	INTEGER,
	"slika" TEXT
	FOREIGN KEY("drzava") REFERENCES "drzava"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "drzava" VALUES (1,'Velika Britanija',1);
INSERT INTO "drzava" VALUES (2,'Francuska',2);
INSERT INTO "drzava" VALUES (3,'Austrija',3);
INSERT INTO "grad" VALUES (1,'London',8825000,1);
INSERT INTO "grad" VALUES (2,'Pariz',2206488,2);
INSERT INTO "grad" VALUES (3,'Beč',1899055,3);
INSERT INTO "grad" VALUES (4,'Manchester',545500,1);
INSERT INTO "grad" VALUES (5,'Graz',280200,3);
COMMIT;
