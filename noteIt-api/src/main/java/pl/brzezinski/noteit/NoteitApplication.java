package pl.brzezinski.noteit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NoteitApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
        SpringApplication.run(NoteitApplication.class, args);
    }

}
