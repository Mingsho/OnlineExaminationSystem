/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author Mingso
 */
@Entity
public class Course implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;
    @Column(nullable = false, unique = true)
    private String courseCode;
    @Column(nullable = false)
    private String courseName;
    @Column(length = 150)
    private String courseDescription;
    
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "enrolledCourses")
    private List<Student> enrolledStudents;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "Course_FK")
    private List<Exam> scheduledExams;
    
    public Course()
    {
        
    }
    
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
            
            
    
}
