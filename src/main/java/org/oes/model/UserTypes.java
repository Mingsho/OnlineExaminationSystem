/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.model;

/**
 *
 * @author Mingso
 */
public enum UserTypes {
    
    STUDENT ("Student"),
    TEACHER ("Teacher"),
    ADMIN ("Admin");
    
    private String label;
    
    private UserTypes(String label)
    {
        this.label=label;
    }
    
    public String getLabel()
    {
        return this.label;
    }
    
}
