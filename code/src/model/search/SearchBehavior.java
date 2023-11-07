package model.search;

import java.util.ArrayList;

public abstract class SearchBehavior {
    protected ArrayList<DataSource> wrappee;

    public SearchBehavior(ArrayList<DataSource> source) {
        wrappee = source;
    }
    public SearchBehavior() {
        wrappee = null;
    }

    // TODO : Implement this
    public abstract ArrayList<DataSource> apply();
}
