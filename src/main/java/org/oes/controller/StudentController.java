/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.oes.model.Student;
import org.oes.beans.StudentEJB;
import org.oes.model.Course;
import org.oes.beans.CourseEJB;
import org.oes.model.User;
import org.oes.beans.UserEJB;
import org.oes.model.UserType;
import org.oes.controller.LoginManager;


/**
 *
 * @author Mingso
 */


@Named
@RequestScoped
public class StudentController implements Serializable  {
    
    @EJB private StudentEJB studentEJB;
    @EJB private CourseEJB courseEJB;
    @EJB private UserEJB userEJB;
    private List<User> lstStudents; //list of all students
    private List<Course> lstCourses; //list of all available courses
    
    @PostConstruct
    public void init()
    {
        lstStudents= userEJB.getAllStudents();
    }
   
    public List<User> getLstStudents()
    {
        return this.lstStudents;
    }
    public List<Course> getLstCourses()
    {
        return this.lstCourses;
    }
    
    
}
