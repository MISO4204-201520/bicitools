package com.bicitools.entity;

import com.bicitools.entity.Usuarioschat;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-01T10:32:10")
@StaticMetamodel(Chat.class)
public class Chat_ { 

    public static volatile SingularAttribute<Chat, String> nombre;
    public static volatile SingularAttribute<Chat, Integer> idchat;
    public static volatile ListAttribute<Chat, Usuarioschat> usuarioschatList;

}