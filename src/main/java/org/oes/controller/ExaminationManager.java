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
import java.util.List;
import java.util.ArrayList;
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
    private int nCurrentIndex;
    
    private List<Question> lstQuestion;
    private Map<Long,OptionNumber> userAttemp;
    
    
    @PostConstruct
    public void init()
    {
        Map<String,Object> sMap= FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        
        Object objExam=sMap.get("selectedExam");
        exam=(Exam)objExam;
        
        course=courseEJB.getCourseByExamId(exam.getExamID());
        lstQuestion=course.getQuestionList();
        nCurrentIndex=0;
        
        if(lstQuestion!=null)
            currentQuestion=lstQuestion.get(nCurrentIndex);
    }
    
    public void someAction()
    {
        userAttemp.put(currentQuestion.getQuestionID(),selectedOption);
        nCurrentIndex++;
        
        
        
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
    
}
