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
import java.util.List;
import org.oes.beans.CourseEJB;

/**
 * <p>Class for supporting the 
 * TotalQuestionPerCourse View of the
 * application</p>
 * @author Mingso
 */
@Named
@RequestScoped
public class TotalQuestions {
    
    @EJB CourseEJB courseEJB;
    private List<Object[]> lstQuestionsPerCourse;
    
    @PostConstruct
    public void init()
    {
        lstQuestionsPerCourse=courseEJB.totalQuestionsPerCourse();
    }
    
    public List<Object[]> getLstQuestionsPerCourse()
    {
        return this.lstQuestionsPerCourse;
    }
    
}
