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
import java.io.Serializable;

/**
 *
 * @author Mingso
 */
@Entity
public class Question implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long Question_ID;
    private String Question;
    private String Option1;
    private String Option2;
    private String Option3;
    private String option4;
    private int DefaultMarks;
    private String CorrectOption;
    
}
