package model.search;

import java.util.ArrayList;

public class SearchBehavior implements ISearch {
    protected ArrayList<ISearch> wrappee;

    public SearchBehavior(ArrayList<ISearch> source) {
        wrappee = source;
    }
    public SearchBehavior() {
        wrappee = null;
    }

    // TODO : Implement this
    public ArrayList<ISearch> apply() {
        return wrappee;
    }
}
