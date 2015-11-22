package com.bicitools.entity;

import com.bicitools.entity.Partes;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-22T10:27:01")
@StaticMetamodel(Proveedores.class)
public class Proveedores_ { 

    public static volatile SingularAttribute<Proveedores, Integer> idProveedor;
    public static volatile SingularAttribute<Proveedores, String> correo;
    public static volatile SingularAttribute<Proveedores, String> direccion;
    public static volatile SingularAttribute<Proveedores, String> usuario;
    public static volatile SingularAttribute<Proveedores, String> telefono;
    public static volatile CollectionAttribute<Proveedores, Partes> partesCollection;

}