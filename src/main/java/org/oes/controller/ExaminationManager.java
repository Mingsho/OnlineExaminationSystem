/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import org.oes.model.Exam;
import org.oes.model.Course;
import org.oes.model.Question;
import org.oes.model.Result;
import org.oes.model.Student;
import org.oes.model.OptionNumber;
import org.oes.model.PassStatus;
import org.oes.beans.CourseEJB;
import org.oes.beans.ResultEJB;
import org.oes.beans.StudentEJB;


/**
 *
 * @author Mingso
 */
@Named
@SessionScoped
public class ExaminationManager implements Serializable {
    
    
    @EJB private CourseEJB courseEJB;
    @EJB private ResultEJB resultEJB;
    @EJB private StudentEJB studentEJB;
    @Inject LoginManager loginManager;
    
    private Student student;
    private Exam exam;
    private Course course;
    private Question currentQuestion;
    private String nSelectedOption;
    private int nCurrentIndex;
    
    private List<Question> lstQuestion;
    private Map<Long,Integer> userAttempt;
    
    /**
     * <p>ExaminationManager constructor</p>
     */
    public ExaminationManager()
    {
        nCurrentIndex=0;
        
    }
    
    /**
     * <p>initialize variable after 
     * the full instantiation
     * of the bean.</p>
     */
    @PostConstruct
    public void init()
    {
        Map<String,Object> sMap= FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        userAttempt=new HashMap<>();
        
        Object objExam=sMap.get("selectedExam");
        exam=(Exam)objExam;
        
        student=studentEJB.getStudentFromBaseInstance(loginManager.getUser());
        
        course=courseEJB.getCourseByExamId(exam.getExamID());
        lstQuestion=courseEJB.getQuestionsForCourse(course);
        
        if(lstQuestion!=null)
            currentQuestion=lstQuestion.get(nCurrentIndex);
        
    }
   
    /**
     * <p>action method to
     * retrieve next question.</p>
     * @return String the outcome of the action method.
     */
    public String nextQuestion()
    {
        FacesContext fContext= FacesContext.getCurrentInstance();
        
        try {
            
            if(nCurrentIndex<=(lstQuestion.size()-0x1)) //is counter less than no of questions?
            {
                if(null!=nSelectedOption)
                {
                    switch(nSelectedOption)
                    {
                        //assign integer values representing options selected.
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
                        case "5":
                            userAttempt.put(currentQuestion.getQuestionID(),5);
                            break;
                    }
                }
                else
                {
                    userAttempt.put(currentQuestion.getQuestionID(),0);
                }
                
                nCurrentIndex++; //counter increment
                
                if(nCurrentIndex<=(lstQuestion.size()-0x1)) //to prevent index out of range 
                    currentQuestion=lstQuestion.get(nCurrentIndex);
            }
            else
            {
                Map<String, Object> sObjectMap=fContext.getExternalContext().getSessionMap();
                
                //persist the calcuated result.
                Result result=calculateResult();
                
                result=resultEJB.persistResult(result, exam);
                
                result=studentEJB.insertResult(result, student.getUserID());
                
                sObjectMap.put("studentResult", result); //store user result in session.
                
                return "/pages/MyResult.xhtml?faces-redirect=true";
                
            }
        } 
        catch(NullPointerException nEx)
        {
            fContext.addMessage(null, new FacesMessage(nEx.getMessage()));
        }
        catch (Exception e) 
        {
            fContext.addMessage(null, new FacesMessage(e.getMessage()));
            
        }
        
        return null;
    }
    /**
     * <p>Calculate students result</p>
     * @return Result caluclated result object
     */
    private Result calculateResult()
            throws NullPointerException
    {
        int nTotalQuestions=lstQuestion.size();
        int nTotalCorrect=0;
        int nTotalPassed=0;
        int nTotalIncorrect=0;
        int nTotalAttempted=0;
        
        Result result=new Result();
        
        for(Question question: lstQuestion)
        {
            OptionNumber opt=question.getCorrectOption();
            Integer nUserSelection=userAttempt.get(question.getQuestionID());
            int n=opt.ordinal();
            
            if(nUserSelection.equals(opt.ordinal()+1))//as enums have indices starting from 0.
                nTotalCorrect++;
            else if(nUserSelection.equals(5)) //passed question are 5
                nTotalPassed++;
            else
                nTotalIncorrect++;
                
        }
        
        nTotalAttempted=lstQuestion.size()-nTotalPassed;
        result.setExamAttemptedDate(new Date());
        result.setTotalQuestionsAttempted(nTotalAttempted);
        result.setTotalPassedQuestions(nTotalPassed);
        result.setTotalCorrectAnswers(nTotalCorrect);
        result.setPassedStatus(determinePassStatus(nTotalCorrect,nTotalQuestions));
        
        return result;
        
    }
    
    private PassStatus determinePassStatus(int nTotalQuestions,
            int nTotalCorrect)
    {
        double percentage=(nTotalCorrect/nTotalQuestions)*100;
        
        if(percentage>60)
            return PassStatus.PASSED;
        else
            return PassStatus.FAILED;
    }
    public Exam getExam()
    {
        return this.exam;
    }
    public List<Question> getLstQuestion()
    {
        return this.lstQuestion;
    }
    public Question getCurrentQuestion()
    {
        return this.currentQuestion;
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
