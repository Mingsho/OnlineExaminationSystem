/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import java.util.Date;
import java.util.List;
import java.io.Serializable;



/**
 *
 * @author Mingso
 */
@Entity
public class Exam implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long examID;
    @Column(nullable = false, length = 50)
    private String examTitle;
    private Date examStartDate;
    private Date examEndDate;
    private int examDuration;
    private int totalQuestions;
    private float passPercentage;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List<Result> results;
    
    public Exam(){}
    
    public long getExamID()
    {
        return this.examID;
        
    }
    public String getExamTitle()
    {
        return this.examTitle;
    }
    public void setExamTitle(String eTitle)
    {
        this.examTitle=eTitle;
    }
    public Date getExamStartDate()
    {
        return this.examStartDate;
    }
    public void setExamStartDate(Date eStartDate)
    {
        this.examStartDate=eStartDate;
    }
    public Date getExamEndDate()
    {
        return this.examEndDate;
    }
    public void setExamEndDate(Date eEndDate)
    {
        this.examEndDate=eEndDate;
    }
    public int getExamDuration()
    {
        return this.examDuration;
    }
    public void setExamDuration(int eDuration)
    {
        this.examDuration=eDuration;
    }
    public int getTotlQuestions()
    {
        return this.totalQuestions;
    }
    public void setTotalQuestions(int eTotalQuestions)
    {
        this.totalQuestions=eTotalQuestions;
    }
    public float getPassPercentage()
    {
        return this.passPercentage;
    }
    public void setPassPercentage(float ePassPercentage)
    {
        this.passPercentage=ePassPercentage;
    }
    
}
