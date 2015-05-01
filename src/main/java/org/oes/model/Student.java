/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
/**
 *
 * @author Mingso
 */

@Entity
@NamedQuery(name = "Student.GetAll", query = "SELECT S FROM Student S")
public class Student extends User implements Serializable {
    
   
    
}
