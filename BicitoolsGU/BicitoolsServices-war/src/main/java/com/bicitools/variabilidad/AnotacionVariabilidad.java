/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.variabilidad;

/**
 *
 * @author Taidy
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //can use in method only.
public @interface AnotacionVariabilidad {
//por defecto es false, es decir que es obligatorio
public enum FeatureName {GestionUsuario,Seguridad,ManejoPerfiles,RedesSociales,Twitter,Facebook,Comunicacion,CompartirRedesSociales}
    FeatureName featureName() default FeatureName.GestionUsuario;
        boolean variable() default false;
}