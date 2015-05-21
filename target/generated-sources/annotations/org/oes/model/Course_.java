package org.oes.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.oes.model.Exam;
import org.oes.model.Question;
import org.oes.model.Student;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-21T12:41:47")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile SingularAttribute<Course, String> courseDescription;
    public static volatile ListAttribute<Course, Question> questionList;
    public static volatile ListAttribute<Course, Exam> scheduledExams;
    public static volatile SingularAttribute<Course, Long> courseId;
    public static volatile SingularAttribute<Course, String> courseCode;
    public static volatile SingularAttribute<Course, String> courseName;
    public static volatile ListAttribute<Course, Student> enrolledStudents;

}