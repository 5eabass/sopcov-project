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
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sopcov.mail.MailSender;

/**
 *
 * @author root
 */
public class DB implements DBInterface {
    
    // JDBC conducteur prenom and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/sopcov";
    // Database credentials
    static final String USER = "prog";
    static final String PASS = "prog";
    
    static final String TABLE_UTILISATEURS = "utilisateurs";
    static final String TABLE_LIEUX_TRAVAIL = "lieux_travail";
    static final String TABLE_VISITES = "visites";
    
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
        
    public void notifyUsers(String email, boolean isAdded){
        
        // on récupère les info qui sont utiles : la commune et le lieu de travail pour trouver le trajet
        // mais aussi si le user est conducteur , (si non alors il n'apporte rien de plus)
        String query = "SELECT conducteur,code_postal,lieu_travail_id FROM " + TABLE_UTILISATEURS + " WHERE email='"+email+"'";
        ResultSet rs;
        int conducteur =0;
        int id_lieu_travail = 0;
        int code_postal = 0;
        
        try{
            // récupération des valeurs
            rs = stmt.executeQuery(query);
            rs.next();
            conducteur = rs.getInt("conducteur");
            code_postal = rs.getInt("code_postal");
            id_lieu_travail = rs.getInt("lieu_travail_id");
            
        }catch(Exception e){
            System.err.println("In DB  - notify users: could not send the query 1 : error "+e);
        }
        
        //si le user est un conducteur on envoit les messages nécessaires
        if (conducteur==1){
            String nomLieuTravail = "";
            // on cherche le nom du lieu de travail (on a que sont id)
            String queryLieuTravail = "SELECT nom_lieu FROM " + TABLE_LIEUX_TRAVAIL + " WHERE id=" + id_lieu_travail;
            
            try{
                rs = stmt.executeQuery(queryLieuTravail);
                rs.next();
                nomLieuTravail = rs.getString("nom_lieu");
            }catch(Exception e){
                System.err.println("In DB  - notify users: could not send the query 2 : error "+e);
            }
            
            
            MailSender mailSender = new MailSender();
            // on choisit la notification en fonction de si le user à été rajouté ou supprimé
            mailSender.setEmailText(isAdded);
            mailSender.setList(getEmailToBeNotified(code_postal,nomLieuTravail));
            mailSender.start();
        }
        
        
    }
    
    @Override
    public User queryInfo(String email) {
        User info = null;
        
        String sql_lieu = "SELECT * FROM " + TABLE_LIEUX_TRAVAIL;
        
        ResultSet rs;
        try {
            
            rs = stmt.executeQuery(sql_lieu);
            rs.next();
            String lieu_travail = rs.getString("nom_lieu");
            String lieu_travail_adresse = rs.getString("adresse");
            String sql = "SELECT * FROM " + TABLE_UTILISATEURS + " Where email='" + email + "'";
            
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
                
                info = new User(email, "", admin, prenom, nom, tel, adresse, commune, code_postal, lieu_travail, lieu_travail_id, heure_depart, heure_retour, jours_travail, conducteur, notif, lieu_travail_adresse);
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
    
    /*
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
    */
    
    @Override
    public int addNewUser(boolean admin, String prenom, String nom, String password, String tel, String email, String adresse, String commune, String codePostal, String nomLieuTravail, String heureDepart, String heureRetour, String joursTravail, boolean conducteur, boolean notif) {
        int lieuTravailID = 0;
        int adminConv = (admin) ? 1 : 0;
        int conducteurConv = (conducteur) ? 1 : 0;
        int notifConv = (notif) ? 1 : 0;
        
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
            
            // envoie des mails en fonction des modification apporté par le nouveau user :
            notifyUsers(email,true);
            
            
        } catch (Exception ex) {
            System.err.println("In DB - addNewUser : n'a pas pu créer l'utilisateur : " + ex.getLocalizedMessage());
            return -3;
        }
        return 0;
    }
    
    @Override
    public void deleteUser(String email) {
        
        String deleteVisiteQuery = "DELETE FROM " + TABLE_VISITES + " WHERE email_visiteur='" + email +"'";
        String deleteUserQuery = "DELETE FROM " + TABLE_UTILISATEURS + " WHERE email='" + email +"'";
        
        // on notifie avant la deletion car sinon on ne trouve plus les info nécessaire
        notifyUsers(email,false);
        int rs = 0;
        
        try {
           //suppression des visites de l'user
            rs = stmt.executeUpdate(deleteVisiteQuery);
       } catch (Exception ex) {
            System.err.println("could not delete visites :"+ ex);
            
        }
        
        try{
         //suppression de l'user
            rs = stmt.executeUpdate(deleteUserQuery);
        } catch (Exception ex) {
            System.err.println("could not delete user :" + ex);
            
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
    public void setAdminRight(String email, String admin){      
        int adminRight;
        
        if (admin != null) {
            adminRight = 1;
        } else {
            adminRight = 0;
        }
        
        String sql = "UPDATE " + TABLE_UTILISATEURS + " SET admin='" + adminRight + "' WHERE email='" + email + "'";
         try {
            int rs = stmt.executeUpdate(sql);
            
        } catch (Exception e) {   
             System.err.println("IN dDB - setAdminRight - could not do the query error :" + e);
        }
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
    public boolean editWorkLocation(int lieu_travail_id, String newNom_lieu, String adresse,String commune, String code_postal) {
        
        String sql = "UPDATE " + TABLE_LIEUX_TRAVAIL + " SET nom_lieu='" + newNom_lieu + "' , adresse='"+ adresse+ "', code_postal='"+code_postal +"'  , commune='"+ commune +"'  WHERE id=" + lieu_travail_id + "";
        
        int rs;
        try {
            rs = stmt.executeUpdate(sql);
            
        } catch (Exception e) {
             Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean deleteWorkLocation(int id, String newlieu_travail_nom) {
        
      String sql = "DELETE FROM " + TABLE_LIEUX_TRAVAIL + " WHERE nom_lieu='" + newlieu_travail_nom + "' AND id='"+ id+"';";
        
        int rs;
        try {
            rs = stmt.executeUpdate(sql);
            
        } catch (Exception e) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, e);
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
    public void editUserProfile(String email, String nom, String prenom, String adresse,
            String tel, String commune, String code_postal, String lieu_travail,
            String heure_depart, String heure_retour, String jours_travail, String conducteur, String notif){
        
        // récupération des bonnes valeurs par rapport à la DB : string -> int ...
        
        // cas des boolean
        int valConducteur;
        int valNotif;
        
        if (conducteur != null) {
            valConducteur = 1;
        } else {
            valConducteur = 0;
        }
        
        if (notif != null){
            valNotif = 1;
        } else {
            valNotif = 0;
        }
        
        // cas du lieu de travail
        int lieuTravailID;
        if (lieu_travail.contains("Ent2")) {
            lieuTravailID = 2;
        } else {
            lieuTravailID = 1;
        }
        
        // récupération des anciennes données utiles pour savoir si on envoie un mail :
        
        String oldDataSql ;
        ResultSet result ;
        int oldConducteur;
        String oldCommune;
        int oldLieu_travail;
       
        oldDataSql = "SELECT commune,lieu_travail_id,conducteur FROM " + TABLE_UTILISATEURS + " WHERE email ='" + email + "'";
        
        try{
            result = stmt.executeQuery(oldDataSql);
            result.next();
            oldConducteur = result.getInt("conducteur");
            oldCommune = result.getString("commune");
            oldLieu_travail = result.getInt("lieu_travail_id");
            
            // gestion mail 
            
            boolean toNotify = false ;
            if (oldConducteur != valConducteur){
               if(valConducteur != 1){                 
                    // si on est plus conducteur : on notifie le groupe impliqué par notre ancien trajet
                    notifyUsers(email, false);                  
                }else {
                    // sinon on devient conducteur donc on devra notifier le nouveau groupe impliqué par notre trajet
                    toNotify = true;
                }
            }else {
                // si la valeur conducteur n'a pas changé ( on regarde juste les lieux -> et on notifie)
                // a noter que si conducteur = 0 alors dans notifyUsers on ne fait rien
                // car cela n'apporte rien à la communauté
                System.out.println("entered in : conducteur =");
                System.out.println("les communes : " + oldCommune +  " /  " + commune + " ///// les lieuTravailID : "+ oldLieu_travail + " / " + lieuTravailID);
                if ( !(oldCommune.equals(commune)) || (lieuTravailID!=oldLieu_travail)){
                    System.out.println("entered in commune =! or lieuTravail !=");
                    // on notifie au groupe impliqué par notre ancien trajet qu'on est plus sur ce trajet                 
                    notifyUsers(email, false);
                    //on devra notifier notre nouveau groupe qu'on est conducteur sur leur trajet
                    toNotify = true;
                }
            }
            
            
            
           // on fait nos changement
            
            String sqlUpdate;
            
            sqlUpdate = "UPDATE " + TABLE_UTILISATEURS + " SET "
                    + "nom='"+nom+"' , "
                    + "prenom='"+prenom+"' , "
                    + "adresse='"+adresse+"' , "
                    + "tel='"+tel+"' , "
                    + "commune='"+commune+"' , "
                    + "code_postal='"+code_postal+"' , "
                    + "lieu_travail_id='"+lieuTravailID+"' , "
                    + "heure_depart='"+heure_depart+"' , "
                    + "heure_retour='"+heure_retour+"' , "
                    + "jours_travail='"+jours_travail+"' , "
                    + "conducteur='"+valConducteur+"' , "
                    + "notif='"+valNotif+"' "
                    + "WHERE email='" + email + "'";
       
        
            int rs ;
            rs = stmt.executeUpdate(sqlUpdate);
 
            if (toNotify){
                // on notifie le groupe impliqué par notre NOUVEAU trajet               
                notifyUsers(email, true);
            }
            
        }catch(Exception e){
            System.err.println("In DB - setuserfield - could not query");
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
    
    /**
     *
     * @param email
     * @return
     */
    @Override
    public boolean isUSerAdmin(String email) {
        boolean isAdmin = false;
        
        String sql;
        sql = "SELECT admin FROM " + TABLE_UTILISATEURS + " WHERE email='" + email + "'";
        ResultSet rs;
        
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if(rs.getInt("admin")==1){
                    isAdmin = true;
                }
                
            }
            rs.close();
            
        } catch (SQLException e) {
            return false;
            //System.err.println("In DB - emailAlreadyUsed : N'a pas pu voir si l'utilisateur existait ou non : " + e.getLocalizedMessage());
        }
        return isAdmin;
    }
    
    
    @Override
    public void rememberUserLogIn(String email) {
        String sql;
        sql = "INSERT INTO " + TABLE_VISITES + " (`email_visiteur`,`date`) VALUES ";
        sql += "(\""+email+"\",NOW())";
        
        try {
            stmt.executeUpdate(sql);
        }
        catch (SQLException ex) {
            System.err.println("In DB - rememberUserLogIn : N'a pas pu enregistrer que l'utilisateur s'est loggé."
                    + " Erreur : " + ex.getLocalizedMessage());
        }
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
    
    public boolean isUserInDB(String email){
        boolean isUserInDB = false;
        
        String sql;
        sql = "SELECT email FROM " + TABLE_UTILISATEURS + " WHERE email='" + email + "'";
        // System.out.println(sql);
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                isUserInDB = true;
            }
            rs.close();
            
        } catch (Exception e) {
            System.err.println("In DB - isUserExist - could not query error : " + e);
        }
        return isUserInDB;
    }
    
    @Override
    public List<User> getAllDrivers() {
        List<User> conducteurs = new ArrayList<>();
        String lieu_travail_nom;
        String lieu_travail_adresse;
        String sql_lieu = "SELECT * FROM " + TABLE_LIEUX_TRAVAIL;
        
        ResultSet rs;
        try {
            
            rs = stmt.executeQuery(sql_lieu);
            rs.next();
            String lieu_travail_nom1 = rs.getString("nom_lieu");
            String lieu_travail_adresse1 = rs.getString("adresse");
            rs.next();
            String lieu_travail_nom2 = rs.getString("nom_lieu");
            String lieu_travail_adresse2 = rs.getString("adresse");
            String sql = "SELECT * FROM " + TABLE_UTILISATEURS + " WHERE conducteur=1";
            
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
                if (lieu_travail_id == 1) {
                    lieu_travail_nom = lieu_travail_nom1;
                    lieu_travail_adresse = lieu_travail_adresse1;
                } else {
                    lieu_travail_nom = lieu_travail_nom2;
                    lieu_travail_adresse = lieu_travail_adresse2;
                }
                conducteurs.add(new User(email, "", admin, prenom, nom, tel, adresse, commune, code_postal, lieu_travail_nom, lieu_travail_id, heure_depart, heure_retour, jours_travail, conducteur, notif, lieu_travail_adresse));
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
            String lieu_travail_nom = rs.getString("nom_lieu");
            String lieu_travail_adresse = rs.getString("adresse");
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
                routes.add(new User(email, "", admin, prenom, nom, tel, adresse, commune, code_postal, lieu_travail_nom, lieu_travail_id, heure_depart, heure_retour, jours_travail, conducteur, notif, lieu_travail_adresse));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routes;
    }
    
    public List<String> getEmailToBeNotified(int code_postal ,String dest){
        List<String> resultEnd = new ArrayList<>();
        // on récupère les info du lieu de travail
        String sqlLieuTravail = "SELECT id FROM " + TABLE_LIEUX_TRAVAIL + " Where nom_lieu='" + dest + "'";
        ResultSet rs;
        
        try{
            rs = stmt.executeQuery(sqlLieuTravail);
            rs.next();
            //on récupère l'ID du lieu de travail en entée de fonction (dest)
            int lieu_travail_id = rs.getInt("id");
            
            //on cherche les emails des personnes qui sont sur le trajet (commune->lieu travail)
            String sqlEmail = "SELECT email FROM " + TABLE_UTILISATEURS + " WHERE code_postal='" + code_postal + "' AND lieu_travail_id='" + lieu_travail_id + "' AND notif='1'";
            ResultSet resEmails;
            resEmails = stmt.executeQuery(sqlEmail);
            
            // on place nos resultats dans notre list final
            while (resEmails.next()) {
                resultEnd.add(resEmails.getString("email"));
            }
            
        }catch(Exception ex){
            System.err.println("In DB - getEmailToBeNotified : could not process query, error :"+ex);
        }
        return resultEnd;
    }
    
    
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
    
    @Override
    public int getNumberOfConnectionBetween(String dateDeb, String heureDeb, String dateFin, String heureFin) {
        int nbrCon = -1;
        String colonne = "nbr_visite";
        
        //Petite vérification du format des dates & heures.
        String[] verifArgu = dateDeb.split("-");
        if (verifArgu.length != 3) {
            System.err.println("In DB - getNumberOfConnectionBetween : dateDeb n'est pas au format AAAA-MM-JJ");
            return nbrCon;
        }
        verifArgu = dateFin.split("-");
        if (verifArgu.length != 3) {
            System.err.println("In DB - getNumberOfConnectionBetween : dateFin n'est pas au format AAAA-MM-JJ");
            return nbrCon;
        }
        verifArgu = heureDeb.split(":");
        if (verifArgu.length != 3) {
            System.err.println("In DB - getNumberOfConnectionBetween : heureDeb n'est pas au format HH:MM:SS");
            return nbrCon;
        }
        verifArgu = heureFin.split(":");
        if (verifArgu.length != 3) {
            System.err.println("In DB - getNumberOfConnectionBetween : heureFin n'est pas au format HH:MM:SS");
            return nbrCon;
        }
        
        //Création de la requête sql
        String sql = "SELECT COUNT(`id`) AS " + colonne + " FROM " + TABLE_VISITES + " WHERE ";
        sql += "(`date` >= \"" + dateDeb + " " + heureDeb + "\"";
        sql += " && ";
        sql += "`date` <= \"" + dateFin + " " + heureFin + "\")";
        
        //Exécution de la requête sql
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                nbrCon = rs.getInt(colonne);
            }
        } catch (SQLException ex) {
            System.err.println("In DB - getNumberOfConnectionBetween : pas pu lire les résultats de la requete " + sql + "\nerreur : " + ex.getLocalizedMessage());
        }
        
        return nbrCon;
    }
    
    @Override
    public int getNumberOfUserForCoupleCommuneAndWorkplace(String commune, String codePostal, String lieuTravail) {
        //Declaration des variables que l'on va utiliser
        int res = -1;
        
        //Declaration des parametres de la requete
        String colonne = "COUNT(l.nom_lieu) AS nbr_utilisateurs";
        String joinCondition = "u.lieu_travail_id = l.id";
        String groupByCouple = "l.nom_lieu,u.commune,u.code_postal";
        String conditionWhere = "l.nom_lieu = \"" + lieuTravail + "\" && u.commune = \"" + commune + "\" && u.code_postal = \"" + codePostal + "\"";
        
        //Creation de la requete sql
        String sql = "SELECT " + colonne + " "
                + " FROM " + TABLE_UTILISATEURS + " AS u"
                + " INNER JOIN " + TABLE_LIEUX_TRAVAIL + " AS l"
                + " ON (" + joinCondition + ")"
                + " WHERE (" + conditionWhere + ")"
                + " GROUP BY " + groupByCouple;
        
        //Execution de la requete sql
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                res = rs.getInt("nbr_utilisateurs");
            }
        } catch (SQLException ex) {
            System.err.println("In DB - getNumberOfUserForCoupleCommuneAndWorkplace : pas pu lire les résultats de la requete " + sql + "\nerreur : " + ex.getLocalizedMessage());
        }
        
        return res;
    }
    
    @Override
    public ArrayList<CoupleCommuneLieuTravail> getAllNumberOfUserByCoupleCommuneAndWorkplace() {
        //Declaration des variables que l'on va utiliser
        ArrayList<CoupleCommuneLieuTravail> res = new ArrayList<CoupleCommuneLieuTravail>();
        CoupleCommuneLieuTravail coupleActuel;
        int nbrUtilisateurActu;
        String communeActu;
        String codePostalActu;
        String nomLieuTravailActu;
        
        //Declaration des parametres de la requete
        String[] colonnes = {"COUNT(l.nom_lieu) AS nbr_utilisateurs", "u.commune", "u.code_postal", "l.nom_lieu"};
        String joinCondition = "u.lieu_travail_id = l.id";
        String groupByCouple = "l.nom_lieu,u.commune,u.code_postal";
        
        //Creation de la requete sql
        String sql = "SELECT " + colonnes[0] + ", " + colonnes[1] + ", " + colonnes[2] + ", " + colonnes[3] + " "
                + " FROM " + TABLE_UTILISATEURS + " AS u"
                + " INNER JOIN " + TABLE_LIEUX_TRAVAIL + " AS l"
                + " ON (" + joinCondition + ")"
                + " GROUP BY " + groupByCouple;
        
        //Execution de la requete sql
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                nbrUtilisateurActu = rs.getInt("nbr_utilisateurs");
                communeActu = rs.getString("commune");
                codePostalActu = rs.getString("code_postal");
                nomLieuTravailActu = rs.getString("nom_lieu");
                coupleActuel = new CoupleCommuneLieuTravail(nbrUtilisateurActu, communeActu, codePostalActu, nomLieuTravailActu);
                res.add(coupleActuel);
            }
        } catch (SQLException ex) {
            System.err.println("In DB - getNumberOfUserByCoupleCommuneAndWorkplace : pas pu lire les résultats de la requete " + sql + "\nerreur : " + ex.getLocalizedMessage());
        }
        
        return res;
    }
    
    @Override
    public double getPercentOfDrivers() {
        double percent = 0.0;
        double nombre_utilisateurs = 0.0;
        
        String sql = "SELECT COUNT(email) AS nombre_utilisateurs,"
                + " SUM(CASE WHEN conducteur=1 THEN 1 ELSE 0 END) AS nombre_conducteurs"
                + " FROM " + TABLE_UTILISATEURS;
        
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                nombre_utilisateurs = (double) rs.getInt("nombre_utilisateurs");
                if (nombre_utilisateurs != 0.0d) {
                    percent = (double) rs.getInt("nombre_conducteurs") / nombre_utilisateurs * 100.0d;
                } else {
                    return 100.0d;
                }
            }
        } catch (SQLException ex) {
            System.err.println("In DB - getPercentOfDrivers : pas pu lire les résultats de la requete " + sql + "\nerreur : " + ex.getLocalizedMessage());
            percent = -1.0d;
        }
        
        return percent;
    }
    
    @Override
    public int getNumberOfDrivers() {
        int conducteurs = 0;
        
        String sql = "SELECT COUNT(email) AS nombre_conducteurs FROM " + TABLE_UTILISATEURS + " WHERE conducteur = 1";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                conducteurs = rs.getInt("nombre_conducteurs");
            }
        } catch (SQLException ex) {
            System.err.println("In DB - getNumbersOfDrivers : pas pu lire les résultats de la requete " + sql + "\nerreur : " + ex.getLocalizedMessage());
            conducteurs = -1;
        }
        
        return conducteurs;
    }
    
    @Override
    public int getNumberOfNonDrivers() {
        int nonConducteurs = 0;
        
        String sql = "SELECT COUNT(email) AS nombre_non_conducteurs FROM " + TABLE_UTILISATEURS + " WHERE conducteur = 0";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                nonConducteurs = rs.getInt("nombre_non_conducteurs");
            }
        } catch (SQLException ex) {
            System.err.println("In DB - getNumbersOfNonDrivers : pas pu lire les résultats de la requete " + sql + "\nerreur : " + ex.getLocalizedMessage());
            nonConducteurs = -1;
        }
        
        return nonConducteurs;
    }
    
    @Override
    public int getNumberOfUsers() {
        int utilisateurs = 0;
        
        String sql = "SELECT COUNT(email) AS nombre_utilisateurs FROM " + TABLE_UTILISATEURS;
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                utilisateurs = rs.getInt("nombre_utilisateurs");
            }
        } catch (SQLException ex) {
            System.err.println("In DB - getNumbersOfUsers : pas pu lire les résultats de la requete " + sql + "\nerreur : " + ex.getLocalizedMessage());
            utilisateurs = -1;
        }
        
        return utilisateurs;
    }
    
//test addUser, ShowDatabase,deleteUSer...
    
  
        
   
    @Override
    public ArrayList<Commune> getAllCommunes() {
        String nomLieu = "commune";
        
        ArrayList<Commune> res = new ArrayList<Commune>() {
        };
        //SELECT distinct name FROM info WHERE status = 1 ORDER BY id
        String sql = "SELECT distinct code_postal," + nomLieu + " FROM " +TABLE_UTILISATEURS;
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                res.add(new Commune(rs.getString(nomLieu),rs.getString("code_postal")));
            }
        } catch (SQLException ex) {
            System.err.println("In DB - getAllWorkplaces : pas pu lire les résultats de la requete " + sql + "\nerreur : " + ex.getLocalizedMessage());
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        //testGetNumberOfConnectionBetween();
        //testGetAllNumberOfUserByCoupleCommuneAndWorkplace();
        //testGetNumberOfUserForCoupleCommuneAndWorkplace();
        testGetPercentOfDrivers();
        testGetNumberOfDrivers();
        testGetNumberOfNonDrivers();
        testGetNumberOfUsers();
        DB dbHelper = new DB();
        // dbHelper.
        // String password = dbHelper.getPassword("simpleuser@test.com");
        // System.out.println("Password is " + password);
        //dbHelper.setPassword("adminuser@test.com","adminuser");
        //dbHelper.editLocation("ghader@etud.insa-toulouse.fr", "Balma");
        //System.out.println(dbHelper.userExists("adminuser@test.com", "adminuser"));
        //dbHelper.addNewUser(false, "omar", "ghader", "pass", "07", "oma@insa.fr", "135 avenue de Rangueil", "Labege", "31400", "Sopra_Group_Ent1", "08:00:00", "17:00:00", "L,M,M,J,V", true, true);
        //dbHelper.addNewUser(true, DB_URL, USER, DB_URL, USER, USER, DB_URL, DB_URL, PASS, TABLE_VISITES, JDBC_DRIVER, DB_URL, JDBC_DRIVER, true, true)
        //System.out.println(dbHelper.searchRoute("Toulouse", "Sopra_Group_Ent2").toString());
        //dbHelper.editWorkLocation(1, "Sopra_Group_Ent1", "37 Chemin des Ramassier", "Colomiers", "31770");
        dbHelper.deleteWorkLocation(1, "Sopra_Group_Ent1");
        System.out.println(dbHelper.getAllCommunes().toString());
        dbHelper.listData();
        dbHelper.closeConnection();
    }//end main
    
    public static void testGetNumberOfConnectionBetween() {
        System.out.println("##########################################");
        System.out.println("###test de getNumberOfConnectionBetween###");
        DB dbHelper = new DB();
        
        String dateDeb = "2014-12-07";
        String heureDeb = "00:00:00";
        String dateFin = "2014-12-31";
        String heureFin = "23:59:59";
        int nbrCon = dbHelper.getNumberOfConnectionBetween(dateDeb, heureDeb, dateFin, heureFin);
        System.out.printf("Nbr Connection : entre %s %s <-> %s %s : \n", dateDeb, heureDeb, dateFin, heureFin);
        System.out.println("Attendu : " + 2);
        System.out.println("Obtenu  : " + nbrCon);
        
        dateDeb = "2014-07";
        heureDeb = "00:00:00";
        dateFin = "2014-12-31";
        heureFin = "23:59:59";
        nbrCon = dbHelper.getNumberOfConnectionBetween(dateDeb, heureDeb, dateFin, heureFin);
        System.out.printf("Nbr Connection : entre %s %s <-> %s %s : \n", dateDeb, heureDeb, dateFin, heureFin);
        System.out.println("Attendu : " + -1);
        System.out.println("Obtenu  : " + nbrCon);
        
        dateDeb = "2014-12-07";
        heureDeb = "00:00";
        dateFin = "2014-12-31";
        heureFin = "23:59:59";
        nbrCon = dbHelper.getNumberOfConnectionBetween(dateDeb, heureDeb, dateFin, heureFin);
        System.out.printf("Nbr Connection : entre %s %s <-> %s %s : \n", dateDeb, heureDeb, dateFin, heureFin);
        System.out.println("Attendu : " + -1);
        System.out.println("Obtenu  : " + nbrCon);
        
        dateDeb = "2014-12-07";
        heureDeb = "00:00:00";
        dateFin = "2014-31";
        heureFin = "23:59:59";
        nbrCon = dbHelper.getNumberOfConnectionBetween(dateDeb, heureDeb, dateFin, heureFin);
        System.out.printf("Nbr Connection : entre %s %s <-> %s %s : \n", dateDeb, heureDeb, dateFin, heureFin);
        System.out.println("Attendu : " + -1);
        System.out.println("Obtenu  : " + nbrCon);
        
        dateDeb = "2014-12-07";
        heureDeb = "00:00:00";
        dateFin = "2014-12-31";
        heureFin = "23:59:";
        nbrCon = dbHelper.getNumberOfConnectionBetween(dateDeb, heureDeb, dateFin, heureFin);
        System.out.printf("Nbr Connection : entre %s %s <-> %s %s : \n", dateDeb, heureDeb, dateFin, heureFin);
        System.out.println("Attendu : " + -1);
        System.out.println("Obtenu  : " + nbrCon);
        
        System.out.println("##########################################");
    }
    
    public static void testGetAllNumberOfUserByCoupleCommuneAndWorkplace() {
        System.out.println("##########################################");
        System.out.println("###test de getAllNumberOfUserByCoupleCommuneAndWorkplace###");
        DB dbHelper = new DB();
        ArrayList<CoupleCommuneLieuTravail> res = dbHelper.getAllNumberOfUserByCoupleCommuneAndWorkplace();
        
        System.out.printf("Attendu : \n%s\t|%s\t|%s|\t%s\n", 1, "Sopra_Group_Ent1", "Toulouse", "31100");
        System.out.printf("%s\t|%s\t|%s|\t%s\n", 2, "Sopra_Group_Ent2", "Toulouse", "31100");
        
        System.out.printf("Obtenu  :\n");
        for (CoupleCommuneLieuTravail c : res) {
            System.out.printf("%s\t|%s\t|%s|\t%s\n", c.getNbrUtilisateurs(), c.getNomLieuTravail(), c.getCommune(), c.getCodePostal());
        }
        System.out.println("##########################################");
    }
    
    public static void testGetNumberOfUserForCoupleCommuneAndWorkplace() {
        System.out.println("##########################################");
        System.out.println("###test de getNumberOfUserForCoupleCommuneAndWorkplace###");
        DB dbHelper = new DB();
        String commune = "Toulouse";
        String codePostal = "31100";
        String lieuTravail = "Sopra_Group_Ent1";
        String lieuTravail2 = "Sopra_Group_Ent2";
        String lieuTravail3 = "Inexistant";
        
        int res = dbHelper.getNumberOfUserForCoupleCommuneAndWorkplace(commune, codePostal, lieuTravail);
        System.out.printf("Attendu : for %s(%s),%s :\t%s\n", commune, codePostal, lieuTravail, 1);
        System.out.printf("Obtenu  : for %s(%s),%s :\t%s\n", commune, codePostal, lieuTravail, res);
        res = dbHelper.getNumberOfUserForCoupleCommuneAndWorkplace(commune, codePostal, lieuTravail2);
        System.out.printf("Attendu : for %s(%s),%s :\t%s\n", commune, codePostal, lieuTravail2, 2);
        System.out.printf("Obtenu  : for %s(%s),%s :\t%s\n", commune, codePostal, lieuTravail2, res);
        res = dbHelper.getNumberOfUserForCoupleCommuneAndWorkplace(commune, codePostal, lieuTravail3);
        System.out.printf("Attendu : for %s(%s),%s :\t%s\n", commune, codePostal, lieuTravail3, -1);
        System.out.printf("Obtenu  : for %s(%s),%s :\t%s\n", commune, codePostal, lieuTravail3, res);
        System.out.println("##########################################");
    }
    
    public static void testGetPercentOfDrivers() {
        System.out.println("##########################################");
        System.out.println("###test de getPercentOfDrivers###");
        System.out.println("##########################################");
        
        DB dbHelper = new DB();
        System.out.println("Cherche le poucentage de conducteurs.");
        System.out.println("Attendu : 66.66667%");
        System.out.println("Obtenu  : " + dbHelper.getPercentOfDrivers());
    }
    
    public static void testGetNumberOfDrivers() {
        System.out.println("##########################################");
        System.out.println("###test de getNumberOfDrivers###");
        System.out.println("##########################################");
        
        DB dbHelper = new DB();
        System.out.println("Cherche le nombre de conducteurs.");
        System.out.println("Attendu : 2");
        System.out.println("Obtenu  : " + dbHelper.getNumberOfDrivers());
        
    }
    
    public static void testGetNumberOfNonDrivers() {
        System.out.println("##########################################");
        System.out.println("###test de getNumberOfNonDrivers###");
        System.out.println("##########################################");
        
        DB dbHelper = new DB();
        System.out.println("Cherche le nombre de non conducteurs.");
        System.out.println("Attendu : 1");
        System.out.println("Obtenu  : " + dbHelper.getNumberOfNonDrivers());
        
    }
    
    public static void testGetNumberOfUsers() {
        System.out.println("##########################################");
        System.out.println("###test de getNumberOfUser###");
        System.out.println("##########################################");
        
        DB dbHelper = new DB();
        System.out.println("Cherche le nombre d'utilisateurs.");
        System.out.println("Attendu : 3");
        System.out.println("Obtenu  : " + dbHelper.getNumberOfUsers());
        
    }
}
