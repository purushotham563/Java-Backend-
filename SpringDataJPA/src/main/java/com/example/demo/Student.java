package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Student")
@Table(name="student",uniqueConstraints = {
        @UniqueConstraint(name = "student_email_name",columnNames = "email")
})
public class Student {
    @Id
    @SequenceGenerator(name="student_sequence",sequenceName = "student_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="student_sequence")
    @Column(name="id",updatable = false)
    private Long id;
    @Column(name="first_name",nullable = false,columnDefinition = "TEXT")
    private String firstName;
    @Column(name="last_name",nullable = false,columnDefinition = "TEXT")
    private String lastName;
    @Column(name="email",nullable = false,columnDefinition = "TEXT")
    private String email;
    @Column(name="age",nullable = false)
    private Integer age;
    @OneToOne(mappedBy = "student",orphanRemoval = true,cascade ={CascadeType.REMOVE,CascadeType.PERSIST})
    private StudentIdCard studentIdCard;
    @OneToMany(mappedBy = "student",orphanRemoval = true,cascade ={CascadeType.REMOVE,CascadeType.PERSIST},fetch =FetchType.LAZY)
    private List<Book>books=new ArrayList<>();
//    @ManyToMany(
//            cascade ={CascadeType.REMOVE,CascadeType.PERSIST}
//    )
//    @JoinTable(name = "enrolment",joinColumns = @JoinColumn
//            (name="student_id",foreignKey = @ForeignKey(
//                    name="enrolment_student_fk")),inverseJoinColumns = @JoinColumn(name="course_id",foreignKey = @ForeignKey(
//            name="enrolment_course_id_fk"))
//    )
//
//    List<Course>courses=new ArrayList<>();
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "student")
    private List<Enrolment>enrolments=new ArrayList<>();


    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }
    public void setStudentIdCard(StudentIdCard studentIdCard){
        this.studentIdCard=studentIdCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
    public void addBook(Book book){
        if(!this.books.contains(book)){
            this.books.add(book);
            book.setStudent(this);
        }
    }
    public List<Book>getBooks(){
        return books;
    }
    public void removeBook(Book book){
        if(this.books.contains(book)){
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    public Student( String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }
    public Student(){

    }
//    public void enrolToCourse(Course course){
//        this.courses.add(course);
//        course.getStudents().add(this);
//    }
//    public  void unEnrollToCourse(Course course){
//        this.courses.remove(course);
//        course.getStudents().remove(this);
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
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
