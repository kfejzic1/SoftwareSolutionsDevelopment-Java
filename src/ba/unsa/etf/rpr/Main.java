package ba.unsa.etf.rpr;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ContentStore contentStore = new ContentStore();
        contentStore.addContent(new Movie("Movie1", 3.1, 59, List.of(Genre.COMEDY)));
        contentStore.addContent(new Movie("Movie2", 2.2, 49, List.of(Genre.ACTION)));
        contentStore.addContent(new Movie("Movie3", 1.3, 39, List.of(Genre.DRAMA)));
        System.out.println(contentStore.getContents().get(2));
    }
}
