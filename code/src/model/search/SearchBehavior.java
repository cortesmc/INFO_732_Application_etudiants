package model.search;

import java.util.ArrayList;

public abstract class SearchBehavior {
    protected ArrayList<ComparableDataSource> wrappee;

    public SearchBehavior(ArrayList<ComparableDataSource> source) {
        wrappee = source;
    }

    // TODO : Implement this
    public ArrayList<ComparableDataSource> apply() {
        for (ComparableDataSource comparable : wrappee) {
            comparable.apply();
        }
        return wrappee;
    }
}
