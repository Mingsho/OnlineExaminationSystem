package org.oes.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-03T14:53:53")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, String> option1;
    public static volatile SingularAttribute<Question, String> option2;
    public static volatile SingularAttribute<Question, String> option3;
    public static volatile SingularAttribute<Question, String> option4;
    public static volatile SingularAttribute<Question, Long> questionID;
    public static volatile SingularAttribute<Question, Integer> correctOption;
    public static volatile SingularAttribute<Question, String> question;
    public static volatile SingularAttribute<Question, Float> defaultMarks;

}