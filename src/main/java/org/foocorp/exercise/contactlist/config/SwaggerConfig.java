package org.foocorp.exercise.contactlist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;
import java.util.Collections;

/**
 * Spring configuration to create Springfox OpenApi 3.0 Docket
 */
@Configuration
public class SwaggerConfig {

  /**
   * Create docket
   * @return docket
   */
  @Bean
  public Docket api() {
    Contact contact = new Contact("Steven J. Woodson", "https://github/something", "swoodson@gmail.com");

    return (new Docket(DocumentationType.OAS_30)).apiInfo(new ApiInfo(
      "Contact list", "Technical exercise", "1.0.0-SNAPSHOT",
      null, contact,
      "Free-range license", null,
      Collections.emptyList())
    ).select()
      .apis(RequestHandlerSelectors.basePackage("org.foocorp.exercise.contactlist.web"))
      .paths(PathSelectors.any())
      .build()
      .directModelSubstitute(LocalDate.class, String.class)
      .useDefaultResponseMessages(false);
  }
}
