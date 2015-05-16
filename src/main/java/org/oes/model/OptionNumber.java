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
public enum OptionNumber {
    
    OPTION1("Option1"),
    OPTION2("Option2"),
    OPTION3("Option3"),
    OPTION4("Option4");
    
    private String label;
    
    private OptionNumber(String label)
    {
        this.label=label;
    }
    public String getLabel()
    {
        return this.label;
    }
    
}
