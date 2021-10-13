package sample;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Connector {

    public Connector() {
        connectorMySql();
        //createTables();
    }

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    String serverName = "127.0.0.1";
    String mydatabase = "MedicineStorage";
    public final String DB_URL = "jdbc:mysql://" + serverName + "/" + mydatabase + "?user=root";
    private Connection conn;
    private Statement stat;

    //■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
    public boolean connectorMySql() {
        try {
            Class.forName(Connector.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection Failed");
            e.printStackTrace();
            return false;
        }
    }

    //■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Close connection error");
            e.printStackTrace();
        }
    }

    //■■■■■■■■■■■■■■■■CREATE■■■■■■■■■■■■■■■■■■■■■■
    public boolean createTables() {
        String createUsers = "CREATE TABLE IF NOT EXISTS users (id_user INTEGER PRIMARY KEY AUTO_INCREMENT, name varchar(255), surname varchar(255), login varchar(255), password varchar(255))";
        String createProduct = "CREATE TABLE IF NOT EXISTS products (id_product INTEGER PRIMARY KEY AUTO_INCREMENT, title varchar(255), barcode varchar(255), expireDate varchar(255))";
        String createFridge = "CREATE TABLE IF NOT EXISTS fridge (id_fridge INTEGER PRIMARY KEY AUTO_INCREMENT, id_user int, id_product int)";
        try {
            stat.execute(createUsers);
            stat.execute(createProduct);
            stat.execute(createFridge);
        } catch (SQLException e) {
            System.err.println("Table create error");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //■■■■■■■■■■■■■■■INSERT■■■■■■■■■■■■■■■■■■■■■■■

    public boolean insertMedication(String name, String ean1) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into medication values (NULL, ?, ?,0,0,0,0);");
            prepStmt.setString(1, name);
            prepStmt.setString(2, ean1);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Insert error");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //■■■■■■■■■■■■■■■DELETE■■■■■■■■■■■■■■■■■■■■■■■

    public boolean deleteMedication(String ean1) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "DELETE FROM medication WHERE ean1 = ?;");
            prepStmt.setString(1, ean1);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Delete error");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //■■■■■■■■■■■■■■■■■■SELECT■■■■■■■■■■■■■■■■■■■■
    public List<Medication> selectMedication() {
        List<Medication> usersMedication = new LinkedList<Medication>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM medication");
            int id;
            String name, ean1, ean2, ean3, ean4, ean5;
            while (result.next()) {
                id = result.getInt("id");
                name = result.getString("name");
                ean1 = result.getString("ean1");
                ean2 = result.getString("ean2");
                ean3 = result.getString("ean3");
                ean4 = result.getString("ean4");
                ean5 = result.getString("ean5");

                usersMedication.add(new Medication(id, name, ean1, ean2, ean3, ean4, ean5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return usersMedication;
    }

    //■■■■■■■■■■■■■■■Execute SQL■■■■■■■■■■■■■■■■■■■■■■■

    public boolean executeSQL(String sql) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(sql);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Execute statement error");
            e.printStackTrace();
            return false;
        }
        return true;
    }

}