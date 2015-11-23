package com.bicitools.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-22T20:25:05")
@StaticMetamodel(LogsUsuario.class)
public class LogsUsuario_ { 

    public static volatile SingularAttribute<LogsUsuario, String> usuario;
    public static volatile SingularAttribute<LogsUsuario, String> latitud;
    public static volatile SingularAttribute<LogsUsuario, Date> fechaRegistro;
    public static volatile SingularAttribute<LogsUsuario, String> longitud;
    public static volatile SingularAttribute<LogsUsuario, Date> fechaInicioRecorrido;
    public static volatile SingularAttribute<LogsUsuario, String> rutaActual;

}