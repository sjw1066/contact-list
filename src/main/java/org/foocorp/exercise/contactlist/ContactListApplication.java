package org.foocorp.exercise.contactlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ContactListApplication {
  public static void main(String[] args) {
    SpringApplication.run(ContactListApplication.class, args);
  }
}
