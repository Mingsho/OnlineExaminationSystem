/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.oes.model.OptionNumber;
import org.oes.model.Question;
import org.oes.model.Course;
import org.oes.beans.QuestionEJB;
import org.oes.beans.CourseEJB;


/**
 * <p>Class for handling the Question entry logic,
 * supporting the CreateQuestion view</p>
 * @author Rumisha
 */
@Named
@RequestScoped
public class QuestionEntry {
    
    
    @EJB QuestionEJB questionEJB;
    @EJB CourseEJB courseEJB;
    @Inject
    private Question question;
    private OptionNumber correctOption;
    private List<Course> courseList;
    private long selectedCourseId;
    
    
    /**
     * <p>Initialization method after
     * the bean has been completely
     * initialized</p>
     */
    @PostConstruct
    public void init()
    {
        this.question=new Question();
        this.courseList=courseEJB.getAllCourse();
    }
    
    /**
     * <p>Persist new question for
     * the selected course</p>
     * @return String Outcome of the action method.
     */
    public String createQuestion()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String,Object> sMap= fContext.getExternalContext().getSessionMap();
        try {
            
            Object objSelectedCourseId=sMap.get("selectedCidQuestion");
            
            if (objSelectedCourseId!=null)
            {
                selectedCourseId=(long)objSelectedCourseId;
                Course course=courseEJB.getCourseById(selectedCourseId);
                questionEJB.createQuestion(question, course);
                
                fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "New question added for the course!",
                        "Question added to the selected course"));
                
                this.question=new Question();
            
            }
        
            
        } 
        catch (Exception e)
        {
            //redirect to error page
            return "/pages/ErrorPage.xhtml?faces-redirect=true";
        }
       return null;
    }
    
    /**
     * <p>action method for the course dropdown list</p>
     * @param e variable for the valuechange event.
     */
    public void dListCourse_SelectedIndexChanged(ValueChangeEvent e)
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String,Object> sObjectId= fContext.getExternalContext().getSessionMap();
        
        selectedCourseId=(long)e.getNewValue();//get the new selected value of the drop down.
        sObjectId.put("selectedCidQuestion", selectedCourseId);
    }
    
    
    
    public OptionNumber[] getOptionNumbers()
    {
        return OptionNumber.values();
    }
    public OptionNumber getCorrectOption()
    {
        return this.correctOption;
    }
    public void setCorectOption(OptionNumber optNumber)
    {
        this.correctOption=optNumber;
    }
    public Question getQuestion()
    {
        if(question==null)
        {
            this.question=new Question();
        }
        return this.question;
    }
    public void setQuestion(Question question)
    {
        this.question=question;
    }
    public List<Course> getCourseList()
    {
        if(courseList==null)
            return null;
        return this.courseList;
    }
    public long getSelectedCourseId()
    {
        return this.selectedCourseId;
    }
    public void setSelectedCourseId(long courseId)
    {
        this.selectedCourseId=courseId;
    }
    
}
