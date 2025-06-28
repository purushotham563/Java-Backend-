package com.example.demo;

import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Course")
@Table(name = "course")
public class Course {
    @Id
    @SequenceGenerator(name = "course_sequence",sequenceName
            = "course_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_sequence")
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name="name",nullable = false,
            columnDefinition ="TEXT")
    private String name;

    @Column(name="department",nullable = false,
            columnDefinition ="TEXT")
    private String department;

//    @ManyToMany(mappedBy ="courses")
//    private List<Student>students=new ArrayList<>();
    @OneToMany(mappedBy = "course",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Enrolment>enrolments=new ArrayList<>();
    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }
    public Course(){
    }
//    public List<Student>getStudents(){
//        return students;
//    }
   public List<Enrolment> getEnrolments() {
    return enrolments;
    }
    public void addEnrolment(Enrolment enrolment){
        if(!enrolments.contains(enrolment)){
            enrolments.add(enrolment);
        }
    }
    public void removeEnrolment(Enrolment enrolment){
        enrolments.remove(enrolment);
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}
