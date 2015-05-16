/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mingso
 */
@ManagedBean(name="LoginBean")
@SessionScoped
public class LoginController {
    
    private String userName;
    private String password;
    
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
    
}
