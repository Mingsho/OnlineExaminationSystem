/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.event.ValueChangeEvent;
import org.oes.model.Course;
import org.oes.beans.CourseEJB;
import org.oes.model.Exam;
import org.oes.beans.ExamEJB;

/**
 * <p>Class for handling the logic of 
 * scheduling exams</p>
 * @author Mingso
 */
@Named
@RequestScoped
public class ScheduleExam implements Serializable {
    
    @EJB CourseEJB courseEJB;
    @EJB ExamEJB examEJB;
    private Exam exam;
    private long selectedCourseId;
    private List<Course> lstCourses;
    
    @PostConstruct
    public void init()
    {
        this.exam=new Exam();
        lstCourses=courseEJB.getAllCourse();
    }
    
    /**
     * <p>schedule a new exam</p>
     * @return String outcome of the action method.
     */
    public String scheduleExam()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String,Object> sMap=fContext.getExternalContext().getSessionMap();
        
        try {
            
            //get selectedCourseid from the session.
            Object objSelectedCourseId=sMap.get("selectedCourseId");
            
            if(objSelectedCourseId!=null)//check for null reference
            {
                selectedCourseId=(long)objSelectedCourseId;
                Course course=courseEJB.getCourseById(selectedCourseId);
                examEJB.scheduleExam(exam, course);
                
                fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "New exam has been scheduled for: "+exam.getExamStartDate(),"Exam scheduled!"));
                
                this.exam=new Exam();
            }
            
        }
        catch(IllegalArgumentException iEx)
        {
            return "ErrorPage?faces-redirect=true";
        }
        catch (Exception e)
        {
            return "ErrorPage?faces-redirect=true";
        }
        return null;
    }
    
    public void dListCourse_SelectedIndexChanged(ValueChangeEvent e)
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String,Object> sObjectId= fContext.getExternalContext().getSessionMap();
        
        selectedCourseId=(long)e.getNewValue();
        sObjectId.put("selectedCourseId", selectedCourseId);
    }
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
    public void setSelectedCourseId(long lId)
    {
        this.selectedCourseId=lId;
    }
    public List<Course> getLstCourses()
    {
        return this.lstCourses;
    }
    
}
