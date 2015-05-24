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
import java.util.List;
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
    
    
    /**
     * Schedule a new exam
     * @param exam
     * @return Exam 
     */
    public Exam scheduleExam(Exam exam)
            throws IllegalArgumentException
    {
        eManager.persist(exam);
        eManager.flush();
        return exam;
        
    }
    
    /**
     * Get a list of all scheduled Exams
     * @return List Exam
     */
    public List<Exam> getAllExams()
    {
        TypedQuery<Exam> lstExams= eManager.createNamedQuery("Exam.GetAll", Exam.class);
        return lstExams.getResultList();
    }
}
