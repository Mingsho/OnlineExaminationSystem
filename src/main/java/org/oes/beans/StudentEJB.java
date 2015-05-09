/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.beans;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ejb.Stateless;
import org.oes.model.Student;
import org.oes.model.UserAccount;
import org.oes.model.User;
import org.oes.utilities.Constants;

/**
 *
 * @author Mingso
 */

@Stateless
public class StudentEJB {
    
    @PersistenceContext(unitName = Constants.PersistenceName )
    EntityManager eManager;
    
    
    public Student createStudent(Student std)
    {
        eManager.persist(std);
        eManager.flush();
        return std;
    }
    
    public void testCreate(User user)
    {
        eManager.persist(user);
        
    }
    
   
    public UserAccount createStudentProfile(UserAccount uAccount)
    {
        eManager.persist(uAccount);
        return uAccount;
    }
    
    
}
