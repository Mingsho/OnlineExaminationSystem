/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpSession;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.annotation.PostConstruct;
import org.oes.model.User;
import org.oes.model.Student;
import org.oes.model.Teacher;
import org.oes.model.Admin;
import org.oes.beans.UserEJB;
import org.oes.utilities.SessionHandler;


/**
 * <p>The main class for handling the authentication
 * logic for the application. The class
 * provides methods for logging in and logging
 * out users from the system.</p>
 * @author Mingso
 */
@Named
@SessionScoped
public class LoginManager implements Serializable {
    
    
    @EJB UserEJB userEJB;
    private String userName;
    private String password;
    private User user;
    private String userType;
    
    
    @PostConstruct
    public void init()
    {
        user=new User();
    }
    /**
     * <p>Method for loggin users into the system</p>
     * @param userName the username with which users log in
     * @param password the password to be provided to the system
     * the password and username combination must match to be authenticated.
     * @return  the outcome of the action method.
     */
    public String login() 
    {
        try {
            
            user=userEJB.getByUnamePwd(userName, password);
        
            if(user!=null)
            {
                HttpSession session= SessionHandler.getSession();
                session.setAttribute("username", this.userName);
                session.setAttribute("LoggedIn", user);
                
                if(user instanceof Student)
                    return "examinee";
                
                return "administrators";
            }
            
            FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Invalid username or password. Please try again.!",
                        "Please try again!"));
        }
        
        catch(NoResultException nEx)
        {
            FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Invalid Login!",
                        "Please try again!"));
                return "Authentication";
            
        }
        return null;
    }
    /**
     * <p>Method for logging users out of the system</p>
     * @return  the outcome of the action method.
     */
    public String logout()  
    {
        HttpSession session=SessionHandler.getSession();
        session.invalidate();
        return "/pages/Authentication.xhtml";
    }
    
    public String getUserName()
    {
        return this.userName;
    }
    public void setUserName(String userName)
    {
        this.userName=userName;
    }
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public String getUserType()
    {
        if(user instanceof Admin)
            userType="Administrator";
        else if(user instanceof Teacher)
            userType="Teacher";
        else
            userType="Student";
        
        return userType;
    }
    public void setUserType(String strUserType)
    {
        this.userType=strUserType;
    }
    public User getUser()
    {
        return this.user;
    }
    public void setUser(User user)
    {
        this.user=user;
    }
    
}
