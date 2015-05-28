package org.oes.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.oes.model.Exam;
import org.oes.model.PassStatus;
import org.oes.model.Student;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-28T12:15:35")
@StaticMetamodel(Result.class)
public class Result_ { 

    public static volatile SingularAttribute<Result, Integer> totalCorrectAnswers;
    public static volatile SingularAttribute<Result, Date> examAttemptedDate;
    public static volatile SingularAttribute<Result, Student> student;
    public static volatile SingularAttribute<Result, PassStatus> status;
    public static volatile SingularAttribute<Result, Integer> totalQuestionsAttempted;
    public static volatile SingularAttribute<Result, Integer> totalPassedQuestions;
    public static volatile SingularAttribute<Result, Long> resultId;
    public static volatile SingularAttribute<Result, Exam> exam;

}