/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;


/**
 *
 * @author Mingso
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Course.GetAllCourses", query="SELECT C FROM Course C"),
    @NamedQuery(name="Course.GetCourseById", query="SELECT C FROM Course C WHERE C.courseId= :Id"),
    @NamedQuery(name="Course.GetByExamId",
            query="SELECT C FROM Course C INNER JOIN C.scheduledExams CS WHERE CS.examID= :Id")
})
public class Course implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseId;
    @Column(nullable = false, unique = true)
    private String courseCode;
    @Column(nullable = false)
    private String courseName;
    @Column(length = 150)
    private String courseDescription;
    
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "enrolledCourses",
            cascade = CascadeType.PERSIST)
    private List<Student> enrolledStudents;
    
    @OneToMany(fetch=FetchType.LAZY,
            cascade=CascadeType.PERSIST,
            mappedBy = "course")
    private List<Exam> scheduledExams;
    
    @OneToMany(fetch=FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            mappedBy = "course")
    private List<Question> questionList;
    
    
    public Course()
    {
        
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public long getCourseID()
    {
        return this.courseId;
    }
    public String getCourseCode()
    {
        return this.courseCode;
    }
    public void setCourseCode(String cCode)
    {
        this.courseCode=cCode;
    }
    public String getCourseName()
    {
        return this.courseName;
    }
    public void setCourseName(String cName)
    {
        this.courseName=cName;
    }
    public String getCourseDescription()
    {
        return this.courseDescription;
    }
    public void setCourseDescription(String cDescription)
    {
        this.courseDescription=cDescription;
    }
    public List<Student> getEnrolledStudents()
    {
        return this.enrolledStudents;
    }
    public void setEnrolledStudents(List<Student> lstStudents)
    {
        this.enrolledStudents=lstStudents;
    }
    
    public List<Exam> getScheduledExams()
    {
        return this.scheduledExams;
    }
    public void setScheduledExams(List<Exam> cScheduledExams)
    {
        this.scheduledExams=cScheduledExams;
    }
    public List<Question> getQuestionList()
    {
        return this.questionList;
    }
    public void setQuestionList(List<Question> lstQuestions)
    {
        this.questionList=lstQuestions;
        
    }
    
            
            
    //</editor-fold>
}
