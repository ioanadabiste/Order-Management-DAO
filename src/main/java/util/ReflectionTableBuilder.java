// util/ReflectionTableBuilder.java
package util;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class ReflectionTableBuilder {

    public static <T> DefaultTableModel buildTableModel(List<T> objectList) {
        if (objectList == null || objectList.isEmpty()) {
            return new DefaultTableModel();
        }

        Class<?> clazz = objectList.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();

        String[] columnNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            columnNames[i] = fields[i].getName();
        }

        Object[][] data = new Object[objectList.size()][fields.length];
        for (int i = 0; i < objectList.size(); i++) {
            T obj = objectList.get(i);
            for (int j = 0; j < fields.length; j++) {
                try {
                    data[i][j] = fields[j].get(obj);
                } catch (IllegalAccessException e) {
                    data[i][j] = "error";
                }
            }
        }

        return new DefaultTableModel(data, columnNames);
    }
}
