/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumns;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author Mingso
 */

@Entity
@NamedQuery(name = "Student.GetAll", query = "SELECT S FROM Student S")
public class Student extends User implements Serializable {
    
   @ManyToMany
   @JoinTable(name = "Enrolment",
           joinColumns = @JoinColumn(name="Student_FK"),
           inverseJoinColumns = @JoinColumn(name= "Course_FK"))
   private List<Course> enrolledCourses;
   
   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name="Student_Result_FK")
   private List<Result> examResults;
   
   
   public Student(){}
   
   public List<Course> getEnrolledCourses()
   {
       return this.enrolledCourses;
   }
   public void setEnrolledCourses(List<Course> lstCourses)
   {
       this.enrolledCourses=lstCourses;
   }
   public List<Result> getExamResults()
   {
       return this.examResults;
   }
   public void setExamResults(List<Result> lstResults)
   {
       this.examResults=lstResults;
   }
    
}
