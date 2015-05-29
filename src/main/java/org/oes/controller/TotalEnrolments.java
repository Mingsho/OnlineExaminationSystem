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
import java.util.List;
import org.oes.beans.CourseEJB;

/**
 * <p>Class for handling or supporting the
 * TotalEnrolmentsPerCourse View</p>
 * @author Mingso
 */
@Named
@RequestScoped
public class TotalEnrolments {
    
    @EJB CourseEJB courseEJB;
    private List<Object[]> lstEnrolments;
    
    /**
     * <p>intialization method for variables
     * after the bean has been initialized</p>
     */
    @PostConstruct
    public void init()
    {
        lstEnrolments=courseEJB.totalEnrolmentsPerCourse();
    }
    
    public List<Object[]> getLstEnrolments()
    {
        return this.lstEnrolments;
    }
    
}
