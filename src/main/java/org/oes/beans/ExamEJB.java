/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.beans;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import org.oes.utilities.Constants;
import org.oes.model.Exam;

/**
 *
 * @author Mingso
 */
@Stateless
public class ExamEJB {
    
    @PersistenceContext(name=Constants.PersistenceName)
    EntityManager eManager;
    
    public Exam scheduleExam(Exam exam) throws EntityExistsException
    {
        eManager.persist(exam);
        eManager.flush();
        return exam;
        
    }
}
