/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import javax.persistence.EntityExistsException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import org.oes.model.Exam;
import org.oes.beans.ExamEJB;
import org.oes.model.Course;
import org.oes.beans.CourseEJB;
import org.oes.model.Student;
import org.oes.beans.StudentEJB;
import org.oes.model.User;
/**
 *
 * @author Mingso
 */
@Named
@SessionScoped
public class ExamDetail implements Serializable {
    
    @EJB private ExamEJB examEJB;
    @EJB private CourseEJB courseEJB;
    @EJB private StudentEJB studentEJB;
    @Inject LoginManager loginController; //Inject LoginManager 
    
    private List<Course> lstCourses; //list of all courses.
    private long selectedCourseId; //Id of the selected course.
    private User loggedInUser; //reference to a loggedInUser
    private List<Course> lstEnrolledCourses; //list of all enrolled courses for a student.
    private List<Exam> lstScheduledExams; //list of the scheduled exams for the selected course
    private HtmlDataTable tblScheduledExam; //reference to the dataTable 
    private Exam exam;
    
    @PostConstruct
    public void init()
    {
        this.exam=new Exam();
        lstCourses=courseEJB.getAllCourse();
        loggedInUser=loginController.getUser();
        lstEnrolledCourses=studentEJB.getEnrolledCourses(loggedInUser.getUserID());
        
        /*sessionMap=fContext.getExternalContext().getSessionMap();
        
        if(sessionMap.get("scheduledExam")!=null)
        lstScheduledExams=(List<Exam>)sessionMap.get("scheduledExam");*/
        
        
        
    }
    /**
     * action listener for the drop-down list
     * @param e
     */
     public void dListEnrolledCourses_SelectedIndexChanged(ValueChangeEvent e)
    {
        //sessionMap= fContext.getExternalContext().getSessionMap();
        FacesContext fContext=FacesContext.getCurrentInstance();
        
        try {
           
            selectedCourseId= (long)e.getNewValue();
            lstScheduledExams=courseEJB.getScheduledExam(selectedCourseId);
            /* sessionMap.put("scheduledExam", lstScheduledExams);*/
            
        } catch (NullPointerException nEx) {
            
            fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No exams scheduled for the course",
                    "There are no exams scheduled for the selected course."));
            
        }
    }
     
     /**
      * get reference to the selected row object and redirect to examination page.
      * @return String
      */
    public String startExam()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String,Object> sessionMap= fContext.getExternalContext().getSessionMap();
        
        try {
            
            exam= (Exam)tblScheduledExam.getRowData();

            sessionMap.put("selectedExam", exam);
            
        } catch (Exception e) {
            
            fContext.addMessage(null, new FacesMessage(e.getMessage()));
        }
        
        return "ExaminationPage?faces-redirect=true";
    }
    /**
     * Schedules a new exam for a course.
     */
    public Exam getExam()
    {
        return this.exam;
    }
    public void setExam(Exam exam)
    {
        this.exam=exam;
    }
    
    public long getSelectedCourseId()
    {
        return this.selectedCourseId;
    }
    public void setSelectedCourseId(long cId)
    {
        this.selectedCourseId=cId;
    }
    public List<Course> getListOfCourses()
    {
        return this.lstCourses;
    }
    public User getLoggedInUser()
    {
        return this.loggedInUser;
    }
    public HtmlDataTable getTblScheduledExam()
    {
        return this.tblScheduledExam;
    }
    public void setTblScheduledExam(HtmlDataTable dTable)
    {
        this.tblScheduledExam=dTable;
    }
    public List<Course> getLstEnrolledCourses()
    {
        return this.lstEnrolledCourses;
    }
    public List<Exam> getLstScheduledExams()
    {
        if(this.lstScheduledExams!=null)
            return this.lstScheduledExams;
        return null;
    }
    
}
