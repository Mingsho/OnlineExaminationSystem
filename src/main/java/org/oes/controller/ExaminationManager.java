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
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import org.oes.model.Exam;
import org.oes.model.Course;
import org.oes.model.Question;
import org.oes.beans.CourseEJB;
import org.oes.model.OptionNumber;

/**
 *
 * @author Mingso
 */
@Named
@SessionScoped
public class ExaminationManager implements Serializable {
    
    
    @EJB private CourseEJB courseEJB;
    private Exam exam;
    private Course course;
    private Question currentQuestion;
    private OptionNumber selectedOption;
    private OptionNumber availableOptions;
    private String nSelectedOption;
    private int nCurrentIndex=0;
    private String strTest;
    
    private List<Question> lstQuestion;
    private Map<Long,Integer> userAttempt;
    
    
    @PostConstruct
    public void init()
    {
        Map<String,Object> sMap= FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        userAttempt=new HashMap<>();
        
        Object objExam=sMap.get("selectedExam");
        exam=(Exam)objExam;
        
        course=courseEJB.getCourseByExamId(exam.getExamID());
        lstQuestion=course.getQuestionList();
        
        if(lstQuestion!=null)
            currentQuestion=lstQuestion.get(nCurrentIndex);
        
        strTest="Starting";
    }
    public String test()
    {
        strTest="Testing";
        return null;
    }
    public String nextQuestion()
    {
        FacesContext fContext= FacesContext.getCurrentInstance();
        
        try {
            
            strTest="Button entered";
            //if(selectedOption!=null)
            if(null!=nSelectedOption)
            {
                switch(nSelectedOption)
                {
                    case "1":
                        userAttempt.put(currentQuestion.getQuestionID(),1);
                        break;
                    case "2":
                        userAttempt.put(currentQuestion.getQuestionID(),2);
                        break;
                    case "3":
                        userAttempt.put(currentQuestion.getQuestionID(),3);
                        break;
                    case "4":
                        userAttempt.put(currentQuestion.getQuestionID(),4);
                        break;
                }
            }
            else
            {
                userAttempt.put(currentQuestion.getQuestionID(),0);
            }
                
            nCurrentIndex++;
            currentQuestion=lstQuestion.get(nCurrentIndex);
            
        } 
        catch (Exception e) 
        {
            strTest="Exception occured";
            fContext.addMessage(null, new FacesMessage(e.getMessage()));
            
        }
        
        
        return null;
        
    }
    
    public Exam getExam()
    {
        return this.exam;
    }
    public List<Question> getLstQuestion()
    {
        return this.lstQuestion;
    }
    public OptionNumber getSelectedOption()
    {
        return this.selectedOption;
    }
    public void setSelectedOption(OptionNumber optNumber)
    {
        this.selectedOption=optNumber;
    }
    public Question getCurrentQuestion()
    {
        return this.currentQuestion;
    }
    public String getTestString()
    {
        return this.strTest;
    }
    public void setTestString(String testString)
    {
        this.strTest= testString;
    }
    public OptionNumber getAvailableOptions()
    {
        return this.availableOptions;
    }
    public String getOptionSelected()
    {
        return this.nSelectedOption;
    }
    public void setOptionSelected(String strSelected)
    {
        this.nSelectedOption=strSelected;
    }
    
    
}
