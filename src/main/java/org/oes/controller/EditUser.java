/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.annotation.PostConstruct;
import java.util.Map;
import org.oes.model.User;
import org.oes.beans.UserEJB;
/**
 *
 * @author Mingso
 */
@Named
@RequestScoped
public class EditUser {
    
    @EJB private UserEJB userEJB;
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
        return "ListUsers?faces-redirect=true";
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
