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
 private int id;
    private boolean admin;
    private String name;
    private String lastname;
    private String password ;
    private String  email ;
    private String tel ;
    private String address  ;
    private String city     ;
    private int postalcode ; 
    private String workplace ; 
    private int deptime ; 
    private int  rettime ;
    private String workdays    ;
    private boolean driver ;
    private boolean notif ;
    

    public User(String name, String lastname, String password,String tel, String email, String address, String city, int postalcode, String workplace, int deptime, int rettime, String workdays, boolean driver, boolean notif) {
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.tel=tel;
        this.address = address;
        this.city = city;
        this.postalcode = postalcode;
        this.workplace = workplace;
        this.deptime = deptime;
        this.rettime = rettime;
        this.workdays = workdays;
        this.driver = driver;
        this.notif = notif;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public int getDeptime() {
        return deptime;
    }

    public void setDeptime(int deptime) {
        this.deptime = deptime;
    }

    public int getRettime() {
        return rettime;
    }

    public void setRettime(int rettime) {
        this.rettime = rettime;
    }

    public String getWorkdays() {
        return workdays;
    }

    public void setWorkdays(String workdays) {
        this.workdays = workdays;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    public boolean isNotif() {
        return notif;
    }

    public void setNotif(boolean notif) {
        this.notif = notif;
    }
    
    
   
}
