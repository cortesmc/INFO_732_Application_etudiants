package model.search;

import model.Internship;
import model.search.ISearch;

import java.util.ArrayList;

public abstract class SortingStrategy implements ISearch {
    protected ISearch wrappee;

    public boolean order;

    public SortingStrategy(ISearch _wrappee, boolean _order) {
        wrappee = _wrappee;
        order = _order;
    }
}
