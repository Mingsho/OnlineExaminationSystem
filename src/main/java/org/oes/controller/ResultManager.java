/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;

import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.util.Map;
import java.util.List;
import org.oes.model.Student;
import org.oes.model.Result;
import org.oes.beans.StudentEJB;


/**
 *
 * @author Mingso
 */
@Named
@RequestScoped
public class ResultManager {
    
    @EJB StudentEJB studentEJB;
    @Inject LoginManager loginManager;
    private List<Result> lstStudentResults;
    private Result result;
    
    /**
     * <p> Initializing method after the
     * bean has been fully intialized</p>
     */
    @PostConstruct
    public void init()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String, Object> sObjMap= fContext.getExternalContext().getSessionMap();
        
        result=(Result)sObjMap.get("studentResult");
        
        Student std= new Student();
        std.getStudentFromBaseInstance(loginManager.getUser());
        
        lstStudentResults=studentEJB.getStudentResults(std);
        
    }
    
    public Result getResult()
    {
        return this.result;
    }
    public void setResult(Result result)
    {
        this.result=result;
    }
    public List<Result> getLstStudentResults()
    {
        return this.lstStudentResults;
    }
    
    
}
