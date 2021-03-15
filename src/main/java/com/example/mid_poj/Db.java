package com.example.mid_poj;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "db", value = "/db")
public class Db extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter wr = response.getWriter();
        wr.println("HAHA");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/udb",
                    "postgres",
                    "123");
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("SELECT login from users");

            while(rs.next()){
                wr.println(rs.getString("login"));
            }
            sta.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void destroy() {
    }
}