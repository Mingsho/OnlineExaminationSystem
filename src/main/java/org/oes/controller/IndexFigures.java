/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import org.oes.beans.CourseEJB;
import org.oes.beans.UserEJB;
import org.oes.beans.ExamEJB;

/**
 * @author Mingso
 * Backing bean for the figures
 * in the index page of the application.
 */

@Named
@RequestScoped
public class IndexFigures {
    
    @EJB CourseEJB courseEJB;
    @EJB UserEJB userEJB;
    @EJB ExamEJB examEJB;
    private int nTotalCourse;
    private int nTotalUsers;
    private int nTotalExams;
    
    /**
     * <p>Initialization method
     * after the bean has been fully
     * initialized</p>
     */
    @PostConstruct
    public void init()
    {
        nTotalCourse=courseEJB.getAllCourse().size();
        nTotalUsers=userEJB.getAllUser().size();
        nTotalExams= examEJB.getAllExams().size();
    }
    
    public int getTotalCourses()
    {
        return this.nTotalCourse;
    }
    public int getTotalUsers()
    {
        return this.nTotalUsers;
    }
    public int getTotalExams()
    {
        return this.nTotalExams;
    }
    
}
