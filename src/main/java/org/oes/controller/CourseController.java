/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import java.io.Serializable;
import org.oes.beans.CourseEJB;
import org.oes.model.Course;

/**
 *
 * @author Mingso
 */
@Named("CourseBean")
@SessionScoped
public class CourseController implements Serializable {
    
    @EJB
    private CourseEJB courseEJB;
    private Course course=new Course();
    private UIComponent btnCreateCourse;
    
    
    public void createCourse()
    {
        course=courseEJB.createCourse(course);
        
        this.course=new Course();
    }
    
    public Course getCourse()
    {
        return this.course;
    }
    public void setCourse(Course course)
    {
        this.course=course;
    }
    public UIComponent getBtnCreateCourse()
    {
        return this.btnCreateCourse;
    }
    public void setBtnCreateCourse(UIComponent uComponent)
    {
        this.btnCreateCourse= uComponent;
    }
    
    
}
