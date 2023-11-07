package model.search;

import model.Internship;

import java.util.ArrayList;
import java.util.Comparator;

class InternshipTitleComparator implements Comparator<ISearch> {
    @Override
    public int compare(ISearch internship1, ISearch internship2) {
        return ((Internship)internship1).getTitle().compareTo(((Internship)internship2).getTitle());
    }
}

public class SortByTitleStrategy extends SortingStrategy {
    public SortByTitleStrategy(ISearch _wrappee, boolean _order) {
        super(_wrappee, _order);
    }

    @Override
    public ArrayList<ISearch> apply() {
        ArrayList<ISearch> result = wrappee.apply();
        result.sort(new InternshipTitleComparator());
        return result;
    }
}
