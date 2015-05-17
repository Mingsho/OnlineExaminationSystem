/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.beans;


import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.NoResultException;
import javax.ejb.Stateless;
import java.util.List;
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
    private boolean checkUnamePwd(String uName, String pWd)
    {
        boolean bRetVal=false;
       
        Query query=eManager.createNamedQuery("User.GetCountUnamePwd", User.class);
        query.setParameter("uName", uName);
        query.setParameter("pWd", pWd);
        
        long count=(long)query.getSingleResult();
        
        if(count>0)
            bRetVal=true;
        
        return bRetVal;
    }
    public User getByUnamePwd(String uName, String pWd) throws NoResultException
    {
        User user=null;
        if (checkUnamePwd(uName,pWd)) {
            
            Query query=eManager.createNamedQuery("User.GetByUnamePwd", User.class);
            query.setParameter("uName", uName);
            query.setParameter("pWd", pWd); 
            
            user= (User)query.getSingleResult();
            
        }
        return user;
    }
    public String getUserType(long uId)
    {
        Query query=eManager.createNamedQuery("SELECT dtype FROM UserProfile"
                + " WHERE userid= :Id");
        query.setParameter("Id", uId);
        
        String strUserType=query.getSingleResult().toString();
        
        return strUserType;
        
    }
    
}
