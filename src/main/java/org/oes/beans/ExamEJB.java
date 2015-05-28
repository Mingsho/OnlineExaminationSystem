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
import org.oes.model.Course;

/**
 *
 * @author Mingso
 * Stateless session bean related to
 * exam business logic.
 */
@Stateless
public class ExamEJB {
    
    @PersistenceContext(name=Constants.PersistenceName)
    EntityManager eManager;
    
    
    /**
     * <p>Schedule a new exam</p>
     * @param exam Exam to schedule
     * @param course Course for the exam.
     * @return Exam 
     */
    public Exam scheduleExam(Exam exam, Course course)
            throws IllegalArgumentException
    {
        exam.setCourse(course);
        eManager.persist(exam);
        eManager.flush();
        return exam;
        
    }
    
    /**
     * <p>Get a list of all scheduled Exams</p>
     * @return List List of all scheduled exams.
     */
    public List<Exam> getAllExams()
    {
        TypedQuery<Exam> lstExams= eManager.createNamedQuery("Exam.GetAll", Exam.class);
        return lstExams.getResultList();
    }
}
