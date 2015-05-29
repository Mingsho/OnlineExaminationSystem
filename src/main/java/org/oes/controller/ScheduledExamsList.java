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
import org.oes.model.Exam;
import org.oes.beans.ExamEJB;


/**
 * <p>Class for handling the retrieval of
 * scheduled exams</p>
 * @author Mingso
 */
@Named
@RequestScoped
public class ScheduledExamsList {
    
    @EJB private ExamEJB examEJB;
    private List<Exam> lstExam;
    
    @PostConstruct
    public void init()
    {
        lstExam=examEJB.getAllExams();
    }
    
    public List<Exam> getLstExam()
    {
        return this.lstExam;
    }
}
