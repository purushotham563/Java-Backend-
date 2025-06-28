package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        StudentIdCardRepository studentIdCardRepository){

        return args -> {
//            Student puli=new Student("puli","mama","puli@gmail.com",22);
//            Student budhi=new Student("budhi","rani","budhi@gamil.com",18);
//            System.out.println("Adding puli and budhi");
//            studentRepository.saveAll(List.of(puli,budhi));
//
//            System.out.println("Number of Students");
//            System.out.println(studentRepository.count());
//            studentRepository.findById(3L).ifPresentOrElse(System.out::println,
//                ()-> System.out.print("student with id 3 not found"));
//            System.out.println("select all students ");
//            List<Student>students=studentRepository.findAll();
//            students.forEach(System.out::println);
//            System.out.println("Delete student 1");
//            studentRepository.deleteById(1L);
//            System.out.println("Number of students");
//            System.out.println(studentRepository.count());
//            studentRepository.findStudentByEmail("puli@gmail.com").ifPresentOrElse((System.out::println),
//                    ()->System.out.println("student with email id not found"));
//            studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThanNative("puli",9).forEach(System.out::println);
//            System.out.println("Deleting student by id");
//            studentRepository.deleteStudentById(2L)
//            sorting(studentRepository);
//            PageRequest pageRequest=PageRequest.of(0,5,Sort.by( "firstName").ascending().and(Sort.by("age").descending()));
//            Page<Student>page=studentRepository.findAll(pageRequest);
//            System.out.println(page);
            Faker faker=new Faker();
            String firstName=faker.name().firstName();
            String lastName=faker.name().lastName();
            String email=String.format("%s.%s@gmail.com",firstName,lastName);
            Student student=new Student(firstName,lastName,email,
                    faker.number().numberBetween(17,55));
            StudentIdCard studentIdCard=new StudentIdCard("123456789",student);
            student.addBook(new Book("Clean code", LocalDateTime.now().minusDays(4)));
            student.addBook(new Book("Think and grow rich ", LocalDateTime.now()));
            student.addBook(new Book("spring jpa", LocalDateTime.now().minusYears(1)));
            student.setStudentIdCard(studentIdCard);
//            student.enrolToCourse(new Course("Computer Science","IT"));
//            student.enrolToCourse(new Course("Spring data JPA","IT"));

            student.addEnrolment(new Enrolment(new EnrolmentId(1L,1L),student,new Course("Computer Science","IT"),LocalDateTime.now()));
            student.addEnrolment(new Enrolment(new EnrolmentId(1L,2L),student,new Course("Spring data JPA","IT"),LocalDateTime.now().minusDays(3)));




            studentRepository.save(student);
            studentRepository.findById(1L).ifPresent(s->{
                System.out.println("Fetching by lazy");
            student.getBooks().forEach(book -> System.out.println(s.getFirstName()+"borrowed"+book.getBookName()));
            }
            );
//            studentIdCardRepository.findById(1L).ifPresent(System.out::println);
//            generateRandomStudent(studentRepository);
//            studentRepository.deleteById(1L);
        };

    }
//    private void sorting(StudentRepository studentRepository) {
//
//        Sort sort=Sort.by( "firstName").ascending().and(Sort.by("age").descending());
//        studentRepository.findAll(sort).forEach(student -> System.out.println(student.getFirstName()+" "+student.getAge()));
//    }

    private void generateRandomStudent(StudentRepository studentRepository) {
        Faker faker=new Faker();
        for (int i = 0; i < 20; i++) {
            String firstName=faker.name().firstName();
            String lastName=faker.name().lastName();
            String email=String.format("%s.%s@gmail.com",firstName,lastName);
            studentRepository.save(new Student(firstName,lastName,email,faker.number().numberBetween(17,55)));
        }
    }
}
