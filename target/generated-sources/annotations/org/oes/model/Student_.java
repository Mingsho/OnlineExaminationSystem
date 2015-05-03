package org.oes.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.oes.model.Course;
import org.oes.model.Result;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-03T14:53:53")
@StaticMetamodel(Student.class)
public class Student_ extends User_ {

    public static volatile ListAttribute<Student, Course> enrolledCourses;
    public static volatile ListAttribute<Student, Result> examResults;

}