/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public interface DBInterface {
 
 
/**
 *
 * @author root
 */
    public void connect();
    public void closeConnection() ;
    public void listData();
    public List<User> queryInfo(String email);
    public String printHTML() ;
    public int addNewUser(int admin, String prenom, String nom, String password, String tel, String email, String adresse, String commune, int code_postal, int lieu_travail_id, String heure_depart, String heure_retour, String jours_travail, int conducteur, int notif );
    public int addNewUser(boolean admin, String prenom, String nom, String password, String tel, String email, String adresse, String commune, String codePostal, String nomLieuTravail, String heureDepart, String heureRetour, String joursTravail, boolean conducteur, boolean notif );
    public void deleteUser(String email, String prenom, String nom) ;
    public String getPassword(String email);
    public boolean editLocation(String email, int lieu_travail_id);
    public boolean deleteLocation(String email, String newlieu_travail_id) ;
    public void setPassword(String email, String password);
    public boolean emailAlreadyUsed(String email);
    public boolean userExists(String email, String password);
    public List<User> getAllDrivers();
    public List<User> searchRoute(String mCity,String mWorkplace);
    public ArrayList<String> getAllWorkplaces();
}
