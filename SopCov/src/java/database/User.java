/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools ; Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author root
 */
public class User {

    private String email;
    private String password;
    private int admin;
    private String prenom;
    private String nom;
    private String tel;
    private String adresse;
    private String commune;
    private int code_postal;
    private int lieu_travail_id;
    private String lieu_travail;
    private String heure_depart;
    private String heure_retour;
    private String jours_travail;
    private int conducteur;
    private int notif;

    public User(String email, String password, int admin, String prenom, String nom, String tel, String adresse, String commune, int code_postal, String lieu_travail ,int lieu_travail_id, String heure_depart, String heure_retour, String jours_travail, int conducteur, int notif) {
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.prenom = prenom;
        this.nom = nom;
        this.tel = tel;
        this.adresse = adresse;
        this.commune = commune;
        this.code_postal = code_postal;
        this.lieu_travail_id = lieu_travail_id;
        this.heure_depart = heure_depart;
        this.heure_retour = heure_retour;
        this.jours_travail = jours_travail;
        this.conducteur = conducteur;
        this.notif = notif;
        this.lieu_travail=lieu_travail;
    }  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public int getLieu_travail_id() {
        return lieu_travail_id;
    }

    public void setLieu_travail_id(int lieu_travail_id) {
        this.lieu_travail_id = lieu_travail_id;
    }

    public String getLieu_travail() {
        return lieu_travail;
    }

    public void setLieu_travail(String lieu_travail) {
        this.lieu_travail = lieu_travail;
    }

    public String getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(String heure_depart) {
        this.heure_depart = heure_depart;
    }

    public String getHeure_retour() {
        return heure_retour;
    }

    public void setHeure_retour(String heure_retour) {
        this.heure_retour = heure_retour;
    }

    public String getJours_travail() {
        return jours_travail;
    }

    public void setJours_travail(String jours_travail) {
        this.jours_travail = jours_travail;
    }

    public int getConducteur() {
        return conducteur;
    }

    public void setConducteur(int conducteur) {
        this.conducteur = conducteur;
    }

    public int getNotif() {
        return notif;
    }

    public void setNotif(int notif) {
        this.notif = notif;
    }
    
    
}
