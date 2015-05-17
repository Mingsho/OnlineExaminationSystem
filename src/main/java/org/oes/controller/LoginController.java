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
import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import org.oes.model.User;
import org.oes.model.Student;
import org.oes.beans.UserEJB;
import org.oes.utilities.SessionHandler;


/**
 *
 * @author Mingso
 */
@Named
@SessionScoped
public class LoginController implements Serializable {
    
    
    @EJB UserEJB userEJB;
    private String userName;
    private String password;
    private String userType;
    
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
        return this.userType;
    }
    public void setUserType(String strUserType)
    {
        this.userType=strUserType;
    }
    public String login() 
    {
        try {
            
            User user=userEJB.getByUnamePwd(userName, password);
        
            if(user!=null)
            {
                HttpSession session= SessionHandler.getSession();
                session.setAttribute("username", this.userName);
                
                if(user instanceof Student)
                    return "examinee";
                
                return "administrators";
            }
            
            
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
    public String logout()  
    {
        HttpSession session=SessionHandler.getSession();
        session.invalidate();
        return "Authentication";
    }
    
}
