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
import javax.persistence.TypedQuery;
import javax.ejb.Stateless;
import java.util.List;
import java.util.ArrayList;
import org.oes.model.User;
import org.oes.model.Student;
import org.oes.utilities.Constants;

/**
 *
 * @author Mingso
 */
@Stateless
public class UserEJB {
    
    @PersistenceContext(name = Constants.PersistenceName)
    EntityManager eManager;
    
    /**
     * Create basic user profile
     * @param user
     * @return User
     */
    public User createUserProfile(User user)
    {
        
        eManager.persist(user);
        eManager.flush();
        return user;
    }
    /**
     * Return all users from the db
     * @return List User
     */
    public List<User> getAllUser()
            throws IllegalArgumentException
    {
        TypedQuery<User> lstUsers=eManager.createNamedQuery("User.GetAllUser", User.class);
        
        return lstUsers.getResultList();
        
    }
    
    /**
     * Simple check of username and password
     * @param uName
     * @param pWd
     * @return boolean
     */
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
    /**
     * Get user by username
     * @param uName
     * @param pWd
     * @return User
     */
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
    /**
     * Get all users who are students.
     * 
     */
    public List<User> getAllStudents()
    {
        List<User> lstUsers=getAllUser();
        List<User> lstStudents=new ArrayList<>();
        
        //iterate through each user in lstUsers
        for(User user: lstUsers)
        {
            //select users who are students
            if(user instanceof Student)
            {
                lstStudents.add(user);
            }
        }
        return lstStudents;
            
    }
    
   
    
}
