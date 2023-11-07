package model.search;

import model.Internship;

import java.util.ArrayList;
import java.util.Comparator;

class InternshipDateComparator implements Comparator<ISearch> {
    @Override
    public int compare(ISearch internship1, ISearch internship2) {
        return ((Internship)internship1).getYear().compareTo(((Internship)internship2).getYear());
    }
}

public class SortByDateStrategy extends SortingStrategy {
    public SortByDateStrategy(ISearch _wrappee, boolean _order) {
        super(_wrappee, _order);
    }

    @Override
    public ArrayList<ISearch> apply() {
        ArrayList<ISearch> result = wrappee.apply();
        result.sort(new InternshipDateComparator());
        return result;
    }
}
