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
import java.io.Serializable;
import org.oes.model.User;
import org.oes.model.UserAccount;
import org.oes.beans.UserEJB;
import org.oes.beans.StudentEJB;
import org.oes.model.UserTypes;

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
    private User user= new User();
    private UserAccount userAccount=new UserAccount();
    private String selectedUserType;
    private String strTest="default";
    
    
    public String createUserProfile()
    {
        try
        {
            user= userEJB.createUserProfile(user);
            
            return "CreateUserAccount";
                    
        }
        catch(Exception eX)
        {
            eX.printStackTrace();
        }
        
        return null;
    }
    
    public String createUserAccount()
    {
        try
        {
            if(selectedUserType!=null && selectedUserType.isEmpty())
            {
                switch(selectedUserType)
                {
                    case "Student":
                        createStudentAccount();
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
    }
    
    public void createStudentAccount()
    {
        strTest="Create Student Account";
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
    
    public UserTypes[] getUserTypes()
    {
        return UserTypes.values();
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
    public String getSelectedUserType()
    {
        return this.selectedUserType;
    }
    public void setSelectedUserType(String sSelectedUType)
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
