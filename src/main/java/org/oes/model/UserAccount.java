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
import java.io.Serializable;
import javax.persistence.FetchType;


/**
 *
 * @author Mingso
 */

@Entity
public class UserAccount {
    
    private String username;
    private String password;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    
    public UserAccount(String username,
            String password, User user )
    {
        this.username=username;
        this.password=password;
        this.user=user;
        
    }
}
