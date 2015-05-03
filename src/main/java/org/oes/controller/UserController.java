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
    private User user= new User();
    private UserAccount userAccount=new UserAccount();
    private String selectedUserType;
    
    
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
    
    public String createStudentAccount()
    {
        return null;
    }
    public String createTeacherAccount()
    {
        return null;
    }
    public String createAdminAccount()
    {
        return null;
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
    public String getSelectedUserType()
    {
        return this.selectedUserType;
    }
    public void setSelectedUserType(String sSelectedUType)
    {
        this.selectedUserType=sSelectedUType;
    }
    
}
