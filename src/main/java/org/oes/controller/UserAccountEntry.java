/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import org.oes.beans.StudentEJB;
import org.oes.beans.TeacherEJB;
import org.oes.beans.AdminEJB;
import org.oes.model.Admin;
import org.oes.model.Student;
import org.oes.model.Teacher;
import org.oes.model.UserAccount;
/**
 *
 * @author Mingso
 */
@Named
@RequestScoped
public class UserAccountEntry {
    
    @EJB StudentEJB studentEJB;
    @EJB TeacherEJB teacherEJB;
    @EJB AdminEJB adminEJB;
    
    private UserAccount userAccount;
   
    @PostConstruct
    public void init()
    {
        userAccount=new UserAccount();
    }
    
    public void createUser()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String, Object> sMap=fContext.getExternalContext().getSessionMap();
        Object obj=sMap.get("objUser");
        
        
        try 
        {
            if (obj!=null) 
            {
                
                if (obj instanceof Student) 
                {
                    Student student = (Student) obj;
                    student.setUserAccount(userAccount);
                    userAccount.setUser(student);
                    studentEJB.createStudent(student);
                
                }
                else if(obj instanceof Teacher)
                {
                    Teacher teacher= (Teacher)obj;
                    teacher.setUserAccount(userAccount);
                    userAccount.setUser(teacher);
                    teacherEJB.createTeacher(teacher);
                    
                }
                else
                {
                    Admin admin=(Admin)obj;
                    admin.setUserAccount(userAccount);
                    userAccount.setUser(admin);
                    adminEJB.createAdmin(admin);
                }
                
                fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "New user created!", "New user profile and user account created!"));
                
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public UserAccount getUserAccount()
    {
        return this.userAccount;
    }
    public void setUserAccount(UserAccount uAccount)
    {
        this.userAccount=uAccount;
    }
    
}
