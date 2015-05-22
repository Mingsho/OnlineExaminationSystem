/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.beans;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.EntityExistsException;
import javax.ejb.Stateless;
import java.util.List;
import java.util.ArrayList;
import org.oes.model.Course;
import org.oes.model.Question;
import org.oes.model.Exam;
import org.oes.model.Student;
import org.oes.utilities.Constants;


/**
 *
 * @author Mingso
 */
@Stateless
public class CourseEJB {
    
    @PersistenceContext(name=Constants.PersistenceName)
    EntityManager eManager;
    
    
    /**
     * Persist Course Entity in the db.
     * @param course
     * @return Course Type
     */
    public Course createCourse(Course course) throws EntityExistsException
    {
        eManager.persist(course);
        return course;
    }
    
    /**
     * merge the detached, persist if not exist.
     * @param course
     */
    public void updateCourse(Course course)
    {
        eManager.merge(course);
        eManager.flush();
    }
    /**
     * Get all Course records from the db.
     * @return Course List
     */
    public List<Course> getAllCourse()
    {
        TypedQuery<Course> lstCourses=eManager.
                createNamedQuery("Course.GetAllCourses", Course.class);
        return lstCourses.getResultList();
    }
    /**
     * Retrieve Course by Id.
     * @param courseId
     * @return Course
     */
    public Course getCourseById(long courseId) throws IllegalArgumentException
    {
        Query query=eManager.createNamedQuery("Course.GetCourseById");
        Course course= (Course)query.setParameter("Id", courseId).getSingleResult();
        return course;
    }
    /**
     * Check if the entity already exists.
     * @param course
     * @return boolean
     */
    public boolean isCourseExists(Course course)
    {
        return eManager.contains(course);
    }
    
    /**
     * Check if course code already exists.
     * @param course
     * @return boolean
     */
    public boolean isCourseCodeExists(Course course)
    {
        List<Course> lstCourse=getAllCourse();
        boolean bRetVal=false;
        
        for(Course c: lstCourse)
        {
            if(c.getCourseCode().equalsIgnoreCase(course.getCourseCode()))
                bRetVal=true;
           
        }
        return bRetVal;
    }
    
    /**
     * Retrieve all questions related to the course.
     * @param courseId
     * @return List Question
     */
    public List<Question> getQuestionsForCourse(long courseId)
            throws IllegalArgumentException
    {
        List<Question> lstQuestions=new ArrayList<>();
        Course course=getCourseById(courseId);
        
        lstQuestions=course.getQuestionList();
        
        return lstQuestions;
     
    }
    /**
     * Get list of Scheduled exam for a course
     * @param courseId
     * @return List Exam
     * @exception IllegalArgumentException
     */
    public List<Exam> getScheduledExam(long courseId)
            throws IllegalArgumentException
    {
        List<Exam> lstScheduledExams=new ArrayList<>();
        Course course=getCourseById(courseId);
        
        return lstScheduledExams=course.getScheduledExams();
        
    }
    
    public Course getCourseByExamId(long examId)
            throws IllegalArgumentException
    {
        Query query=eManager.createNamedQuery("Course.GetByExamId", Course.class);
        query.setParameter("Id", examId);
        
        return (Course)query.getSingleResult();
        
        
    }
    
    
}
