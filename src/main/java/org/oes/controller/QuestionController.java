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
import javax.annotation.PostConstruct;
import org.oes.model.OptionNumber;
import org.oes.model.Question;
import org.oes.model.Course;
import org.oes.beans.QuestionEJB;
import org.oes.beans.CourseEJB;


/**
 *
 * @author Mingso
 */
@Named("QuestionBean")
@RequestScoped
public class QuestionController {
    
    
    @EJB QuestionEJB questionEJB;
    @EJB CourseEJB courseEJB;
    @Inject
    private Question question;
    private OptionNumber correctOption;
    private List<Course> courseList;
    private Course selectedCourse;
    
    public void createQuestion()
    {
        question=questionEJB.createQuestion(question);
        
    }
    
    @PostConstruct
    public void init()
    {
        this.courseList=courseEJB.getAllCourse();
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
    public Course getSelectedCourse()
    {
        return this.selectedCourse;
    }
    public void setSelectedCourse(Course course)
    {
        this.selectedCourse=course;
    }
}
