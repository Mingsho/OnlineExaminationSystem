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
import javax.faces.component.html.HtmlDataTable;
import java.util.Map;
import java.util.List;
import org.oes.model.Student;
import org.oes.model.Result;
import org.oes.beans.StudentEJB;
import org.oes.beans.ResultEJB;


/**
 *
 * @author Mingso
 */
@Named
@RequestScoped
public class ResultManager {
    
    @EJB StudentEJB studentEJB;
    @EJB ResultEJB resultEJB;
    @Inject LoginManager loginManager;
    private List<Result> lstStudentResults;
    private Result result;
    private HtmlDataTable tblExamResults;
    
    /**
     * <p> Initializing method after the
     * bean has been fully intialized</p>
     */
    @PostConstruct
    public void init()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String, Object> sObjMap= fContext.getExternalContext().getSessionMap();
        
        if(sObjMap.get("studentResult")!=null)
            result=(Result)sObjMap.get("studentResult");
        else if(sObjMap.get("viewStdResult")!=null)
            result=(Result)sObjMap.get("viewStdResult");
        
        Student std= new Student();
        std= std.getStudentFromBaseInstance(loginManager.getUser());//get student instance of the current user.
        lstStudentResults=resultEJB.getStudentResults(std);//retrieve student results list
        
        
        
    }
    
    /**
     * <p>action method to view the
     * selected result</p>
     * @return String The outcome of the action result.
     */
    public String viewResult()
    {
        Map<String, Object> sObj=FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        
        try {
            
            Result result=(Result)tblExamResults.getRowData();
            sObj.put("viewStdResult", result);
            
        } 
        catch (Exception e)
        {
            return "ErrorPage?faces-redirect=true";
        }
        return "MyResult?faces-redirect=true";
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
    public HtmlDataTable getTblExamResults()
    {
        return this.tblExamResults;
    }
    public void setTblExamResults(HtmlDataTable tbl)
    {
        this.tblExamResults=tbl;
    }
    
}
