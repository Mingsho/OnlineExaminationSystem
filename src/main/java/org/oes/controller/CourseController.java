/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.oes.beans.CourseEJB;
import org.oes.model.Course;

/**
 *
 * @author Mingso
 */
@Named
@RequestScoped
public class CourseController implements Serializable {
    
    @EJB
    private CourseEJB courseEJB;
    
    private Course course;
    
    public CourseController()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        course=new Course();
    }
    public void createCourse()
    {
        //FacesContext fContext= FacesContext.getCurrentInstance();
        
        //try {
            
            
            course=courseEJB.createCourse(course);
        
            this.course=new Course();
                
        //} catch (Exception e) {
        //}
        
    }
    
    public Course getCourse()
    {
        return this.course;
    }
    public void setCourse(Course course)
    {
        this.course=course;
    }
    
}
