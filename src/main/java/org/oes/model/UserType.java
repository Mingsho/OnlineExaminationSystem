/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

/**
 * <p>Enumeration for holding the values of different
 * User types in the application</p>
 * @author Mingso
 */
public enum UserType {
    
    STUDENT ("Student"),
    TEACHER ("Teacher"),
    ADMIN ("Admin");
    
    private String label;
    
    private UserType(String label)
    {
        this.label=label;
    }
    
    public String getLabel()
    {
        return this.label;
    }
    
}
