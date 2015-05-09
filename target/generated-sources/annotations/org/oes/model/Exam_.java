package org.oes.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.oes.model.Result;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-09T13:11:51")
@StaticMetamodel(Exam.class)
public class Exam_ { 

    public static volatile SingularAttribute<Exam, Float> passPercentage;
    public static volatile ListAttribute<Exam, Result> results;
    public static volatile SingularAttribute<Exam, Long> examID;
    public static volatile SingularAttribute<Exam, Date> examEndDate;
    public static volatile SingularAttribute<Exam, Integer> examDuration;
    public static volatile SingularAttribute<Exam, String> examTitle;
    public static volatile SingularAttribute<Exam, Date> examStartDate;
    public static volatile SingularAttribute<Exam, Integer> totalQuestions;

}