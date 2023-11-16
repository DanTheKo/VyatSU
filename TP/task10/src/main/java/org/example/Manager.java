package org.example;

import java.sql.*;

public class Manager {

    private Connection conn;

    public void Connect(){
        try{
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres");
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

    private void ExecuteUpdate(String sql) {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void CreateTableProgress(){
        ExecuteUpdate("CREATE TABLE progress\n" +
                "(\n" +
                "    id serial NOT NULL PRIMARY KEY ,\n" +
                "    rating int NOT NULL,\n" +
                "    student_id int NOT NULL REFERENCES public.student(id) on delete cascade,\n" +
                "    subject_id int NOT NULL REFERENCES public.subject(id) on delete cascade,\n" +
                "    check (rating <= 5 and rating >=2 )\n" +
                ");");
    }

    public void InsertInProgress(){
        ExecuteUpdate("insert into public.progress (rating, student_id, subject_id)\n" +
                "values (5, 1, 1),\n" +
                "       (4, 1, 2),\n" +
                "       (5, 1, 4),\n" +
                "       (2, 2, 1),\n" +
                "       (5, 2, 2),\n" +
                "       (3, 4, 1),\n" +
                "       (5, 3, 1);");
    }

    public void CreateTableStudent(){
        ExecuteUpdate("CREATE TABLE student\n" +
                "(    id serial NOT NULL PRIMARY KEY,\n" +
                "     name text NOT NULL ,\n" +
                "     passport_series varchar(4) NOT NULL CHECK (passport_series~ '^[0-9]+$'),\n" +
                "     passport_number varchar(6) NOT NULL CHECK (passport_number~ '^[0-9]+$'),\n" +
                "     unique (passport_number, passport_series)\n" +
                ");");
    }
    public void InsertInStudent(){
        ExecuteUpdate("insert into public.student (name, passport_series, passport_number)\n" +
                "values ('Даниил', '5432', '763751'),\n" +
                "       ('Иван', '5432', '563751'),\n" +
                "       ('Денис', '5432', '363751'),\n" +
                "       ('Михаил', '5433', '263951')\n" +
                ";");
    }
    public void CreateTableSubject(){
        ExecuteUpdate("CREATE TABLE subject\n" +
                "(\n" +
                "    id serial NOT NULL PRIMARY KEY,\n" +
                "    name text NOT NULL\n" +
                ");");
    }

    public void InsertInSubject(){
        ExecuteUpdate("insert into public.subject (name)\n" +
                "values ('Математика'),\n" +
                "       ('Русский язык'),\n" +
                "       ('История'),\n" +
                "       ('Информатика');");
    }

    public void Select0(int _rating, String _subject) throws SQLException {
        String str = "select stud.id, stud.name, stud.passport_series, stud.passport_number, prog.rating from public.student stud\n" +
                "    join public.progress prog\n" +
                "        on stud.id = prog.student_id\n" +
                "    join public.subject s\n" +
                "         on prog.subject_id = s.id\n" +
                "where rating >=" + _rating + " and s.name = '" + _subject +"';";
        PreparedStatement prStatement = conn.prepareStatement(str.toString());
        ResultSet set = prStatement.executeQuery();
        while (set.next()){
            String id = set.getString(1);
            String name = set.getString(2);
            String passport_series = set.getString(3);
            String passport_number = set.getString(4);
            String rating = set.getString(5);

            System.out.print(id + " | " + name + " | " + passport_series + " | " + passport_number +" | " + rating +"\n");
        }
    }

    public void Select1() throws SQLException {
        String str = "select avg(rating) from public.progress prog\n" +
                "join public.subject\n" +
                "    on prog.subject_id = subject.id;";
        PreparedStatement prStatement = conn.prepareStatement(str.toString());
        ResultSet set = prStatement.executeQuery();
        while (set.next()){
            String avg = set.getString(1);
            System.out.print(avg+"\n");
        }
    }
    public void Select2(String _name) throws SQLException {
        String str = "select avg(rating) from public.progress prog\n" +
                "                            join public.student stud\n" +
                "                                 on prog.subject_id = stud.id\n" +
                "where stud.name = '" + _name + "';";
        PreparedStatement prStatement = conn.prepareStatement(str.toString());
        ResultSet set = prStatement.executeQuery();
        while (set.next()){
            String avg = set.getString(1);
            System.out.print(_name + " | "  +avg+"\n");
        }
    }

    public void Select3() throws SQLException {
        String str = "select sb.name, COUNT(sb.name)\n" +
                "from public.Progress p\n" +
                "         join public.Student s on p.student_id = s.id\n" +
                "         join public.Subject sb on p.subject_id = sb.id\n" +
                "group by sb.name\n" +
                "order by count(sb.name) DESC\n" +
                "limit 3;";
        PreparedStatement prStatement = conn.prepareStatement(str.toString());
        ResultSet set = prStatement.executeQuery();
        while (set.next()){
            String subjName = set.getString(1);
            String count = set.getString(2);
            System.out.print(subjName+ " | "  +count+"\n");
        }
    }

    public void Select4() throws SQLException {
        String str = "select *\n" +
                "from student s\n" +
                "where exists (\n" +
                "select *\n" +
                "from subject sb\n" +
                "    where not exists (\n" +
                "    select *\n" +
                "    from progress p\n" +
                "    where p.student_id = s.id\n" +
                "    and p.subject_id = sb.id\n" +
                "    and p.rating > 3)\n" +
                ");";
        PreparedStatement prStatement = conn.prepareStatement(str.toString());
        ResultSet set = prStatement.executeQuery();
        while (set.next()){
            String id = set.getString(1);
            String name = set.getString(2);
            String passport_series = set.getString(3);
            String passport_number = set.getString(4);

            System.out.print(id + " | " + name + " | " + passport_series + " | " + passport_number +"\n");
        }
    }
}
