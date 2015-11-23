package com.bicitools.entity;

import com.bicitools.entity.Ordenes;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-22T19:02:36")
@StaticMetamodel(EstadoOrden.class)
public class EstadoOrden_ { 

    public static volatile SingularAttribute<EstadoOrden, String> idEstado;
    public static volatile SingularAttribute<EstadoOrden, String> nombre;
    public static volatile CollectionAttribute<EstadoOrden, Ordenes> ordenesCollection;

}