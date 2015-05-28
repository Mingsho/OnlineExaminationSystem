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
import org.oes.model.Teacher;
import org.oes.utilities.Constants;

/**
 * @author Mingso
 * Stateless session bean related to
 * teacher business logic.
 */
@Stateless
public class TeacherEJB {
    
    @PersistenceContext(name= Constants.PersistenceName)
    EntityManager eManager;
    
    public void createTeacher(Teacher teacher)
    {
        eManager.persist(teacher);
        eManager.flush();
    }
    
}
