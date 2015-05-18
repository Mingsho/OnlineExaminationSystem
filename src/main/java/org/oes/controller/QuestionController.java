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
@Named
@RequestScoped
public class QuestionController {
    
    
    @EJB QuestionEJB questionEJB;
    @EJB CourseEJB courseEJB;
    @Inject
    private Question question;
    private OptionNumber correctOption;
    private List<Course> courseList;
    private long selectedCourseId;
    
    public void createQuestion()
    {
        try {
            
        } catch (Exception e) {
        }
        if (selectedCourseId>0) {
            
            List<Question> lstQuestion= new ArrayList<>();
            lstQuestion.add(question);
            Course course=courseEJB.getCourseById(selectedCourseId);
            course.setQuestionList(lstQuestion);
            
            courseEJB.updateCourse(course);
            
            
        }
        this.question=new Question();
    }
    
    @PostConstruct
    public void init()
    {
        this.question=new Question();
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
    public long getSelectedCourseId()
    {
        return this.selectedCourseId;
    }
    public void setSelectedCourseId(long courseId)
    {
        this.selectedCourseId=courseId;
    }
    
}
