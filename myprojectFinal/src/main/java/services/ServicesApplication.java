/**
 * Shahow Kakavandy, 2022.
 */

package services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This is the class for the Service application.
 */

@SpringBootApplication
@EnableScheduling
public class ServicesApplication {

    public static void main(String... args) {
        SpringApplication.run(ServicesApplication.class, args);
    }
}
