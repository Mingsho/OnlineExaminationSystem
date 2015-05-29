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
import javax.ejb.Stateless;
import java.util.List;
import java.util.ArrayList;
import org.oes.model.User;
import org.oes.model.Student;
import org.oes.model.Course;
import org.oes.model.Result;
import org.oes.utilities.Constants;

/**
 * Stateless bean related to student
 * business logic.
 * @author Mingso
 */
@Stateless
public class StudentEJB {
    
    @PersistenceContext(unitName = Constants.PersistenceName )
    EntityManager eManager;
    
    
    /**
     * <p>persist new student instance
     * to the database.</p>
     * @param std entity to be persisted.
     */
    public void createStudent(Student std)
    {
        eManager.persist(std);
        eManager.flush();
        //return std;
    }
    
    /**
     * <p>Check to see if student exists</p>
     * @param std Entity to check
     * @return boolean true or false.
     */
    public boolean isStudentExist(Student std)
    {
        boolean bRetVal=false;
        
        if(eManager.contains(std))
            bRetVal=true;
        return bRetVal;
        
    }
    
    /**
     * <p>Merge the student entity into the current
     * persistence context</p>
     * @param std Student record to merge
     * @return Student the merged record.
     */
    public Student updateStudent(Student std)
    {
        eManager.merge(std);
        eManager.flush();
        return std;
    }
    /**
     * <p>Get derived from base instance</p>
     * @param user The base User instance
     * @return Student The derived Student instance.
     */
    public Student getStudentFromBaseInstance(User user)
    {
        Student stdTemp=new Student();
        stdTemp=stdTemp.getStudentFromBaseInstance(user);
        
        return stdTemp;
    }
    /**
     * <p>get list of all
     * students in the database.</p>
     * @return List Student's list.
     */
    public List<Student> getAllStudents()
    {
        TypedQuery<Student> lstStudents=eManager.
                createNamedQuery("Student.GetAll", Student.class);
        
        return lstStudents.getResultList();
    }
    
    /**
     * <p>return a student instance by Id.</p>
     * @param studentId Student's Id.
     * @return Student required student instance.
     */
    public Student getStudentById(long studentId)
            throws IllegalArgumentException
    {
        List<Student> lstStudentTemp=getAllStudents();
        Student student=null;
        
        for(Student std: lstStudentTemp)
        {
            if(std.getUserID()==studentId)
            {
                student=std;
            }
        }
        
        return student;
    }
    /**
     * <p>get list of courses that
     * student is enrolled into.</p>
     * @param studentId Student's Id
     * @return List List of all enrolled courses.
     */
    public List<Course> getEnrolledCourses(long studentId)
    {
        Student std=getStudentById(studentId);
        List<Course> lstEnrolledCourses;
        
        if(std!=null)
            lstEnrolledCourses=std.getEnrolledCourses();
        else
            lstEnrolledCourses=null;
        
        return lstEnrolledCourses;
        
    }
    
    /**
     * <p>check if student is enrolled
     * in the particular course.</p>
     * @param courseId Enrolled course id.
     * @param studentId student's Id.
     * @return boolean true or false.
     */
    public boolean isStudentEnrolled(long courseId, long studentId)
    {
        List<Course> lstCourse= getEnrolledCourses(studentId);
        boolean bRetVal=false;
        
        if(!lstCourse.isEmpty())
        {
            for(Course course: lstCourse)
            {
                if(course.getCourseID()==courseId)
                    bRetVal=true;
            }
        }
        return bRetVal;
    }
    
    /**
     * <p>Insert student's result 
     * into the db</p>
     * @param result the result object to persist
     * @param studentId the associated student's id
     * @return Result the persisted result object.
     */
    public Result insertResult(Result result, long studentId)
    {
        List<Result> stdResultTemp=new ArrayList<>();
        
        Student student=getStudentById(studentId);
        stdResultTemp=student.getExamResults();
        
        stdResultTemp.add(result);
        
        updateStudent(student);
        
        return result;
        
    }
    
    /**
     * <p>Method to retrieve the list of
     * all results that the student has sat for</p>
     * @param student Student object for whom the result is to be retrieved.
     * @return List List of student's results.
     */
    public List<Result> getStudentResults(Student student)
    {
        List<Result> lstResult=student.getExamResults();
        
        return lstResult;
    }
    
}
