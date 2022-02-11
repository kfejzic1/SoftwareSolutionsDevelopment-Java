package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ContentStore {
    private List<Content> kontent = new ArrayList<>();

    public void addContent(Content content) {kontent.add(0, content);}

    public String getContentsAsCsvFormatWithBraces() {
        return (String) kontent.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    public List<Content> getContents() {
        return this.kontent;
    }
}
