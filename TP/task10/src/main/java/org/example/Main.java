package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Manager manager = new Manager();

        manager.Connect();


//        manager.CreateTableStudent();
//        manager.CreateTableSubject();
//        manager.CreateTableProgress();
//
//        manager.InsertInStudent();
//        manager.InsertInSubject();
//        manager.InsertInProgress();


        manager.Select0(3, "Математика");
        manager.Select1();
        manager.Select2("Даниил");
        manager.Select3();
        manager.Select4();

        manager.Disconnect();
    }
}