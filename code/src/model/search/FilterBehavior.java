package model.search;

import java.util.ArrayList;

public class FilterBehavior extends SearchBehavior {
    private String attribute;
    private String value;

    public FilterBehavior(String _attribute, String _value) {
        super(new ArrayList<>());
        attribute = _attribute;
        value = _value;
    }
}
