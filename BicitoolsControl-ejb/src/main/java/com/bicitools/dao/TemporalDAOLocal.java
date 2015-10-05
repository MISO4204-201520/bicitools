/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import javax.ejb.Local;

/**
 *
 * @author Ludwing
 */
@Local
public interface TemporalDAOLocal {
    public int creaTemp(String dato);
    
    
}
