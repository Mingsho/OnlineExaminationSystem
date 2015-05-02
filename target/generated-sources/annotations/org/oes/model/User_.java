package org.oes.model;

import java.sql.Blob;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.oes.model.UserAccount;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-03T00:35:14")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> middleName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> contactNumber;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Long> userId;
    public static volatile SingularAttribute<User, Blob> profilePicture;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, UserAccount> userAccount;

}