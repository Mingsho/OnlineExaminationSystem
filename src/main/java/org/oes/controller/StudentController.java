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
import org.oes.model.UserType;


/**
 *
 * @author Mingso
 */

@SessionScoped
@Named("StudentBean")
public class StudentController implements Serializable  {
    
  
    @EJB 
    private StudentEJB studentEJB;
    private UIComponent btnCreateStudentProfile;
    private Student student=new Student();
   
    public String createStudent()
    {
        String strReturnUrl=null;
        try{
            
           // student= studentEJB.createStudent(student);
            
            FacesContext.getCurrentInstance().getExternalContext().
                    getSessionMap().put("objStudent", student);
            
            strReturnUrl="CreateUserAccount";
            
            
        }
        catch(IllegalArgumentException iEx)
        {
            iEx.printStackTrace();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return strReturnUrl;
        
    }
    
    public Student getStudent()
    {
        return this.student;
    }
    
    public void setStudent(Student student)
    {
        this.student=student;
    }
    
    public UIComponent getBtnCreateProfile()
    {
        return this.btnCreateStudentProfile;
    }
    
    public void setBtnCreateProfile(UIComponent btnCreate)
    {
        this.btnCreateStudentProfile=btnCreate;
    }
  
    public UserType[] getUserTypes()
    {
        return UserType.values();
    }
    
}
