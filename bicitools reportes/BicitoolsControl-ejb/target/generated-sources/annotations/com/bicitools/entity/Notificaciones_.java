package com.bicitools.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-22T10:27:01")
@StaticMetamodel(Notificaciones.class)
public class Notificaciones_ { 

    public static volatile SingularAttribute<Notificaciones, Integer> idNotificacion;
    public static volatile SingularAttribute<Notificaciones, Date> fechaHora;
    public static volatile SingularAttribute<Notificaciones, String> usuario;
    public static volatile SingularAttribute<Notificaciones, Integer> tipoNotificacion;

}