/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.beans;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.ejb.Stateless;
import org.oes.model.User;
import org.oes.utilities.Constants;

/**
 *
 * @author Mingso
 */
@Stateless
public class UserEJB {
    
    @PersistenceContext(name = Constants.PersistenceName)
    EntityManager eManager;
    
    public User createUserProfile(User user)
    {
        eManager.persist(user);
        eManager.flush();
        return user;
    }
    
}
