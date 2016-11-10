package edu.csupomona.cs480;

import edu.csupomona.cs480.util.CrimeStats.CrimeStats;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {

    @Bean
    public CrimeStats crimeStats() {
        CrimeStats crimeStats = new CrimeStats();
        return crimeStats;
    }

    @Bean
    public Random random() {
		return new Random();
    }

    /**
     * This is the running main method for the web application.
     * Please note that Spring requires that there is one and
     * ONLY one main method in your whole program. You can create
     * other main methods for testing or debugging purposes, but
     * you cannot put extra main method when building your project.
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
