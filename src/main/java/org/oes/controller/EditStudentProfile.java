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
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.annotation.PostConstruct;
import java.util.Map;
import org.oes.model.User;
import org.oes.beans.UserEJB;

/**
 * <p>Class for handling the editing of student
 * profile</p>
 * @author Rumisha
 * 
 */
@Named
@RequestScoped
public class EditStudentProfile {
    
    @EJB private UserEJB userEJB;
    @Inject LoginManager loginManager;
    private User user;
    
    /**
     * <p>Initialize essential bean
     * variables, after all dependencies
     * are available</P>
     */
    @PostConstruct
    public void init()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String, Object> sObj=fContext.getExternalContext().getSessionMap();
        
        user=(User)sObj.get("userToEdit");
        
        if(user==null) //if user is null, the view is student's view.
            user=loginManager.getUser();
    }
    
     /**
     * <p>Update new values of
     * the User entity</p>
     * @return String
     */
    public String update()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        
        try {
            user=userEJB.updateUserProfile(user);
            
            fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "User profile updated!", "Profile updated for the user profile."));
        }
        catch(IllegalArgumentException iEx)
        {
            return "/pages/ErrorPage.xhtml?faces-redirect=true";
        }
        catch (Exception e) {
            
            return "/pages/ErrorPage.xhtml?faces-redirect=true";
        }
        return null;
    }
    
    /**
     * <p>Cancel the current
     * update, return to the user list</p>
     * @return String
     */
    public String cancel()
    {
        return "/pages/StudentWelcomePage.xhtml?faces-redirect=true";
    }
    
    /**
     * <p>Get User instance</P>
     * @return User
     */
    public User getUser()
    {
        return this.user;
    }
    /**
     * <p>Set current instance to 
     * user entity</P>
     */
    public void setUser(User user)
    {
        this.user=user;
    }
   
    
}
