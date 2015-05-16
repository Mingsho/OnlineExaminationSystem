package org.oes.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.oes.model.OptionNumber;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-14T17:39:07")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, OptionNumber> option1;
    public static volatile SingularAttribute<Question, OptionNumber> option2;
    public static volatile SingularAttribute<Question, OptionNumber> option3;
    public static volatile SingularAttribute<Question, OptionNumber> option4;
    public static volatile SingularAttribute<Question, Long> questionID;
    public static volatile SingularAttribute<Question, OptionNumber> correctOption;
    public static volatile SingularAttribute<Question, String> question;
    public static volatile SingularAttribute<Question, Float> defaultMarks;

}