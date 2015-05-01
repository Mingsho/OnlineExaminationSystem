package org.oes.model;

import java.sql.Blob;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-30T12:14:12")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Long> User_ID;
    public static volatile SingularAttribute<User, String> ContactNumber;
    public static volatile SingularAttribute<User, String> MiddleName;
    public static volatile SingularAttribute<User, String> Address;
    public static volatile SingularAttribute<User, String> EmailAddress;
    public static volatile SingularAttribute<User, String> FirstName;
    public static volatile SingularAttribute<User, String> LastName;
    public static volatile SingularAttribute<User, Blob> ProfilePicture;

}