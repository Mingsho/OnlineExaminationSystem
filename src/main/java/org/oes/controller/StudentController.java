/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.ejb.EJB;
import java.io.Serializable;
import org.oes.model.Student;
import org.oes.beans.StudentEJB;


/**
 *
 * @author Mingso
 */

@ManagedBean(name = "StudentBean")
@SessionScoped
public class StudentController implements Serializable  {
    
    @EJB StudentEJB studentEJB;
    
    private UIComponent btnCreate;
    private Student student=new Student();
    
    
    
    public String createStudent()
    {
        try{
            
            student= studentEJB.createStudent(student);
            
            this.student=new Student();
            
            return "success";
            
           
           
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
    
    
}
