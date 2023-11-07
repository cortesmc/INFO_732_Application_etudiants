package model.search;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class FilterBehavior extends SearchBehavior {
    private String attribute;
    private String value;

    public FilterBehavior(ArrayList<DataSource> wrapped,String _attribute, String _value) {
        super(wrapped);
        attribute = _attribute;
        value = _value.toLowerCase();
    }

    @Override
    public ArrayList<DataSource> apply() {
        ArrayList<DataSource> result = new ArrayList<>();
        for (DataSource source: wrappee) {
            try {
                Field field = source.getClass().getDeclaredField(attribute);
                field.setAccessible(true);
                String value = field.get(source).toString().toLowerCase();
                if(value.contains(this.value)) result.add(source);
            } catch (NoSuchFieldException | IllegalAccessException ignored) {
            }
        }
        return result;
    }
}
