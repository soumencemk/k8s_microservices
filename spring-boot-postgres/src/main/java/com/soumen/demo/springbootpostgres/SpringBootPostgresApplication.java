package com.soumen.demo.springbootpostgres;

import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.stream.Stream;

@Repository
interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootPostgresApplication implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPostgresApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Soumen", "Arun", "Rabin", "Polu", "Mark")
                .map(e -> new Employee(null, e))
                .forEach(employeeRepository::save);
    }
}


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
