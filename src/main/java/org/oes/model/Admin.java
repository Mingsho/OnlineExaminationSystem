/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import java.io.Serializable;
import javax.persistence.Entity;


/**
 *
 * @author Mingso
 */

@Entity
public class Admin extends User {
    
    
    public Admin getAdminFromBaseInstance(User user)
    {
        Admin admin=new Admin();
        
        if(user!=null)
        {
            admin.setFirstName(user.getFirstName());
            admin.setMiddleName(user.getMiddleName());
            admin.setLastName(user.getMiddleName());
            admin.setContactNumber(user.getContactNumber());
            admin.setAddress(user.getAddress());
            admin.setEmailAddress(user.getEmailAddress());
            
        }
        
        return admin;
    }
    
}
