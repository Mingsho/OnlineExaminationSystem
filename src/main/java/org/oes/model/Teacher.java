/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


/**
 *
 * @author Mingso
 */

@Entity
public class Teacher extends User {
    
    
    public Teacher getTeacherFromBaseInstance(User user)
    {
        Teacher teacher=new Teacher();
        if(user!=null)
        {
            teacher.setFirstName(user.getFirstName());
            teacher.setMiddleName(user.getMiddleName());
            teacher.setLastName(user.getLastName());
            teacher.setContactNumber(user.getContactNumber());
            teacher.setAddress(user.getAddress());
            teacher.setEmailAddress(user.getEmailAddress());
        }
        return teacher;
    }
    
}
