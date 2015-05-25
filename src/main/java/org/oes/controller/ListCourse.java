/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;
import org.oes.model.Course;
import org.oes.beans.CourseEJB;

/**
 *
 * @author Mingso
 */

@Named
@RequestScoped
public class ListCourse {
    
    @EJB CourseEJB courseEJB;
    private List<Course> lstCourse;
    private Course courseToEdit;
    private HtmlDataTable tblCourses;
    
    @PostConstruct
    public void init()
    {
        lstCourse=courseEJB.getAllCourse();
    }
    
    /**
     * <p>edit the selected course from
     * the data table</p>
     * @return String the outcome of the action method.
     */
    public String editSelectedCourse()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String,Object> sObjMap=fContext.getExternalContext().getSessionMap();
        
        try {
            
            courseToEdit=(Course)tblCourses.getRowData();
            
            sObjMap.put("courseToEdit", courseToEdit);
            
        } 
        catch (Exception e) 
        {
            return "/pages/ErrorPage.xhtml?faces-redirect=true";
        }
        return "EditCourse?faces-redirect=true";
    }
    
    public List<Course> getLstCourse()
    {
        return this.lstCourse;
    }
    public HtmlDataTable getTblCourses()
    {
        return this.tblCourses;
    }
    public void setTblCourses(HtmlDataTable tbl)
    {
        this.tblCourses=tbl;
    }
}
