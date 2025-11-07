package dataAccessLayer;


import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import connection.ConnectionFactory;

public class GenericDAO<T> {
    private final Class<T> type;

    public GenericDAO(Class<T> type) {
        this.type = type;
    }

    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM " + type.getSimpleName();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                T obj = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(obj, rs.getObject(field.getName()));
                }
                list.add(obj);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void insert(T obj) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            StringBuilder fields = new StringBuilder();
            StringBuilder values = new StringBuilder();
            List<Object> valList = new ArrayList<>();

            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                fields.append(field.getName()).append(",");
                values.append("?,");
                valList.add(field.get(obj));
            }

            String query = String.format("INSERT INTO %s (%s) VALUES (%s)",
                    type.getSimpleName(),
                    fields.substring(0, fields.length()-1),
                    values.substring(0, values.length()-1));

            PreparedStatement ps = conn.prepareStatement(query);
            for (int i = 0; i < valList.size(); i++) ps.setObject(i+1, valList.get(i));
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = String.format("DELETE FROM %s WHERE id=?", type.getSimpleName());
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(T obj) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            StringBuilder sb = new StringBuilder();
            Object idVal = null;

            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.getName().equals("id")) {
                    sb.append(field.getName()).append("=?,");
                } else {
                    idVal = field.get(obj);
                }
            }

            String query = String.format("UPDATE %s SET %s WHERE id=?",
                    type.getSimpleName(),
                    sb.substring(0, sb.length() - 1));

            PreparedStatement ps = conn.prepareStatement(query);
            int index = 1;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.getName().equals("id")) {
                    ps.setObject(index++, field.get(obj));
                }
            }
            ps.setObject(index, idVal);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public T findById(int id) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = String.format("SELECT * FROM %s WHERE id=?", type.getSimpleName());
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                T obj = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(obj, rs.getObject(field.getName()));
                }
                return obj;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}
