/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.oes.model.Course;
import org.oes.beans.CourseEJB;

/**
 * <p> Class for handling the Edit course 
 * view logic</p>
 * @author Rumisha
 */
@Named
@RequestScoped
public class EditCourse {
    
    @EJB CourseEJB courseEJB;
    private Course course;
    
    
    /**
     * <p>method to initialize the
     * variables after the bean has 
     * been initialized</p>
     */
    @PostConstruct
    public void init()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String, Object> sObj=fContext.getExternalContext().getSessionMap();
        
        course=(Course)sObj.get("courseToEdit");
    }
    
    public String update()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        
        try {
            
            courseEJB.updateCourse(course);
            
            fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Course updated!", "The course details has been updated!"));
            
        } 
        catch (Exception e)
        {
            return "/pages/ErrorPage.xhtml?faces-redirect=true";
        }
        return null;
    }
    
    public String cancel()
    {
        return "ListCourse?faces-redirect=true";
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
