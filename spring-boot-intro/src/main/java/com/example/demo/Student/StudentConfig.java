package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student puli=
            new Student("Puli",
                    LocalDate.of(2000, Month.JANUARY,
                    5),"puli@gmil.com");
            Student alex=
                    new Student("alex",
                            LocalDate.of(2003, Month.JANUARY,
                                    5),"alex@gmil.com");
            repository.saveAll(List.of(puli,alex));
        };
    }
}
