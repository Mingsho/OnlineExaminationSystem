/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.io.Serializable;

/**
 *
 * @author Mingso
 */
@Entity
public class Question implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionID;
    
    @Column(nullable=false, length=255)
    private String question;
    
    @Column(name="Opt1",
            length = 100,
            nullable = false)
    private String option1;
    
    @Column(name="Opt2",
            length= 100,
            nullable=false)
    private String option2;
    
    @Column(name="Opt3",
            length=100,
            nullable=false)
    private String option3;
    
    @Column(name="Opt4",
            length=100,
            nullable=false)
    private String option4;
    
    @Column(nullable=false)
    private float defaultMarks;
    
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private OptionNumber correctOption;
    
    public Question(){}
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setter">
    
    public long getQuestionID()
    {
        return this.questionID;
    }
    public String getQuestion()
    {
        return this.question;
    }
    public void setQuestion(String sQuestion)
    {
        this.question=sQuestion;
    }
    public String getOption1()
    {
        return this.option1;
    }
    public void setOption1(String sOption1)
    {
        this.option1=sOption1;
    }
    public String getOption2()
    {
        return this.option2;
    }
    public void setOption2(String sOption2)
    {
        this.option2=sOption2;
    }
    public String getOption3()
    {
        return this.option3;
    }
    public void setOption3(String sOption3)
    {
        this.option3=sOption3;
    }
    public String getOption4()
    {
        return this.option4;
    }
    public void setOption4(String sOption4)
    {
        this.option4=sOption4;
    }
    public float getDefaultMarks()
    {
        return this.defaultMarks;
    }
    public void setDefaultMarks(float fDefaultMarks)
    {
        this.defaultMarks=fDefaultMarks;
    }
    public OptionNumber getCorrectOption()
    {
        return this.correctOption;
    }
    public void setCorrectOption(OptionNumber nCorrectOption)
    {
        this.correctOption=nCorrectOption;
    }
    
    //</editor-fold>
    
}
