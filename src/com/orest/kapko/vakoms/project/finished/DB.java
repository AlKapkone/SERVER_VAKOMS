package com.orest.kapko.vakoms.project.finished;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПІДКЛЮЧЕННЯ ДО БАЗИ ДАНИХ--------
    public static void conToDB() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:Test.s3db");

        System.out.println("База Підключена!");
    }

    // --------Створення таблиці--------
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'Name' text, 'Email' text);");

        System.out.println("Таблицю створено або така уже існує.");
    }

    // --------Оновлення інфи в таблиці--------
    public static void updateUser() throws SQLException
    {
        statmt.executeUpdate("UPDATE 'users' SET name = 'Andriy', Email = 'andy_fox@gmail.com' WHERE id = 3");

        System.out.println("Інформацію про користувача оновлено");
    }

    // --------Видалення користувача з таблиці--------
    public static String deleteUser(int respId) throws SQLException
    {
        statmt.execute("DELETE FROM 'users' WHERE id = ('"+respId+"')");
        String delResult = "Користувача з ID = " + respId + "видалено";
        System.out.println("Користувача видалено");
        return delResult;
    }

    // --------Додавання нового користувача в таблицю--------
    public static void addNewUser(List<User> usersList) throws SQLException {
        for (User user : usersList) {
            String name = user.getName();
            String email = user.getEmail();
            statmt.execute("INSERT INTO 'users' ('name', 'Email') VALUES ('"+name+"','"+email+"');");
        }
        System.out.println("Користувача додано");
    }

    // -------- Вивід таблиці--------
    public static JSONObject getAllUsers() throws ClassNotFoundException, SQLException, JSONException {
        resSet = statmt.executeQuery("SELECT * FROM users");
        JSONObject json = new JSONObject();
        while (resSet.next()) {
            int id = resSet.getInt("id");
            String name = resSet.getString("name");
            String email = resSet.getString("Email");

            json.put(name.toString(), email.toString());
            System.out.println("ID = " + id );
            System.out.println("name = " + name );
            System.out.println("Email = " + email);
            System.out.println();
        }
        System.out.println("Таблицю виведено" + json);
        return json;
    }
    // --------Закриття--------
    public static void closeDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();
        System.out.println("Всі з'єднання успішно закрито");
    }
}
