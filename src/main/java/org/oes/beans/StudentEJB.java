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
import org.oes.model.Student;
import org.oes.model.Course;
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
    
    public boolean isStudentExist(Student std)
    {
        boolean bRetVal=false;
        
        if(eManager.contains(std))
            bRetVal=true;
        return bRetVal;
        
    }
    public List<Student> getAllStudents()
    {
        TypedQuery<Student> lstStudents=eManager.createNamedQuery("Student.GetAll", Student.class);
        
        return lstStudents.getResultList();
    }
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
    public void enrollStudent(List<Course> courses)
    {
        
    }
    
}
