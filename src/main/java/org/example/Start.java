package org.example;

import connection.ConnectionFactory;
import presentation.Controller;
import presentation.MainFrame;

import java.sql.Connection;

public class Start {
    public static void main(String[] args) {
        new Controller(new MainFrame());
    }
}
//
//public class Start {
//    public static void main(String[] args) {
//        try {
//            Connection conn = ConnectionFactory.getConnection();
//            System.out.println("Conexiune reușită!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
