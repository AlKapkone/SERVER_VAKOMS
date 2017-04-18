package com.orest.kapko.vakoms.project.finished;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.*;
import java.net.*;
import java.text.*;

public class Server {
    private static String line = null;
    protected static final int PORT_NUMBER = 55555;

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        DB.conToDB();
        DB.createDB();
//        DB.updateUser();
//        DB.deleteUser();
//        DB.getAllUsers();
//        DB.closeDB();
        try {
            ServerSocket servsock = new ServerSocket(PORT_NUMBER);
            System.out.println("Server running...");

                Socket sock = servsock.accept();
                System.out.println("Connection from: " + sock.getInetAddress());
                Scanner in = new Scanner(sock.getInputStream());
                BufferedReader inStream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                PrintWriter out = new PrintWriter(sock.getOutputStream());
                String request = in.nextLine();

                while (!inStream.ready()) {

            }

            System.out.print(inStream.readLine());
            System.out.println(request);
            System.out.println("....");
            System.out.print("'\n");
//                Converts JSON string into a collection of Users object.

//            System.out.print(line = in.readLine());
            Gson gson = new Gson();
            Type type = new TypeToken<List<User>>() {}.getType();
            List<User> usersList = gson.fromJson(line, type);
            DB.addNewUser(usersList);
            in.close();
//
//            while (in.hasNext()) {
//                    request = in.nextLine();
//                    System.out.println("Request: " + request);
//
//                    if(request.toUpperCase().equals("TIME")) {
//                        out.println(getTime());
//                        out.flush();
//                    } else if(request.toUpperCase().equals("GET_ALL")) {
//                        out.println(DB.getAllUsers());
//                        out.flush();
//                    } else if(request.toUpperCase().equals("DELETE")) {
//                        out.println(DB.deleteUser(4));
//                        out.flush();
//                    } else {
//                        out.println("Invalid Request...");
//                        out.flush();
//                    }
//                }
//            }

        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
    protected static String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return (dateFormat.format(date));
    }
}

