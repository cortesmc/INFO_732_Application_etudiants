package model.search;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

public class FilterBehavior implements ISearch {
    private String attribute;
    private String value;
    private ISearch wrappee;

    public FilterBehavior(ISearch _wrapper, String _attribute, String _value) {
        wrappee = _wrapper;
        attribute = _attribute;
        value = _value.toLowerCase();
    }

    @Override
    public ArrayList<ISearch> apply() {
        ArrayList<ISearch> result = wrappee.apply();

        Iterator<ISearch> iterator = result.iterator();
        while (iterator.hasNext()) {
            ISearch source = iterator.next();
            try {
                Field field = source.getClass().getDeclaredField(attribute);
                field.setAccessible(true);
                String value = field.get(source).toString().toLowerCase();
                if(!value.contains(this.value)) iterator.remove();
            } catch (NoSuchFieldException | IllegalAccessException ignored) { }
        }
        return result;
    }
}
