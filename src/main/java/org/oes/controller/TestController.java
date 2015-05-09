/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.util.Map;
import org.oes.model.Student;
import org.oes.model.UserAccount;
import java.io.Serializable;

/**
 *
 * @author Mingso
 */
@Named("TestBean")
@SessionScoped
public class TestController implements Serializable {
    
    
    private UserAccount userAccount=new UserAccount();
    ExternalContext externalContext=FacesContext.getCurrentInstance().getExternalContext();
    Map<String, Object> sessionMap=externalContext.getSessionMap();
    private UIComponent testButton;
    private String strTest;
    private String strRdString;
    
   
    Object obj=sessionMap.get("objUser");
    
    public void createUserAccount()
    {
        
        if(obj instanceof Student)
        {
            Student std= (Student)obj;
            strTest=std.getFirstName();
            
        }
    }
    
    public UserAccount getUserAccount()
    {
        return this.userAccount;
    }
    public void setUserAccount(UserAccount uAccount)
    {
        this.userAccount=uAccount;
    }
    
    public String getTestString()
    {
        return this.strTest;
    }
    public void setTestString(String str)
    {
        this.strTest=str;
    }
    public UIComponent getTestButton()
    {
        return this.testButton;
    }
    public void setTestButton(UIComponent ui)
    {
        this.testButton=ui;
    }
    public String getRadioString()
    {
        return this.strRdString;
    }
    public void setRadioString(String str)
    {
        this.strRdString=str;
    }
    
}
