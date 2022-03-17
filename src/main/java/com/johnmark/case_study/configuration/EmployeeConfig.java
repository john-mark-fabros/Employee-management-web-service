package com.johnmark.case_study.configuration;

import com.johnmark.case_study.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * John Mark A. Fabros
 */
@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
           /*Employee johnMark = new Employee("John Mark", "Fabros", "johnmark@gmail.com");
            repository.save(johnMark);*/
        };

    }
}
