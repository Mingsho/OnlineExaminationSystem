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
import org.oes.model.Course;
import org.oes.utilities.Constants;


/**
 *
 * @author Mingso
 */
@Stateless
public class CourseEJB {
    
    @PersistenceContext(name=Constants.PersistenceName)
    EntityManager eManager;
    
    public Course createCourse(Course course) 
    {
        eManager.persist(course);
        return course;
    }
    public void updateCourse(Course course)
    {
        eManager.merge(course);
        eManager.flush();
    }
    public List<Course> getAllCourse()
    {
        TypedQuery<Course> lstCourses=eManager.
                createNamedQuery("Course.GetAllCourses", Course.class);
        return lstCourses.getResultList();
    }
    public Course getCourseById(long courseId)
    {
        Query query=eManager.createNamedQuery("Course.GetCourseById");
        Course course= (Course)query.setParameter("Id", courseId).getSingleResult();
        return course;
    }
    public boolean isCourseExists(Course course)
    {
        return eManager.contains(course);
    }
}
