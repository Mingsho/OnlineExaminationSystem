/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.TemporalType;

/**
 *
 * @author Mingso
 */
@Entity
public class Result implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long resultId;
    
    @Column(name="Attempted_Date")
    @Temporal(TemporalType.DATE)
    private Date examAttemptedDate;
    
    private int totalQuestionsAttempted;
    
    private int totalPassedQuestions;
    
    private int totalCorrectAnswers;
    
    @Enumerated(EnumType.STRING)
    private PassStatus status;
    
    public Result(){}
    
    public long getResultID()
    {
        return this.resultId;
    }
    public Date getExamAttemptedDate()
    {
        return this.examAttemptedDate;
    }
    public void setExamAttemptedDate(Date attemptedDate)
    {
        this.examAttemptedDate=attemptedDate;
    }
    public int getTotalQuestionsAttempted()
    {
        return this.totalQuestionsAttempted;
    }
    public void setTotalQuestionsAttempted(int nTotalAttemptedQuestions)
    {
        this.totalQuestionsAttempted=nTotalAttemptedQuestions;
    }
    public int getTotalPassedQuestions()
    {
        return this.totalPassedQuestions;
    }
    public void setTotalPassedQuestions(int nTotalPassedQuestions)
    {
        this.totalPassedQuestions=nTotalPassedQuestions;
    }
    public int getTotalCorrectAnswers()
    {
        return this.totalCorrectAnswers;
    }
    public void setTotalCorrectAnswers(int nTotalCorrectAnswers)
    {
        this.totalCorrectAnswers=nTotalCorrectAnswers;
    }
    public PassStatus getPassedStatus()
    {
        return this.status;
    }
    public void setPassedStatus(PassStatus stat)
    {
        this.status=stat;
    }
    
    
    
}
