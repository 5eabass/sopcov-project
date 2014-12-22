/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.models;

/**
 *
 * @author gb
 */
public abstract class DBUserInfo {

    private String name;
    private String pswd;
    private String cat;

    public DBUserInfo() {
        name="";
        pswd="";
        cat="";
    }

    public DBUserInfo(String name, String pswd, String cat) {
        this.name = name;
        this.pswd = pswd;
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public String getPswd() {
        return pswd;
    }

    public String getCat() {
        return cat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

}
