package com.bicitools.entity;

import com.bicitools.entity.Horariodomiciliario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T19:44:34")
@StaticMetamodel(Domiciliario.class)
public class Domiciliario_ { 

    public static volatile SingularAttribute<Domiciliario, String> nombre;
    public static volatile SingularAttribute<Domiciliario, String> apellido;
    public static volatile SingularAttribute<Domiciliario, String> direccion;
    public static volatile CollectionAttribute<Domiciliario, Horariodomiciliario> horariodomiciliarioCollection;
    public static volatile SingularAttribute<Domiciliario, Integer> estado;
    public static volatile SingularAttribute<Domiciliario, String> email;
    public static volatile SingularAttribute<Domiciliario, String> idDomiciliario;
    public static volatile SingularAttribute<Domiciliario, String> celular;

}