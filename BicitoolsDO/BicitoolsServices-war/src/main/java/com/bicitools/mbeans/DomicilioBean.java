/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mbeans;

import javax.faces.bean.ManagedBean;
/**
 *
 * @author Ludwing
 */

@ManagedBean
public class DomicilioBean {
    private String world = "Hellosssss World!";

    /**
     * @return the world
     */
    public String getWorld() {
        return world;
    }

    /**
     * @param world the world to set
     */
    public void setWorld(String world) {
        this.world = world;
    }
    
}
