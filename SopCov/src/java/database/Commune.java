/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author root
 */
public class Commune {
    String commune;
    String code_postal;

    public Commune(String commune, String code_postal) {
        this.commune = commune;
        this.code_postal = code_postal;
    }
    
    public String toString(){
        return "\n"+commune+ ", "+code_postal;
    }
    
    public String toOptionString(){
        return commune+ "/"+code_postal;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }
    
    
}
