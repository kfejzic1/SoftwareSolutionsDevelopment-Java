package ba.unsa.etf.rpr;

import java.util.List;

public class ContentStore {
    private List<Content> kontent;

    public void addContent(Content content) {kontent.add(content);}

//    List<Watchable> getWatchables() {
//        return kontent.
//    }

    public String getContentsAsCsvFormatWithBraces() {
        return kontent.stream().toString();
    }

    public List<Content> getContents() {
        return this.kontent;
    }
}
