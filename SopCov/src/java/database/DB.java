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

    // JDBC conducteur prenom and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/sopcov";
    static final String DB_TABLE = "utilisateurs";
    // Database credentials
    static final String USER = "prog";
    static final String PASS = "prog";

    static final String TABLE_UTILISATEURS = "utilisateurs";
    static final String TABLE_LIEUX_TRAVAIL = "lieux_travail";

    Connection conn = null;
    Statement stmt = null;

    Connection connTravail = null;
    Statement stmtTravail = null;

    public DB() {
        connect();
    }

    @Override
    public void connect() {
        try {
            //STEP 2: Register JDBC conducteur
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("conn= " + 1);
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.err.println("In DB - connect : " + e.getLocalizedMessage());

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

        String sql;

        //sql = "SELECT email,admin, prenom, nom,password FROM utilisateurs";
        sql = "SELECT email,admin, prenom, nom,password FROM " + TABLE_UTILISATEURS;

        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            System.out.println("+++++++++++++++++++++++++++User DB+++++++++++++++++++++++++++++++\n");
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column prenom
                String email = rs.getString("email");
                int age = rs.getInt("admin");
                String first = rs.getString("prenom");
                String last = rs.getString("nom");
                String password = rs.getString("password");
                //Display values
                System.out.print("email: " + email);
                System.out.print("\t| admin: " + age);
                System.out.print("\t| prenom: " + first);
                System.out.print("\t| nom: " + last);
                System.out.println("\t| Password: " + password);
            }
            System.out.println("\n+++++++++++++++++++++++++++END+++++++++++++++++++++++++++++++");
            //Display values
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> queryInfo(String email) {
        List<User> info = new ArrayList<User>();

        String sql;

        sql = "SELECT * FROM " + TABLE_UTILISATEURS + " Where email='" + email + "'";

        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column prenom

                int admin = rs.getInt("admin");
                int conducteur = rs.getInt("conducteur");
                int notif = rs.getInt("notif");
                String tel = rs.getString("tel");
                String prenom = rs.getString("prenom");
                String nom = rs.getString("nom");
                //String email = rs.getString("email");
                String adresse = rs.getString("adresse");
                String commune = rs.getString("commune");
                String heure_depart = rs.getString("heure_depart");
                String heure_retour = rs.getString("heure_retour");
                int code_postal = rs.getInt("code_postal");
                int lieu_travail_id = rs.getInt("lieu_travail_id");
                String jours_travail = rs.getString("jours_travail");
                //Display values
                info.add(new User(email, "", admin, prenom, nom, tel, adresse, commune, code_postal, lieu_travail_id, heure_depart, heure_retour, jours_travail, conducteur, notif));
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;

    }

    @Override
    public String printHTML() {
        String htmlcode = "<table border='1'><tr><th>Admin</th>";//<th>ID</th>
        htmlcode += "<th>Name</th><th>Lastprenom</th>";
        htmlcode += "<th>email</th><th>adress</th>";
        htmlcode += "<th>commune</th><th>heure_depart</th></tr>";

        String sql;
        sql = "SELECT * FROM " + TABLE_UTILISATEURS;

        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column prenom
                int id = rs.getInt("id");
                int admin = rs.getInt("admin");
                String prenom = rs.getString("prenom");
                String nom = rs.getString("nom");
                String email = rs.getString("email");
                String adress = rs.getString("adresse");
                String commune = rs.getString("commune");
                String heure_depart = rs.getString("heure_depart");
                //Display values

                htmlcode += "<tr>";
                //htmlcode += "<td>" + id + "</td>";
                htmlcode += "<td>" + admin + "</td>";
                htmlcode += "<td>" + prenom + "</td>";
                htmlcode += "<td>" + nom + "</td>";
                htmlcode += "<td>" + email + "</td>";
                htmlcode += "<td>" + adress + "</td>";
                htmlcode += "<td>" + commune + "</td>";
                htmlcode += "<td>" + heure_depart + "</td>";
                htmlcode += "</tr>";
            }
            htmlcode += "</table>";
            // System.out.println(htmlcode);
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return htmlcode;
    }

    @Override
    public int addNewUser(int admin, String prenom, String nom, String password, String tel, String email, String adresse, String commune, int code_postal, int lieu_travail_id, String heure_depart, String heure_retour, String jours_travail, int conducteur, int notif) {

        try {

            String query = " INSERT INTO `utilisateurs` (`tel`, `admin` ,`prenom` ,`nom` ,`password` ,`email` ,`adresse` ,`commune` ,`code_postal` ,`lieu_travail_id` ,`heure_depart` ,`heure_retour` ,`jours_travail` ,`conducteur` ,`notif`)VALUES (";

            //query += "'"+id + "','" +admin+"',";
            query += "'" + tel + "','" + admin + "',";
            query += "'" + prenom + "','" + nom + "',";
            query += "'" + password + "','" + email + "',";
            query += "'" + adresse + "','" + commune + "',";
            query += "'" + code_postal + "','" + lieu_travail_id + "',";
            query += "'" + heure_depart + "','" + heure_retour + "',";
            query += "'" + jours_travail + "','" + conducteur + "',";
            query += "'" + notif + "');";

            //System.out.println(query);
            int rs = stmt.executeUpdate(query);

        } catch (Exception ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 0;
    }

    @Override
    public int addNewUser(boolean admin, String prenom, String nom, String password, String tel, String email, String adresse, String commune, String codePostal, String nomLieuTravail, String heureDepart, String heureRetour, String joursTravail, boolean conducteur, boolean notif) {
        int lieuTravailID = 0;
        int adminConv = (admin)? 1:0;
        int conducteurConv = (conducteur)? 1:0;
        int notifConv = (notif)? 1:0;
        
        try {
            String queryLieuTravail = " SELECT id FROM `" + TABLE_LIEUX_TRAVAIL + "` WHERE `nom_lieu`='" + nomLieuTravail + "'";
            System.out.println("In DB - addNewUser : query : " + queryLieuTravail);
            ResultSet resSet = stmt.executeQuery(queryLieuTravail);

            if (resSet.next()) {
                lieuTravailID = resSet.getInt("id");
            } else {
                System.err.println("In DB - addNewUser : n'a pas pu récupérer l'ID associé au lieu de travail " + nomLieuTravail);
                return -1;
            }

        } catch (Exception ex) {
            System.err.println("In DB - addNewUser : n'a pas pu accéder à l'id du lieu de travail : " + ex.getLocalizedMessage());
            return -2;
        }
        try {
            String queryCreeUtilisateur = " INSERT INTO `" + TABLE_UTILISATEURS + "` (`tel`, `admin` ,`prenom` ,`nom` ,`password` ,`email` ,`adresse` ,`commune` ,`code_postal` ,`lieu_travail_id` ,`heure_depart` ,`heure_retour` ,`jours_travail` ,`conducteur` ,`notif`)VALUES (";

            //query += "'"+id + "','" +admin+"',";
            queryCreeUtilisateur += "'" + tel + "','" + adminConv + "',";
            queryCreeUtilisateur += "'" + prenom + "','" + nom + "',";
            queryCreeUtilisateur += "'" + password + "','" + email + "',";
            queryCreeUtilisateur += "'" + adresse + "','" + commune + "',";
            queryCreeUtilisateur += "'" + codePostal + "','" + lieuTravailID + "',";
            queryCreeUtilisateur += "'" + heureDepart + "','" + heureRetour + "',";
            queryCreeUtilisateur += "'" + joursTravail + "','" + conducteurConv + "',";
            queryCreeUtilisateur += "'" + notifConv + "');";

            System.out.println("In DB - addNewUser : query : " + queryCreeUtilisateur);

            //System.out.println(query);
            int rs = stmt.executeUpdate(queryCreeUtilisateur);

        } catch (Exception ex) {
            System.err.println("In DB - addNewUser : n'a pas pu créer l'utilisateur : " + ex.getLocalizedMessage());
            return -3;
        }
        return 0;
    }

    @Override
    public void deleteUser(String email, String prenom, String nom) {
        try {

            String query = "DELETE FROM " + TABLE_UTILISATEURS + " WHERE email=" + email + " AND nom='" + nom + "' AND prenpm='" + prenom + "' ;";

            //System.out.println(querry);
            int rs = stmt.executeUpdate(query);

        } catch (Exception ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public String getPassword(String email) {
        String pass = "";

        String sql;

        sql = "SELECT password FROM " + TABLE_UTILISATEURS + " WHERE email='" + email + "'";

        //System.out.println(sql);
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            rs.next();
            pass = rs.getString("password");
            rs.close();

        } catch (Exception e) {
            // Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
        }

        return pass;
    }

    @Override
    public boolean editLocation(String email, int lieu_travail_id) {

        String sql = "UPDATE " + TABLE_UTILISATEURS + " SET lieu_travail_id='" + lieu_travail_id + "' WHERE email='" + email + "'";

        int rs;
        try {
            rs = stmt.executeUpdate(sql);

        } catch (Exception e) {
            // Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteLocation(String email, String newlieu_travail_id) {

        String sql = "UPDATE " + TABLE_UTILISATEURS + " SET lieu_travail_id='" + "' WHERE email='" + email + "'";

        int rs;
        try {
            rs = stmt.executeUpdate(sql);

        } catch (Exception e) {
            // Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public void setPassword(String email, String password) {

        String sql;

        sql = "UPDATE " + TABLE_UTILISATEURS + " SET password='" + password + "' WHERE email='" + email + "'";

        //  System.out.println(sql);
        int rs;
        try {
            rs = stmt.executeUpdate(sql);

        } catch (Exception e) {
            // Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public boolean emailAlreadyUsed(String email) {
        boolean emailAlreadyUsed = false;

        String sql;
        sql = "SELECT email FROM " + TABLE_UTILISATEURS + " WHERE email='" + email + "'";
        ResultSet rs;

        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                emailAlreadyUsed = true;
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("In DB - emailAlreadyUsed : N'a pas pu voir si l'utilisateur existait ou non : " + e.getLocalizedMessage());
        }
        return emailAlreadyUsed;
    }

    @Override
    public boolean userExists(String email, String password) {
        boolean userExists = false;

        String sql;
        sql = "SELECT email,password FROM " + TABLE_UTILISATEURS + " WHERE email='" + email + "' AND password='" + password + "'";
        // System.out.println(sql);
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                userExists = true;
            }
            rs.close();

        } catch (Exception e) {
            // Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
        }
        return userExists;
    }

    @Override
    public List<User> getAllDrivers() {
        List<User> conducteurs = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_UTILISATEURS + " WHERE conducteur=1";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column prenom
                int admin = rs.getInt("admin");
                int conducteur = rs.getInt("conducteur");
                int notif = rs.getInt("notif");
                String tel = rs.getString("tel");
                String prenom = rs.getString("prenom");
                String nom = rs.getString("nom");
                String email = rs.getString("email");
                String adresse = rs.getString("adresse");
                String commune = rs.getString("commune");
                String heure_depart = rs.getString("heure_depart");
                String heure_retour = rs.getString("heure_retour");
                int code_postal = rs.getInt("code_postal");
                int lieu_travail_id = rs.getInt("lieu_travail_id");
                String jours_travail = rs.getString("jours_travail");
                //Display values
                conducteurs.add(new User(email, "", admin, prenom, nom, tel, adresse, commune, code_postal, lieu_travail_id, heure_depart, heure_retour, jours_travail, conducteur, notif));
            }

        } catch (Exception e) {

        }
        return conducteurs;
    }

    @Override
    public List<User> searchRoute(String mCity, String lieu_travail) {
        List<User> routes = new ArrayList<>();

        String sql_lieu = "SELECT * FROM " + TABLE_LIEUX_TRAVAIL + " Where nom_lieu='" + lieu_travail + "'";

        ResultSet rs;
        try {

            rs = stmt.executeQuery(sql_lieu);
            rs.next();
            int lieu_travail_id = rs.getInt("id");
            System.out.println(lieu_travail + "," + lieu_travail_id);
            String sql = "SELECT * FROM " + TABLE_UTILISATEURS + " Where conducteur=1 AND commune='" + mCity + "' AND lieu_travail_id='" + lieu_travail_id + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column prenom

                int admin = rs.getInt("admin");
                int conducteur = rs.getInt("conducteur");
                int notif = rs.getInt("notif");
                String tel = rs.getString("tel");
                String prenom = rs.getString("prenom");
                String nom = rs.getString("nom");
                String email = rs.getString("email");
                String adresse = rs.getString("adresse");
                String commune = rs.getString("commune");
                String heure_depart = rs.getString("heure_depart");
                String heure_retour = rs.getString("heure_retour");
                int code_postal = rs.getInt("code_postal");
                String jours_travail = rs.getString("jours_travail");

                //Display values
                routes.add(new User(email, "", admin, prenom, nom, tel, adresse, commune, code_postal, code_postal, heure_depart, heure_retour, jours_travail, conducteur, notif));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return routes;
    }

//test addUser, ShowDatabase,deleteUSer...
    public static void main(String[] args) {
        DB dbHelper = new DB();
        // dbHelper.
        // String password = dbHelper.getPassword("simpleuser@test.com");
        // System.out.println("Password is " + password);
        //dbHelper.setPassword("adminuser@test.com","adminuser");
        //dbHelper.editLocation("ghader@etud.insa-toulouse.fr", "Balma");
        //System.out.println(dbHelper.userExists("adminuser@test.com", "adminuser"));
        //dbHelper.addNewUser(0, "omar", "ghader","pass","07", "og@insa.fr", "av rang", "Toulouse", 31400, 2, "08:00:00","17:00:00", "L,M,M,J,V", 1, 1);
        //System.out.println(dbHelper.searchRoute("Toulouse", "Sopra_Group_Ent2").toString());
        dbHelper.listData();
        
          // Get the parameters to change password
            String mail= "adminuser@test.com";
            String apwd = "adminuser2";
            String npwd = "adminuser";
            String rnpwd = "adminuser";

            DB database=new DB();

            if(!database.getPassword(mail).equals(apwd)){
                System.out.println("Old Password not correct!");
            }
            else{
                //The old password is correct
                if(npwd.length()<8){
                    //Password length < 8 carachters
                    System.out.println("New Password not correct!");
                }
                else{
                    if(!npwd.equals(rnpwd)){
                        //Not same repeated Password
                        System.out.println("Please enter the same password twice!");
                    }
                    else{
                        database.setPassword(mail, npwd);
                       //String destination="changePass.jsp";
                        System.out.println("ChangePass");
                    }
                }
            }
            
            dbHelper.listData();
        dbHelper.closeConnection();
    }//end main

    @Override
    public ArrayList<String> getAllWorkplaces() {
        String nomLieu = "nom_lieu";

        ArrayList<String> res = new ArrayList<String>() {
        };

        String sql = "SELECT " + nomLieu + " FROM " + TABLE_LIEUX_TRAVAIL;
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                res.add(rs.getString(nomLieu));
            }
        } catch (SQLException ex) {
            System.err.println("In DB - getAllWorkplaces : pas pu lire les résultats de la requete " + sql + "\nerreur : " + ex.getLocalizedMessage());
        }

        return res;
    }
}
