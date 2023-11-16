package org.example;

import org.example.animals.FurType;
import org.example.animals.WhiteBear;

import java.lang.reflect.Field;
import java.sql.*;

public class TableManager {
    private Connection conn;
    private static StringBuilder script = new StringBuilder();
    public void Connect(){
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Disconnect(){
        try {
            conn.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void ExecuteUpdate(StringBuilder query) {
        try (Statement statement = conn.createStatement()) {
            script.append(query).append("\n");
            statement.executeUpdate(query.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void CreateTable(Class<?> _class)  throws IllegalAccessException {
        if (_class.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = _class.getAnnotation(Table.class);
            String tableName = tableAnnotation.title();

            StringBuilder query = new StringBuilder("DROP TABLE IF EXISTS " + tableName + "; CREATE TABLE ");
            query.append(tableName);
            query.append(" (");

            Field[] fields = _class.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    field.setAccessible(true);
                    query.append(field.getName());
                    if (field.getType() == int.class) {
                        query.append(" INTEGER").append(", ");
                    } else if (field.getType() == String.class) {
                        query.append(" VARCHAR(100)").append(", ");
                    } else if (field.getType().isEnum()) {
                        query.append(" VARCHAR(100)").append(", ");
                    }
                }
            }
            query.setLength(query.length() - 2);
            query.append(")");

            ExecuteUpdate(query);
            script.append("\n");
        }
    }

    public void Insert(WhiteBear... objects) throws IllegalAccessException {
        Class<?> objectClass = objects[0].getClass();
        String tableName = objectClass.getAnnotation(Table.class).title();

        for(Object object: objects){
            if (object.getClass().isAnnotationPresent(Table.class)) {

                StringBuilder query = new StringBuilder("INSERT INTO ");
                query.append(tableName).append(" VALUES (");
                Field[] objectFields = objectClass.getDeclaredFields();

                for (Field field : objectFields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        field.setAccessible(true);
                        if (field.getType() == int.class) {
                            query.append(field.get(object)).append(", ");
                        } else if (field.getType() == String.class) {
                            query.append("'").append(field.get(object)).append("', ");
                        } else if (field.getType().isEnum()) {
                            query.append("'").append(field.get(object)).append("', ");
                        }
                    }
                }
                query.setLength(query.length() - 2);
                query.append(")");
                ExecuteUpdate(query);
        }



        }
    }

//    public void Delete(Object object) throws IllegalAccessException {
//        Class<?> objectClass = object.getClass();
//
//        String tableName = objectClass.getAnnotation(Table.class).title();
//        Field[] objectFields = objectClass.getDeclaredFields();
//        StringBuilder query = new StringBuilder("DELETE FROM ");
//        for (Field field : objectFields) {
//            if (field.isAnnotationPresent(Column.class)) {
//                field.setAccessible(true);
//                query.append(tableName);
//                query.append(" WHERE ").append(field.getName());
//                query.append(" = ").append(field.getName());
//                break;
//            }
//        }
//        ExecuteUpdate(query);
//    }

    public void ClearTable(Class<?> _class) {
        StringBuilder query = new StringBuilder("DELETE FROM ");
        query.append(_class.getAnnotation(Table.class).title());

        ExecuteUpdate(query);
    }

    public void DropTable(Class<?> _class) {
        StringBuilder query = new StringBuilder("DROP TABLE ");
        query.append(_class.getAnnotation(Table.class).title());

        ExecuteUpdate(query);
    }

    public void PrintScript() {
        System.out.println(script.toString());
    }

}