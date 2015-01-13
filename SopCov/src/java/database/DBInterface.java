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

    public void closeConnection();

    public void listData();
    
    public User queryInfo(String email);

    public String printHTML();
    
    //public int addNewUser(int admin, String prenom, String nom, String password, String tel, String email, String adresse, String commune, int code_postal, int lieu_travail_id, String heure_depart, String heure_retour, String jours_travail, int conducteur, int notif);

    /**
     * Ajoute un utilisateur à la base de donnée. Attention aucune vérification
     * des paramètres n'est faite.
     *
     * @param admin Si l'utilisateur doit être administrateur ou non
     * @param prenom Le prenom de l'utilisateur
     * @param nom Le nom de l'utilisateur
     * @param password Le nom de l'utilisateur
     * @param tel Le numéro de téléphone de l'utilisateur
     * @param email L'email de l'utilisateur
     * @param adresse L'adresse de l'utilisateur
     * @param commune La commune de l'utilisateur ("Toulouse")
     * @param codePostal Le code postal de l'utilisateur ("31100")
     * @param nomLieuTravail Le nom du lieu de travail de l'utilisateur ("Sopra
     * Group 1")
     * @param heureDepart L'heure de départ de l'utilisateur ("08:00:00")
     * @param heureRetour L'heure de retour de l'utilisateur ("17:45:00")
     * @param joursTravail Les jours de travail de l'utilisateur
     * ("Lun,Mar,Mer,Jeu,Ven,Sam,Dim") ("Mar,Jeu")
     * @param conducteur Si l'utilisateur est un conducteur qui donc propose le
     * covoiturage ou non
     * @param notif Si l'utilisateur veut recevoir des mail ou non
     * @return 0 = tout va bien <br/>
     * -1 = n'a pas pu récupérer l'ID associé au lieu de travail<br/>
     * -2 = n'a pas pu accéder à l'id du lieu de travail<br/>
     * -3 = n'a pas pu créer l'utilisateur
     */
    public int addNewUser(boolean admin, String prenom, String nom, String password, String tel, String email, String adresse, String commune, String codePostal, String nomLieuTravail, String heureDepart, String heureRetour, String joursTravail, boolean conducteur, boolean notif);

    public void deleteUser(String email);

    public String getPassword(String email);
    
    public void setAdminRight(String email,String admin);

    public boolean editLocation(String email, int lieu_travail_id);

    public boolean deleteLocation(String email, String newlieu_travail_id);
    public boolean editWorkLocation(int lieu_travail_id, String newNom_lieu, String adresse,String commune, String code_postal);
    public boolean deleteWorkLocation(int id, String newlieu_travail_nom) ;

    public void setPassword(String email, String password);

    public void editUserProfile(String email, String nom, String prenom, String adresse,
            String tel, String commune, String code_postal, String lieu_travail, 
            String heure_depart, String heure_retour, String jours_travail, String conducteur, String notif);
    
    public boolean emailAlreadyUsed(String email);
    
    /**
     * Retient la date à laquelle l'utilisateur s'est connecté.
     * @param email l'email de l'utilisateur qui vient de se connecter
     */
    public void rememberUserLogIn(String email);

    public boolean userExists(String email, String password);

    public boolean isUserInDB(String email);
    
    public boolean isUSerAdmin(String email);
    public ArrayList<Commune> getAllCommunes();
    public List<User> getAllDrivers();

    public List<User> searchRoute(String mCity, String mWorkplace);

    public ArrayList<String> getAllWorkplaces();

    /*
     ADMIN REPORTS :
     */
    /**
     * Renvoie le nombre de connections ayant eu lieu entre les dates dateDeb
     * heureDeb et dateFin heureFin
     *
     * @param dateDeb "AAAA-MM-JJ"
     * @param heureDeb "HH:MM:SS"
     * @param dateFin "AAAA-MM-JJ"
     * @param heureFin "HH:MM:SS"
     * @return le nombre de connections ou -1 si quelque chose s'est mal passé.
     */
    public int getNumberOfConnectionBetween(String dateDeb, String heureDeb, String dateFin, String heureFin);

    /**
     * Donne la liste de couple Commune/Lieu de travail/nombre utilisateurs
     * interesses
     *
     * @return la liste de couple Commune/Lieu de travail avec le nombre
     * d'utilisateurs interesses par ce trajet (ce couple) ou null s'il n'y a
     * pas de couple Commune/Lieu de travail
     */
    public ArrayList<CoupleCommuneLieuTravail> getAllNumberOfUserByCoupleCommuneAndWorkplace();

    /**
     * Donne le nombre de personnes interessees par un trajet depuis une
     * commune(code postal) vers un lieu de travail
     *
     * @param commune la commune des utilisateurs vises
     * @param codePostal le code postal associe a celle-ci
     * @param lieuTravail le lieu de travail des utilisateurs vises
     * @return le nombre d'utilisateurs interesses par un trajet entre
     * commune(code postal) et un lieu de travail
     */
    public int getNumberOfUserForCoupleCommuneAndWorkplace(String commune, String codePostal, String lieuTravail);

    /**
     * Donne le pourcentage de conducteurs
     *
     * @return le pourcentage de conducteurs
     * -1.0 si il y a eu erreur dans la requête
     */
    public double getPercentOfDrivers();

    /**
     * Donne le nombre de conducteurs
     *
     * @return le nombre de conducteurs<br/>
     * -1 si il y a eu erreur dans la requête
     */
    public int getNumberOfDrivers();

    /**
     * Donne le nombre de non conducteurs
     *
     * @return le nombre de non conducteurs<br/>
     * -1 si il y a eu erreur dans la requête
     * 100.0 si il n'y a pas de conducteurs
     */
    public int getNumberOfNonDrivers();
    
    /**
     * Donne le nombre de gens
     *
     * @return le nombre de gens<br/>
     * -1 si il y a eu erreur dans la requête
     */
    public int getNumberOfUsers();
}
