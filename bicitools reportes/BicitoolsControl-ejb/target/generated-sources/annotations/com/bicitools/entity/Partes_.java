package com.bicitools.entity;

import com.bicitools.entity.Productos;
import com.bicitools.entity.Proveedores;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-30T00:09:57")
@StaticMetamodel(Partes.class)
public class Partes_ { 

    public static volatile SingularAttribute<Partes, String> descripcion;
    public static volatile SingularAttribute<Partes, Proveedores> idProveedor;
    public static volatile SingularAttribute<Partes, String> valor;
    public static volatile SingularAttribute<Partes, Integer> idTipo;
    public static volatile SingularAttribute<Partes, Productos> idProducto;
    public static volatile SingularAttribute<Partes, Integer> idParte;

}