/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gb
 */
public class MySQLDBI extends DBUserInfo implements DBI {

    public MySQLDBI() {
        super();
    }

    public MySQLDBI(String name, String pswd, String catalog) {
        super(name, pswd, catalog);
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection cn = DriverManager.getConnection(getConnectionURL());
            return cn;
        } catch (Exception ex) {
            System.err.println("Erreur in MySQLDBI : " + ex.getMessage());
        }
        return null;
    }

    @Override
    public String getConnectionURL() {
        return String.format("jdbc:mysql://localhost:3306/%s?user=%s&password=%s"
                , this.getCat(), this.getName(), this.getPswd());
    }

    @Override
    public String getConnectionDetails() {
        return "MySQL Database Connection to " + getCat();
    }

    @Override
    public String getTablesSchemaQuery() {
        return "select table_name from information_schema.tables where table_schema =  " + getCat();
    }

}
