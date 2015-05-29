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
import org.oes.utilities.Constants;


/**
 * Stateless session bean related to
 * course business logic.
 * @author Rumisha
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
     * <p>Retrieve Course by Id.</p>
     * @param courseId Course Id
     * @return Course record retrieved from db.
     */
    public Course getCourseById(long courseId) throws IllegalArgumentException
    {
        Query query=eManager.createNamedQuery("Course.GetCourseById");
        Course course= (Course)query.setParameter("Id", courseId).getSingleResult();
        return course;
    }
    /**
     * <p>Check if the entity already exists.</p>
     * @param course Course to check
     * @return boolean true or false.
     */
    public boolean isCourseExists(Course course)
    {
        return eManager.contains(course);
    }
    
    /**
     * <p>Check if course code already exists.</p>
     * @param course course to check
     * @return boolean true or false.
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
     * <p>Retrieve all questions
     * related to the course.</p>
     * @param course Course for which questions are to be retrieved.
     * @return List List of questions for the course.
     */
    public List<Question> getQuestionsForCourse(Course course)
            throws IllegalArgumentException
    {
        List<Question> lstQuestions=course.getQuestionList();
        return lstQuestions;
     
    }
    /**
     * <p>Get list of Scheduled exam for a course</p>
     * @param courseId Course Id
     * @return List List of scheduled exams
     * @exception IllegalArgumentException
     */
    public List<Exam> getScheduledExam(long courseId)
            throws IllegalArgumentException
    {
        List<Exam> lstScheduledExams=new ArrayList<>();
        Course course=getCourseById(courseId);
        
        return lstScheduledExams=course.getScheduledExams();
        
    }
    
    /**
     * <p>Get course record for an exam</p>
     * @param examId The exam id
     * @return Course the required record.
     */
    public Course getCourseByExamId(long examId)
            throws IllegalArgumentException
    {
        Query query=eManager.createNamedQuery("Course.GetByExamId", Course.class);
        query.setParameter("Id", examId);
        
        return (Course)query.getSingleResult();
        
        
    }
    
    /**
     * <p>Schedule and exam for a course</p>
     * @param exam the exam object
     * @param courseId id of the course to schedule.
     */
    public void scheduleAnExam(long courseId, Exam exam)
    {
        Course course= getCourseById(courseId);
        
        List<Exam> examList=course.getScheduledExams();
        
        examList.add(exam);
        
        course.setScheduledExams(examList);
        
        updateCourse(course);
        
    }
    
    /**
     * <p>Method to return total student
     * enrolment per course</p>
     * @return List<Object[]> list of object types
     * representing the scalar values.
     */
    public List<Object[]> totalEnrolmentsPerCourse()
    {
        String strSql="select course.COURSENAME, count(enrolment.STUDENT_FK) AS \"Total Students\" from course inner join\n" +
                    "enrolment on course.COURSEID=enrolment.COURSE_FK\n" +
                    "group by course.COURSENAME";
        
        Query query= eManager.createNativeQuery(strSql);
        
        List<Object[]> result= query.getResultList();
        
        return result;
    }
    
    /**
     * <p>Method to return total questions
     * per course</p>
     * @return List<Object[]> List of object types
     * representing the scalar values.
     */
    public List<Object[]> totalQuestionsPerCourse()
    {
        String strSql="select course.COURSENAME, count(question.QUESTIONID) as \"Total Questions\" from course\n" +
                    "inner join question on course.COURSEID=question.COURSE_QUESTION_FK\n" +
                    "Group by course.COURSENAME";
        
        Query query=eManager.createNativeQuery(strSql);
        
        List<Object[]> result=query.getResultList();
        
        return result;
                
    }
    
}
