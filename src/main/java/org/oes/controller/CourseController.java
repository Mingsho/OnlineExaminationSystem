/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import org.oes.beans.CourseEJB;

/**
 *
 * @author Mingso
 */
public class CourseController {
    
    @EJB
    private CourseEJB courseEJB;
    
}
