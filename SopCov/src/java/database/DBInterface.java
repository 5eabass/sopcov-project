/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
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
    public int addNewUser(int admin, String name, String lastname, String password,String tel, String email, String address, String city, int postalcode, String workplace, int deptime, int rettime, String workdays, int driver, int notif);
    public void deleteUser(int id, String name, String lastname);
    public String getPassword(String email);
    public boolean editLocation(String email, String newWorkplace);
    public boolean deleteLocation(String email, String newWorkplace) ;
    public void setPassword(String email, String password);
    public boolean userExists(String email, String password);
    public List<User> getAllDrivers();
    public List<User> searchRoute(String mCity,String mWorkplace);
}
