package com.example.springdatajpa;

import com.example.springdatajpa.Student.Student;
import com.example.springdatajpa.Student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student seyha = new Student(
                    "Seyha",
                    "Vorn",
                    "seyhavorn@gmail.com",
                    21
            );

            Student cheat = new Student(
                    "Le",
                    "Sreycheat",
                    "lesreycheat@gmail.com",
                    21
            );

            studentRepository.saveAll(List.of(seyha, cheat));

            System.out.println("Number of student: ");
            System.out.println(studentRepository.count());

            studentRepository
                    .findById(2L)
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with Id 2 not found"));

            studentRepository
                    .findById(3L)
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with Id 3 not found.")
                    );
            System.out.println("Select all students");
            List<Student> students = studentRepository.findAll();
            students.forEach(System.out::println);

            System.out.println("Delete Seyha");
            studentRepository.deleteById(2L);

            System.out.println("Number of student: ");
            System.out.println(studentRepository.count());


        };

    }

}
