package ba.unsa.etf.rpr.tutorijal5.util;

import ba.unsa.etf.rpr.tutorijal5.Racun;

@FunctionalInterface
public interface KreditnaSposobnost {
    Double provjeri (Racun racun);
}
