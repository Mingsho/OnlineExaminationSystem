/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.MapsId;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import javax.persistence.FetchType;


/**
 *
 * @author Mingso
 */

@Entity
public class UserAccount implements Serializable {
    
    @Id
    private long userID;
    private String username;
    private String password;
   
    @MapsId
    @OneToOne
    @JoinColumn(name="U_ID")
    private User user;
    
    public UserAccount(){}
    
    public long getUserID()
    {
        return this.userID;
    }
    public String getUserName()
    {
        return this.username;
    }
    public void setUserName(String uName)
    {
        this.username=uName;
    }
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String pass)
    {
        this.password= pass;
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
