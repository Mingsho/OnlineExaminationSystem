/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.beans;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.ejb.Stateless;
import org.oes.model.Result;
import org.oes.model.Exam;
import org.oes.utilities.Constants;
/**
 *
 * @author Mingso
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
}
