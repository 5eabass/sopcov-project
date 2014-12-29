/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.models;

import java.sql.Connection;

/**
 * This class allows polymorphic database connections
 * @author gb
 */
public interface DBI {
    Connection getConnection();
    String getConnectionURL();
    String getConnectionDetails();
    String getTablesSchemaQuery();
}
