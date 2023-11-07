package model;
public class FilterBehavior extends SearchBehavior {
    private String attribute;
    private String value;

    public FilterBehavior(String _attribute, String _value) {
        super();
        attribute = _attribute;
        value = _value;
    }
}
