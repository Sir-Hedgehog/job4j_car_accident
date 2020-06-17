package ru.job4j.accidents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 6.0
 * @since 17.06.2020
 */

@SpringBootApplication
public class AccidentsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AccidentsApplication.class);
    }

    /**
     * Метод запуска программы
     */

	public static void main(String[] args) {
	    SpringApplication.run(AccidentsApplication.class, args);
	}
}
