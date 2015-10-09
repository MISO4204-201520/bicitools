/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson;



/**
 *
 * @author Ludwing
 */
public class TemporalJson {
    private int pkColumn;
    private String estring;
    
    public TemporalJson(){
        
    }

    /**
     * @return the pkColumn
     */
    public int getPkColumn() {
        return pkColumn;
    }

    /**
     * @param pkColumn the pkColumn to set
     */
    public void setPkColumn(int pkColumn) {
        this.pkColumn = pkColumn;
    }

    /**
     * @return the estring
     */
    public String getEstring() {
        return estring;
    }

    /**
     * @param estring the estring to set
     */
    public void setEstring(String estring) {
        this.estring = estring;
    }
    
     @Override
    public String toString() {
        return new StringBuffer(" pkColumn : ").append(this.pkColumn)
                .append(" esttring : ").append(this.estring).toString();
    }
    
            
    
}
