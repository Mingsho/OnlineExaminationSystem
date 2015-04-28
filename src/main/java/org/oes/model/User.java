/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.sql.Blob;
import java.io.Serializable;


/**
 *
 * @author Mingso
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "UserProfile")
public class User implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long User_ID;
    @Column(nullable = false)
    private String FirstName;
    private String MiddleName;
    @Column(nullable = false)
    private String LastName;
    private String ContactNumber;
    private String Address;
    private String EmailAddress;
    private Blob ProfilePicture;
    
    //<editor-fold defaultstate="Collapsed" desc="Getters & Setters">
    
    public long getUserID()
    {
        return User_ID;
    }
    public void setUserID(long UserID)
    {
        User_ID=UserID;
    }
    public String getFirstName()
    {
        return FirstName;
    }
    public void setFirstName(String FirstName)
    {
        this.FirstName=FirstName;
    }
    public String getMiddleName()
    {
        return MiddleName;
    }
    public void setMiddleName(String MiddleName)
    {
        this.MiddleName= MiddleName;
    }
    public String getLastName()
    {
        return LastName;
    }
    public void setLastName(String LastName)
    {
        this.LastName=LastName;
    }
    public String getContactNumber()
    {
        return ContactNumber;
    }
    public void setContactNumber(String ContactNumber)
    {
        this.ContactNumber=ContactNumber;
    }
    public String getAddress()
    {
        return Address;
    }
    public void setAddress(String Address)
    {
        this.Address=Address;
    }
    public String getEmailAddress()
    {
        return EmailAddress;
    }
    public void setEmailAddress(String EmailAdd)
    {
        this.EmailAddress=EmailAdd;
    }
    
    public Blob getProfilePicture()
    {
        return ProfilePicture;
    }
    public void setProfilePicture(Blob ProfilePic)
    {
        this.ProfilePicture=ProfilePic;
    }
    
   //</editor-fold>
    
}
