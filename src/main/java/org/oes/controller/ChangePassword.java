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
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.util.Map;
import org.oes.model.UserAccount;
import org.oes.model.User;
import org.oes.beans.UserEJB;

/**
 *
 * @author Mingso
 */
@Named
@RequestScoped
public class ChangePassword {
    
    @EJB UserEJB userEJB;
    @Inject LoginManager loginManager;
    private User user;
    private UserAccount userAccount;
    
    /**
     * <p>Intialization method for
     * initializing the vairables after
     * the bean has been initialized</p>
     */
    @PostConstruct
    public void init()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String, Object> sObjMap= fContext.getExternalContext().getSessionMap();
        
        if(sObjMap.get("userToEdit")!=null) //check if the session object has the user to edit.
            user=(User)sObjMap.get("userToEdit");
        else //else retrieve info of the logged in user.
            user=loginManager.getUser();
        
        userAccount= userEJB.getUserAccount(user);
    }
    
    /**
     * <p>Update the user password</p>
     * @return String The outcome.
     */
    public String update()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        
        try {
            
            user=userEJB.updateUserProfile(user);
            
            fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "The password has been changed successfull!", "The user password has been changed!"));
        } 
        catch (Exception e)
        {
            return "/pages/ErrorPage.xhtml?faces-redirect=true";
        }
        return null;
    }
    
    /**
     * <p>Cancel action method to redirect
     * the user</p>
     * @return String Outcome
     */
    public String cancel()
    {
        return "StudentWelcomePage?faces-redirect=true";
    }
    
    public User getUser()
    {
        return this.user;
    }
    public void setUser(User user)
    {
        this.user= user;
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
