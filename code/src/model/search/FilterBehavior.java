package model.search;

import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class FilterBehavior extends SearchBehavior {
    private String attribute;
    private String value;

    public FilterBehavior(ArrayList<DataSource> wrapped,String _attribute, String _value) {
        super(wrapped);
        attribute = _attribute;
        value = _value;
    }

    @Override
    public ArrayList<DataSource> apply() {
        ArrayList<DataSource> result = new ArrayList<>(wrappee);
        for (DataSource source: wrappee) {
            try {
                Field field = source.getClass().getDeclaredField(attribute);
                field.setAccessible(true);
                String value = field.get(source).toString();
                if(!value.contains(this.value)) result.remove(source);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                result.remove(source);
            }
        }
        System.out.println(result.size());
        return result;
    }
}
