/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.component.html.HtmlDataTable;
import java.util.List;
import java.util.Map;
import org.oes.model.User;
import org.oes.beans.UserEJB;

/**
 * <p>Class for handling the retrieval of the
 * users and supporting the ListUsers view</p>
 * @author Mingso
 */
@Named
@RequestScoped
public class UserList {
    
    @EJB private UserEJB userEJB;
    private List<User> lstUsers;
    private HtmlDataTable tblUserList;
    private User userToEdit;
    
    /**
     * <p>method to initialize vairables
     * after the bean has been initialized</p>
     */
    @PostConstruct
    public void init()
    {
        lstUsers=userEJB.getAllUser();
    }
    
    /**
     * <p>action method to redirect 
     * to the EditUser page</p>
     * @return String Outcome
     */
    public String editSelectedUser()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String, Object> sObjectMap=fContext.getExternalContext().getSessionMap();
        
        try {
            
            userToEdit=(User)tblUserList.getRowData();
            
            sObjectMap.put("userToEdit", userToEdit);
            
        }
        catch (Exception e)
        {
            return "/pages/ErrorPage.xhtml?faces-redirect=true";
        }
        return "EditUser?faces-redirect=true";
    }
    
    /**
     * <p>method to redirect
     * to the UserPasswordChange page</p>
     * @return String Outcome of the action method.
     */
    public String updateSelectedUserPassword()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String, Object> sObjectMap=fContext.getExternalContext().getSessionMap();
        
        try {
            userToEdit=(User)tblUserList.getRowData();
            
            sObjectMap.put("userToEdit", userToEdit);
            
        } 
        catch (Exception e)
        {
            return "/pages/ErrorPage.xhtml?faces-redirect=true";
        }
        return "UserPasswordChange?faces-redirect=true";
    }
    
    public HtmlDataTable getTblUserList()
    {
        return this.tblUserList;
    }
    public void setTblUserList(HtmlDataTable tbl)
    {
        this.tblUserList=tbl;
    }
    public List<User> getLstUsers()
    {
        return this.lstUsers;
    }
    
    
    
}
