package com.bicitools.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-22T17:25:46")
@StaticMetamodel(Mensajeschat.class)
public class Mensajeschat_ { 

    public static volatile SingularAttribute<Mensajeschat, Integer> idmensajeschat;
    public static volatile SingularAttribute<Mensajeschat, Date> fecha;
    public static volatile SingularAttribute<Mensajeschat, String> usuario;
    public static volatile SingularAttribute<Mensajeschat, Integer> idchat;
    public static volatile SingularAttribute<Mensajeschat, Integer> recibido;
    public static volatile SingularAttribute<Mensajeschat, String> mensaje;

}