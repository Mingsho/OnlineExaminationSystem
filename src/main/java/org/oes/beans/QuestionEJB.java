/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.beans;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import org.oes.model.Question;
import org.oes.utilities.Constants;


/**
 *
 * @author Mingso
 */
@Stateless
public class QuestionEJB {
    
    @PersistenceContext(name=Constants.PersistenceName)
    EntityManager eManager;
    
    public Question createQuestion(Question question)
    {
        eManager.persist(question);
        return question;
    }
    
    
    
}
