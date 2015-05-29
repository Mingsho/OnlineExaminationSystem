/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.TemporalType;



/**
 * <p>Exam entity for holding information related
 * to exam in the application</p>
 * @author Mingso
 */
@Entity
@NamedQuery( name="Exam.GetAll",
        query="SELECT E FROM Exam E ORDER BY E.examID")
public class Exam implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long examID;
    @Column(nullable = false, length = 50)
    
    private String examTitle;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date examStartDate;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date examEndDate;
    
    private int examDuration;
    
    private int totalQuestions;
    
    private float passPercentage;
    
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "exam",
            cascade = CascadeType.PERSIST)
    private List<Result> lstResults;
    
    @ManyToOne
    @JoinColumn(name="COURSE_EXAM_FK")
    private Course course;
    
    public Exam(){}
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
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
    public int getTotalQuestions()
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
    public List<Result> getLstResult()
    {
        return this.lstResults;
    }
    public void setLstResult(List<Result> resultList)
    {
        this.lstResults=resultList;
    }
    public Course getCourse()
    {
        return this.course;
    }
    public void setCourse(Course course)
    {
        this.course=course;
    }
    
    //</editor-fold>
    
    
    
}
