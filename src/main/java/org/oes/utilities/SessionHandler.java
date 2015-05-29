/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.utilities;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * <p>Class to handle the session 
 * of the logged in users</p>
 * @author Mingso
 */
public class SessionHandler {
    
    public static HttpSession getSession()
    {
        return (HttpSession)
                FacesContext.getCurrentInstance().
                getExternalContext().
                getSession(false);
    }
    
    public static HttpServletRequest getRequest()
    {
        return (HttpServletRequest)
                FacesContext.getCurrentInstance().
                getExternalContext().getRequest();
    }
    
    /**
     * <p>Get the currently logged 
     * in users username</p>
     * @return String the username
     */
    public static String getUserName()
    {
        HttpSession session= (HttpSession)FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }
    /**
     * <p>Get the user id of the logged 
     * in user</p>
     * @return String the user id.
     */
    public static String getUserId()
    {
        HttpSession session= getSession();
        if(session!=null)
            return (String)session.getAttribute("userId");
        else
            return null;
    }
    
}
