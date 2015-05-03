/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;
import org.oes.model.Student;
import org.oes.beans.StudentEJB;
import org.oes.model.UserTypes;


/**
 *
 * @author Mingso
 */

@SessionScoped
@Named("StudentBean")
public class StudentController implements Serializable  {
    
  
    @EJB 
    private StudentEJB studentEJB;
    private UIComponent btnCreate;
    private Student student=new Student();
   
    public String createStudent()
    {
        try{
            
            student= studentEJB.createStudent(student);
            
            return "CreateUserAccount";
            
        }
        catch(IllegalArgumentException iEx)
        {
            iEx.printStackTrace();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return null;
        
    }
    
    public Student getStudent()
    {
        return this.student;
    }
    
    public void setStudent(Student student)
    {
        this.student=student;
    }
    
    public UIComponent getBtnCreate()
    {
        return this.btnCreate;
    }
    
    public void setBtnCreate(UIComponent btnCreate)
    {
        this.btnCreate=btnCreate;
    }
  
    public UserTypes[] getUserTypes()
    {
        return UserTypes.values();
    }
    
}
