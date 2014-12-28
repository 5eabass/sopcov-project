/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class DB implements DBInterface {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sopcov";
    // Database credentials
    static final String USER = "root";
    static final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    public DB() {

    }

    @Override
    public void connect() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (Exception e) {
        }

    }

    @Override
    public void closeConnection() {
        try {
            // rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

    }

    @Override
    public void listData() {
        connect();
        String sql;
        sql = "SELECT id, admin, name, lastname,password FROM users";

        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            System.out.println("+++++++++++++++++++++++++++User DB+++++++++++++++++++++++++++++++\n");
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("admin");
                String first = rs.getString("name");
                String last = rs.getString("lastname");
                String password = rs.getString("password");
                //Display values
                System.out.print("ID: " + id);
                System.out.print("\t| admin: " + age);
                System.out.print("\t| name: " + first);
                System.out.print("\t| Lastname: " + last);
                System.out.println("\t| Password: " + password);
            }
            System.out.println("\n+++++++++++++++++++++++++++END+++++++++++++++++++++++++++++++");
            //Display values
            rs.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> queryInfo(String email) {
        List<User> info = new ArrayList<User>();
        connect();
        String sql;
        //sql = "SELECT id, admin, name, lastname,workplace,deptime,rettime,email FROM users Where email='" + email + "'";
        sql = "SELECT * FROM users Where email='" + email + "'";

        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                int admin = rs.getInt("admin");
                String tel = rs.getString("tel");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                //String email = rs.getString("email");
                String address = rs.getString("address");
                String city = rs.getString("city");
                int deptime = rs.getInt("deptime");
                String workplace = rs.getString("workplace");
                int retttime = rs.getInt("rettime");
                int postalcode = rs.getInt("postalcode");
                String workdays = rs.getString("workdays");
                //Display values
                info.add(new User(name, lastname, "", tel, email, address, city, postalcode, workplace, deptime, retttime, workdays, true, true));
            }
            rs.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;

    }

    @Override
    public String printHTML() {
        String htmlcode = "<table border='1'><tr><th>Admin</th>";//<th>ID</th>
        htmlcode += "<th>Name</th><th>Lastname</th>";
        htmlcode += "<th>email</th><th>adress</th>";
        htmlcode += "<th>city</th><th>deptime</th></tr>";
        connect();
        String sql;
        sql = "SELECT * FROM users";

        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                int admin = rs.getInt("admin");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String adress = rs.getString("address");
                String city = rs.getString("city");
                String deptime = rs.getString("deptime");
                //Display values

                htmlcode += "<tr>";
                //htmlcode += "<td>" + id + "</td>";
                htmlcode += "<td>" + admin + "</td>";
                htmlcode += "<td>" + name + "</td>";
                htmlcode += "<td>" + lastname + "</td>";
                htmlcode += "<td>" + email + "</td>";
                htmlcode += "<td>" + adress + "</td>";
                htmlcode += "<td>" + city + "</td>";
                htmlcode += "<td>" + deptime + "</td>";
                htmlcode += "</tr>";
            }
            htmlcode += "</table>";
            // System.out.println(htmlcode);
            rs.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return htmlcode;
    }

    @Override
    public int addNewUser(int admin, String name, String lastname, String password, String tel, String email, String address, String city, int postalcode, String workplace, int deptime, int rettime, String workdays, int driver, int notif) {

        try {
            connect();
            String query = " INSERT INTO `users` (`tel`, `admin` ,`name` ,`lastname` ,`password` ,`email` ,`address` ,`city` ,`postalcode` ,`workplace` ,`deptime` ,`rettime` ,`workdays` ,`driver` ,`notif`)VALUES (";

            //query += "'"+id + "','" +admin+"',";
            query += "'" + tel + "','" + admin + "',";
            query += "'" + name + "','" + lastname + "',";
            query += "'" + password + "','" + email + "',";
            query += "'" + address + "','" + city + "',";
            query += "'" + postalcode + "','" + workplace + "',";
            query += "'" + deptime + "','" + rettime + "',";
            query += "'" + workdays + "','" + driver + "','" + notif + "');";

            //System.out.println(query);
            int rs = stmt.executeUpdate(query);
            closeConnection();
        } catch (Exception ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 0;
    }

    @Override
    public void deleteUser(int id, String name, String lastname) {
        try {
            connect();
            String query = "DELETE FROM users WHERE id=" + id + " AND name='" + name + "' AND lastname='" + lastname + "' ;";
            //System.out.println(querry);
            int rs = stmt.executeUpdate(query);
            closeConnection();
        } catch (Exception ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public String getPassword(String email) {
        String pass = "";
        connect();
        String sql;
        sql = "SELECT password FROM users WHERE email='" + email + "'";
        //System.out.println(sql);
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            rs.next();
            pass = rs.getString("password");
            rs.close();
            closeConnection();
        } catch (Exception e) {
            // Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
        }

        return pass;
    }

    @Override
    public boolean editLocation(String email, String newWorkplace) {
        connect();
        String sql = "UPDATE users SET workplace='" + newWorkplace + "' WHERE email='" + email + "'";
        int rs;
        try {
            rs = stmt.executeUpdate(sql);
            closeConnection();
        } catch (Exception e) {
            // Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    public boolean deleteLocation(String email, String newWorkplace) {
        connect();
        String sql = "UPDATE users SET workplace='" + "' WHERE email='" + email + "'";
        int rs;
        try {
            rs = stmt.executeUpdate(sql);
            closeConnection();
        } catch (Exception e) {
            // Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public void setPassword(String email, String password) {
        connect();
        String sql;
        sql = "UPDATE users SET password='" + password + "' WHERE email='" + email + "'";
        //  System.out.println(sql);
        int rs;
        try {
            rs = stmt.executeUpdate(sql);

            closeConnection();
        } catch (Exception e) {
            // Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public boolean userExists(String email, String password) {
        boolean userExists = false;
        connect();
        String sql;
        sql = "SELECT email,password FROM users WHERE email='" + email + "' AND password='" + password + "'";
        // System.out.println(sql);
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                userExists = true;
            }
            rs.close();
            closeConnection();
        } catch (Exception e) {
            // Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
        }
        return userExists;
    }

    @Override
    public List<User> getAllDrivers() {
        List<User> drivers = new ArrayList<>();
        connect();
        String sql = "SELECT * FROM users WHERE driver=1";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                int admin = rs.getInt("admin");
                String tel = rs.getString("tel");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String city = rs.getString("city");
                int deptime = rs.getInt("deptime");
                String workplace = rs.getString("workplace");
                int retttime = rs.getInt("rettime");
                int postalcode = rs.getInt("postalcode");
                String workdays = rs.getString("workdays");
                //Display values
                drivers.add(new User(name, lastname, "", tel, email, address, city, postalcode, workplace, deptime, retttime, workdays, true, true));
            }

        } catch (Exception e) {

        }
        return drivers;
    }

    @Override
    public List<User> searchRoute(String mCity, String mWorkplace) {
        List<User> routes = new ArrayList<>();
        connect();
        String sql = "SELECT * FROM users Where driver=1 AND city='" + mCity + "' AND workplace='" + mWorkplace + "'";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                //int admin = rs.getInt("admin");
                String tel = rs.getString("tel");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String city = rs.getString("city");
                int deptime = rs.getInt("deptime");
                String workplace = rs.getString("workplace");
                int retttime = rs.getInt("rettime");
                int postalcode = rs.getInt("postalcode");
                String workdays = rs.getString("workdays");
                //Display values
                routes.add(new User(name, lastname, "", tel, email, address, city, postalcode, workplace, deptime, retttime, workdays, true, true));
            }

        } catch (Exception e) {

        }
        return routes;
    }

//test addUser, ShowDatabase,deleteUSer...
    public static void main(String[] args) {
        DB dbHelper = new DB();
        //dbHelper.showDatabase();
        //dbHelper.deleteUser(1, "Om", "GH");
        //System.out.println("\n\n");
        dbHelper.addNewUser(0, "Ernesto", "Exposito", "ernesto", "0712345678", "exposito@etud.insa-toulouse.fr", "135 av.R", "Paris", 31400, "Bordeaux", 8, 19, "Lundi,Mardi,Jeudi,Vendredi", 1, 1);
        //dbHelper.setPassword("so@so.fr","abo");
        //dbHelper.editLocation("ghader@etud.insa-toulouse.fr", "Balma");
        //System.out.println(dbHelper.userExists("so@so.fr", "12345"));
        //System.out.println(dbHelper.getPassword("so@so.fr"));
        dbHelper.listData();
    }//end main

}
