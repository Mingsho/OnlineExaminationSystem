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
import javax.persistence.OneToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.sql.Blob;
import java.io.Serializable;
import javax.persistence.CascadeType;


/**
 *
 * @author Mingso
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "UserProfile")
@NamedQueries({
    
    @NamedQuery(name = "User.GetByUnamePwd",
            query = "SELECT U FROM User U WHERE U.userAccount.username= :uName"
                    + " AND U.userAccount.password= :pWd"),
    @NamedQuery(name="User.GetCountUnamePwd",
            query="SELECT COUNT(U) FROM User U WHERE U.userAccount.username= :uName"
                    + " AND U.userAccount.password= :pWd")
})
public class User implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(nullable = false)
    private String firstName;
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    private String contactNumber;
    private String address;
    private String email;
    private Blob profilePicture;
    
    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "user")
    private UserAccount userAccount;
    
    //<editor-fold defaultstate="Collapsed" desc="Getters & Setters">
    
    public long getUserID()
    {
        return userId;
    }
    public void setUserID(long UserID)
    {
        userId=UserID;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String FirstName)
    {
        this.firstName=FirstName;
    }
    public String getMiddleName()
    {
        return middleName;
    }
    public void setMiddleName(String MiddleName)
    {
        this.middleName= MiddleName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String LastName)
    {
        this.lastName=LastName;
    }
    public String getContactNumber()
    {
        return contactNumber;
    }
    public void setContactNumber(String ContactNumber)
    {
        this.contactNumber=ContactNumber;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String Address)
    {
        this.address=Address;
    }
    public String getEmailAddress()
    {
        return email;
    }
    public void setEmailAddress(String EmailAdd)
    {
        this.email=EmailAdd;
    }
    
    public Blob getProfilePicture()
    {
        return profilePicture;
    }
    public void setProfilePicture(Blob ProfilePic)
    {
        this.profilePicture=ProfilePic;
    }
    public UserAccount getUserAccount()
    {
        return this.userAccount;
    }
    public void setUserAccount(UserAccount userAccount)
    {
        this.userAccount=userAccount;
    }
   //</editor-fold>
    
}
