/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

/**
 *
 * @author jhony
 */
public abstract class RutasAbstractDAO implements RutasDAOLocal{
    private RutasDAOLocal rutasDAO;
    
    public RutasAbstractDAO (RutasDAOLocal rutas){
        this.rutasDAO = rutas;
    }
    
    protected RutasDAOLocal getRutasDAO(){
        return rutasDAO;
    }
    
}
