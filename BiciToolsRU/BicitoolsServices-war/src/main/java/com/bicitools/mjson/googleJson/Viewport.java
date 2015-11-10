/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson.googleJson;

/**
 *
 * @author Pedro
 */                 
public class Viewport
{
    private Southwest southwest;

    private Northeast northeast;

    public Southwest getSouthwest ()
    {
        return southwest;
    }

    public void setSouthwest (Southwest southwest)
    {
        this.southwest = southwest;
    }

    public Northeast getNortheast ()
    {
        return northeast;
    }

    public void setNortheast (Northeast northeast)
    {
        this.northeast = northeast;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [southwest = "+southwest+", northeast = "+northeast+"]";
    }
}
			