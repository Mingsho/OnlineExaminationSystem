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
import javax.persistence.EntityExistsException;
import java.io.Serializable;
import java.util.List;
import org.oes.model.Exam;
import org.oes.beans.ExamEJB;
import org.oes.model.Course;
import org.oes.beans.CourseEJB;

/**
 *
 * @author Mingso
 */
@Named
@RequestScoped
public class ExamController implements Serializable {
    
    @EJB
    private ExamEJB examEJB;
    @EJB
    private CourseEJB courseEJB;
    private Exam exam;
    private List<Course> lstCourses;
    private long selectedCourseId;
    
    @PostConstruct
    public void init()
    {
        this.exam=new Exam();
        lstCourses=courseEJB.getAllCourse();
        
    }
    /**
     * Schedules a new exam for a course.
     */
    public void scheduleExam()
    {
        try {
            
            exam=examEJB.scheduleExam(exam);
            
            this.exam=new Exam();
            
        } catch (EntityExistsException eEx) {
            
            
        }
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
    public void setSelectedCourseId(long cId)
    {
        this.selectedCourseId=cId;
    }
    public List<Course> getListOfCourses()
    {
        return this.lstCourses;
    }
    
}
