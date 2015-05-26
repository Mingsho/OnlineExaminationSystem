/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.controller;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.event.ValueChangeEvent;
import org.oes.beans.CourseEJB;
import org.oes.model.Course;
import org.oes.model.Student;
import org.oes.beans.StudentEJB;

/**
 *
 * @author Mingso
 */
@Named
@RequestScoped
public class CourseManager implements Serializable {
    
    @EJB private CourseEJB courseEJB;
    @EJB private StudentEJB studentEJB;
    private Course course;
    private Student student;
    private List<Course> lstCourse;
    private List<Student> lstStudent;
    private String strTestString;
    private long selectedCourseId;
    private long selectedStudentId;
    
    public CourseManager()
    {
        
    }
    
    /**
     * <p>initialization method for vairables after the
     * bean has been fully initialized</p>
     */
    @PostConstruct
    public void init()
    {
        course=new Course();
        student=new Student();
        lstCourse=courseEJB.getAllCourse();
        lstStudent=studentEJB.getAllStudents();
        
    }
    
    /**
     * <p>persist a new course
     * to the db.</p>
     */
    public void createCourse()
    {
        FacesContext fContext= FacesContext.getCurrentInstance();
        
        try {
            
            if(courseEJB.isCourseCodeExists(course))//check if course code already exists.
                fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Course Code already exists! Please select a different code. ",
                        "Please select a different course code."));
            else
            {
                course=courseEJB.createCourse(course);

                fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Course Created!", "Course created successfully!"));

                this.course=new Course();
            }
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * <p> action listener for course dropdown list</p>
     * @param e event variable.
     */
    public void dListCourses_SelectedIndexChanged(ValueChangeEvent e)
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String,Object> sObjectId= fContext.getExternalContext().getSessionMap();
        
        selectedCourseId=(long)e.getNewValue();
        sObjectId.put("selectedCourseId", selectedCourseId);
        
    }
    
    /**
     * <p> action listener for student dropdown list</p>
     * @param e event variable.
     */
    public void dListStudents_SelectedIndexChanged(ValueChangeEvent e)
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String,Object> sObjectId=fContext.getExternalContext().getSessionMap();
        
        selectedStudentId=(long)e.getNewValue();
        sObjectId.put("selectedStudentId", selectedStudentId);
    }
   
    /**
     * <p>Enrol student to the selected course</p>
     * @return String Outcome of the action method.
     */
    public String enrolStudent()
    {
        FacesContext fContext=FacesContext.getCurrentInstance();
        Map<String,Object> sMap=fContext.getExternalContext().getSessionMap();
       
        try {
            
            //get selected course id and selected student id.
            Object objSelectedCId=sMap.get("selectedCourseId");
            Object objSelectedSId=sMap.get("selectedStudentId");

            if(objSelectedCId!=null && objSelectedSId!=null) //check to see if session objects are not null
            {
                long cId=(long)objSelectedCId;
                long sId=(long)objSelectedSId;
                
                if(!studentEJB.isStudentEnrolled(cId,sId))//check to see if student is already enrolled?
                {
                    Course tempCourse=courseEJB.getCourseById(cId);
                    Student tempStudent=studentEJB.getStudentById(sId);
                    
                    tempCourse.getEnrolledStudents().add(tempStudent);
                    tempStudent.getEnrolledCourses().add(tempCourse);
                    
                    courseEJB.updateCourse(tempCourse);
                    studentEJB.updateStudent(tempStudent);
                    
                    fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Student enrolled into the course!",
                    "The student has been successfully enrolled!"));
                }
                else
                    fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Student already enrolled into the selected course",
                    "Selected student has already been enrolled into the course"));

            }
            
        } 
        catch (Exception e) 
        {
            fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Something went wrong! Please try again later!",
                    "Some Error!Please try again later"));
            
            return "error";
        }
        
        return null;
             
    }
    
    public Course getCourse()
    {
        return this.course;
    }
    public void setCourse(Course course)
    {
        this.course=course;
    }
    public List<Course> getLstCourse()
    {
        return this.lstCourse;
    }
    public Student getStudent()
    {
        return this.student;
    }
    public void setStudent(Student std)
    {
        this.student=std;
    }
    public List<Student> getLstStudent()
    {
        return this.lstStudent;
    }
    public String getTestString()
    {
        return this.strTestString;
    }
    public void setTestString(String str)
    {
        this.strTestString=str;
    }
    public long getSelectedCourseId()
    {
        return this.selectedCourseId;
    }
    public void setSelectedCourseId(long lId)
    {
        this.selectedCourseId=lId;
    }
    public long getSelectedStudentId()
    {
        return this.selectedStudentId;
    }
    public void setSelectedStudentId(long lId)
    {
        this.selectedStudentId=lId;
    }
    
}
