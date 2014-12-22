/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gb
 */
public class DBManager {
    Connection cn = null;
    DBI dbi = null;

    public DBManager() {
    }

    public DBManager(DBI dbi) {
        this.dbi = dbi;
    }
    
    public boolean setDBI( DBI dbi ) {
        if (dbi == null) {
            throw new IllegalArgumentException("Please use a valid database interface");
        }
        this.dbi = dbi;
        return true;
    }
    
    public boolean openConnection()
    {
        try {
           if (dbi==null) {
               throw new IllegalArgumentException("Define a database interface");
           }
           if (dbi != null) {
               closeConnection(false);
           }
           cn = dbi.getConnection();
        }
        catch (Exception ex) {
            System.err.println("Error in DBManager : " + ex.getLocalizedMessage());
            return false;
        }
        if (cn == null) return false;
        return true;
    }
    
    public boolean closeConnection(boolean keepAlive) {
        try {
            if (cn != null) {
                if (!cn.isClosed()) {
                    cn.close();
                }
            }
            if (!keepAlive) {
                cn = null;
            }
        } catch (SQLException ex) {
            System.err.println("Error in DBManager : " + ex.getLocalizedMessage());
            return false;
        }
        return true;
    }
    
    public boolean isConnected() {
        return cn != null;
    }
    
    public boolean ExecuteNonQuery(String query)
	{
		try
		{
			Statement st = cn.createStatement();
			int i = st.executeUpdate(query);
			if (i == -1)
			{
				//log it
				return false;
			}
			st.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ResultSet ExecuteResultSet(String query) throws SQLException
	{
		PreparedStatement st = cn.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		return rs;
	}
	
	public Connection getConnection()
	{
		return cn;
	}

	public String getConnectionURL()
	{
		return dbi.getConnectionURL();
	}
	
	public String getTablesSchemaQuery()
	{
		return dbi.getTablesSchemaQuery();
	}
}
