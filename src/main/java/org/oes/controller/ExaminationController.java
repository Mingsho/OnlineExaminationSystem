/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import javax.faces.event.ValueChangeEvent;
import org.oes.beans.CourseEJB;
import org.oes.model.Course;
import org.oes.beans.StudentEJB;
import org.oes.model.User;
import org.oes.model.Exam;

/**
 *
 * @author Mingso
 */
@Named
@SessionScoped
public class ExaminationController implements Serializable {
    
    
    @EJB private CourseEJB courseEJB;
    @EJB private StudentEJB studentEJB;
    @Inject LoginController loginController; //Inject LoginController 
    private User loggedInUser; //reference to a loggedInUser
    private List<Course> lstEnrolledCourses; //list of all enrolled courses for a student.
    private List<Exam> lstScheduledExams;
    private long selectedCourseId; //the selected course id from the dropdown list.
    
    @PostConstruct
    public void init()
    {
        loggedInUser=loginController.getUser();
        lstEnrolledCourses=studentEJB.getEnrolledCourses(loggedInUser.getUserID());
    }
    
    public void dListEnrolledCourses_SelectedIndexChanged(ValueChangeEvent e)
    {
        selectedCourseId= (long)e.getNewValue();
        
    }
    public User getLoggedInUser()
    {
        return this.loggedInUser;
    }
    public List<Course> getLstEnrolledCourses()
    {
        return this.lstEnrolledCourses;
    }
    public long getSelectedCourseId()
    {
        return this.selectedCourseId;
    }
    public void setSelectedCourseId(long lId)
    {
        this.selectedCourseId=lId;
    }
}
