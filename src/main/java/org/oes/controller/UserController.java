/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;
import org.oes.model.User;
import org.oes.beans.UserEJB;
import org.oes.model.Student;
import org.oes.model.Admin;
import org.oes.model.Teacher;
import org.oes.beans.StudentEJB;
import org.oes.model.UserType;


/**
 *
 * @author Mingso
 */
@Named("UserBean")
@RequestScoped
public class UserController {
    
    //@EJB private UserEJB userEJB;
    //@EJB private StudentEJB studentEJB;
    
    //private UICommand btnCreateProfile;
    private User user=new User();
    private UserType selectedUserType;
    
    public UserController(){}
    
    public String createUser()
    {
        //outcome for the result of this subroutine.
        String navigationOutcome="failure";
        //create sessionmap from the external context.
        Map<String, Object> sessionMap=FacesContext.
                getCurrentInstance().getExternalContext().getSessionMap();
        try
        {
            
            switch(selectedUserType) //determine the selected user type from the dropdown list.
            {
                case ADMIN:
                    Admin admin= new Admin();
                    admin=admin.getAdminFromBaseInstance(user);
                    sessionMap.put("objUser", admin);
                    navigationOutcome="success";
                    break;
                case STUDENT:
                    //Student objStudent= createStudentProfile(user);
                    Student student=new Student();
                    student=student.getStudentFromBaseInstance(user);
                    sessionMap.put("objUser", student);
                    navigationOutcome="success";
                    break;
                case TEACHER:
                    Teacher teacher=new Teacher();
                    teacher=teacher.getTeacherFromBaseInstance(user);
                    sessionMap.put("objUser", teacher);
                    navigationOutcome="success";
                    break;
            }
        }
        catch(Exception eX)
        {
            System.out.println(eX.getMessage());
            //eX.printStackTrace();
        }
        
        return navigationOutcome;
    }
    
    
    public User getUser()
    {
        return this.user;
      
    }
    public void setUser(User user)
    {
        this.user=user;
    }
    
    public UserType[] getUserTypes()
    {
        return UserType.values();
    }
    
    public UserType getSelectedUserType()
    {
        return this.selectedUserType;
    }
    public void setSelectedUserType(UserType sSelectedUType)
    {
        this.selectedUserType=sSelectedUType;
    }
    
}
