package com.bicitools.entity;

import com.bicitools.entity.Domiciliario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T19:44:34")
@StaticMetamodel(Horariodomiciliario.class)
public class Horariodomiciliario_ { 

    public static volatile SingularAttribute<Horariodomiciliario, Integer> idHorarioDomiciliario;
    public static volatile SingularAttribute<Horariodomiciliario, String> inicio;
    public static volatile SingularAttribute<Horariodomiciliario, Integer> dia;
    public static volatile SingularAttribute<Horariodomiciliario, String> fin;
    public static volatile SingularAttribute<Horariodomiciliario, Domiciliario> idDomiciliario;

}