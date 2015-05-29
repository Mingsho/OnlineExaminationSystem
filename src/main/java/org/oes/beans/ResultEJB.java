/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.beans;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ejb.Stateless;
import java.util.List;
import org.oes.model.Result;
import org.oes.model.Exam;
import org.oes.model.Student;
import org.oes.utilities.Constants;

/**
 * Stateless bean related to result
 * business logic.
 * @author Rumisha
 */
@Stateless
public class ResultEJB {
    
    @PersistenceContext(name=Constants.PersistenceName)
    EntityManager eManager;
    
    /**
     * <p>Persist the result 
     * into the database</p>
     * @param result entity
     * @return Result persisted entity.
     */
    public Result persistResult(Result result, Exam exam)
    {
        result.setExam(exam);
        eManager.persist(result);
        eManager.flush();
        return result;
    }
    
    /**
     * <p>Retrieve student results</p>
     * @param student The concerned student.
     * @return List List of results for the concerned student.
     */
    public List<Result> getStudentResults(Student student)
    {
        TypedQuery<Result> studentResults=eManager.
                createNamedQuery("Result.GetByStudent", Result.class);
        studentResults.setParameter("Id", student.getUserID());
        
        return studentResults.getResultList();
    }
}
