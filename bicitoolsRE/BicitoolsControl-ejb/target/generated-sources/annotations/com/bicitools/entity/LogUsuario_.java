package com.bicitools.entity;

import com.bicitools.entity.LogUsuarioPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-22T18:34:24")
@StaticMetamodel(LogUsuario.class)
public class LogUsuario_ { 

    public static volatile SingularAttribute<LogUsuario, Date> fechaInicioRecorrido;
    public static volatile SingularAttribute<LogUsuario, String> rutaActual;
    public static volatile SingularAttribute<LogUsuario, LogUsuarioPK> logUsuarioPK;

}