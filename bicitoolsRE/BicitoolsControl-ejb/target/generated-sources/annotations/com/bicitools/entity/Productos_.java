package com.bicitools.entity;

import com.bicitools.entity.Partes;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-22T19:02:36")
@StaticMetamodel(Productos.class)
public class Productos_ { 

    public static volatile SingularAttribute<Productos, Integer> idProducto;
    public static volatile SingularAttribute<Productos, String> nombre;
    public static volatile CollectionAttribute<Productos, Partes> partesCollection;

}