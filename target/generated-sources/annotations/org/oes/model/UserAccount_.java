package org.oes.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.oes.model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-18T14:39:56")
@StaticMetamodel(UserAccount.class)
public class UserAccount_ { 

    public static volatile SingularAttribute<UserAccount, Long> userID;
    public static volatile SingularAttribute<UserAccount, String> username;
    public static volatile SingularAttribute<UserAccount, String> password;
    public static volatile SingularAttribute<UserAccount, User> user;

}