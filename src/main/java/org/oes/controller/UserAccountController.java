/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;
import java.util.Map;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import javax.faces.component.UICommand;
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
@ManagedBean(name="UserAccountBean")
@RequestScoped
public class UserAccountController {
    
    @EJB StudentEJB studentEJB;
    @EJB TeacherEJB teacherEJB;
    @EJB AdminEJB adminEJB;
    
    private UserAccount userAccount=new UserAccount();
    private UICommand btnCreateProfile;
   
    
    public void createUser()
    {
        Map<String, Object> sMap=FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
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
                    student=studentEJB.createStudent(student);
                
                }
                else if(obj instanceof Teacher)
                {
                    Teacher teacher= (Teacher)obj;
                    teacher.setUserAccount(userAccount);
                    userAccount.setUser(teacher);
                    teacher= teacherEJB.createTeacher(teacher);
                    
                }
                else
                {
                    Admin admin=(Admin)obj;
                    admin.setUserAccount(userAccount);
                    userAccount.setUser(admin);
                    admin=adminEJB.createAdmin(admin);
                }
                
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
    public UICommand getBtnCreateProfile()
    {
        return this.btnCreateProfile;
    }
    public void setBtnCreateProfile(UICommand uCommand)
    {
        this.btnCreateProfile=uCommand;
    }
}
