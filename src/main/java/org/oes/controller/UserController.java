/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;
import javax.faces.context.ExternalContext;
import org.oes.model.User;
import org.oes.model.UserAccount;
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
@SessionScoped
@Named("UserBean")
public class UserController implements Serializable {
    
    @EJB UserEJB userEJB;
    @EJB StudentEJB studentEJB;
    
    private UIComponent btnCreateProfile;
    private UIComponent btnCreateAccount;
    private User user=new User();
    
    private UserAccount userAccount=new UserAccount();
    private UserType selectedUserType;
    private String strTest="default";
    
    ExternalContext externalContext=FacesContext.getCurrentInstance().getExternalContext();
    Map<String, Object> sessionMap=externalContext.getSessionMap();
    
    
    public String createUserProfile()
    {
        String navigationOutcome="failure";
        try
        {
            
            
            switch(selectedUserType)
            {
                case ADMIN:
                    createAdminAccount();
                    break;
                case STUDENT:
                    Student objStudent= createStudentProfile(user);
                    sessionMap.put("objUser", objStudent);
                    navigationOutcome="success";
                    break;
                case TEACHER:
                    createTeacherAccount();
                    break;
            }
            /*user= userEJB.createUserProfile(user);
            
            return "CreateUserAccount";*/
                    
        }
        catch(Exception eX)
        {
            eX.printStackTrace();
            //navigationOutcome="failure";
        }
        
        return navigationOutcome;
    }
    
    /*
    public String createUserAccount()
    {
        try
        {
            if(selectedUserType!=null && selectedUserType.isEmpty())
            {
                switch(selectedUserType)
                {
                    case "Student":
                        createStudentProfile();
                        break;
                    case "Teacher":
                        createTeacherAccount();
                        break;
                    case "Admin":
                        createAdminAccount();
                        break;
                }
            }
        }
        catch(Exception eX)
        {
            eX.printStackTrace();
        }
        return null;
    }*/
    
    public Student createStudentProfile(User user)
    {
        Student std=new Student();
        std=std.getStudentFromBaseInstance(user);
        studentEJB.testCreate(std);
        //return "CreateUserAccount";
        return std;
       
    }
    public void createTeacherAccount()
    {
        strTest="Create Teacher Account";
    }
    public void createAdminAccount()
    {
        strTest="Create Admin Account";
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
    
    public UIComponent getBtnCreateProfile()
    {
        return this.btnCreateProfile;
    }
    public void setBtnCreateProfile(UIComponent uComponent)
    {
        this.btnCreateProfile=uComponent;
    }
    public UIComponent getBtnCreateAccount()
    {
        return this.btnCreateAccount;
    }
    public void setBtnCreateAccount(UIComponent uComponent)
    {
        this.btnCreateAccount= uComponent;
    }
    public UserType getSelectedUserType()
    {
        return this.selectedUserType;
    }
    public void setSelectedUserType(UserType sSelectedUType)
    {
        this.selectedUserType=sSelectedUType;
    }
    public String getStrTest()
    {
        return this.strTest;
    }
    public void setStrTest(String strTest)
    {
        this.strTest=strTest;
    }
}
