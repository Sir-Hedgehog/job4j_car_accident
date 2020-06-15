package ru.job4j.accidents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import ru.job4j.accidents.mem.AccidentMem;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import java.util.HashMap;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 04.06.2020
 */

@Configuration
@ComponentScan("ru.job4j.accidents.controller")
public class WebConfig {

    /**
     * Метод формирует единственный экземпляр хранилища
     * @return - созданное хранилище данных
     */

    @Bean
    public HashMap<Integer, Accident> getAccidentStore() {
        return new HashMap<>();
    }

    /**
     * Метод формирует единственный экземпляр класса получения данных из хранилища
     * @return - созданный экземпляр класса получения данных из хранилища
     */

    @Bean
    public AccidentMem getAccidentMem() {
        return new AccidentMem(getAccidentStore());
    }

    /**
     * Метод формирует единственный экземпляр валидационного класса
     * @return - созданный экземпляр валидационного класса
     */

    @Bean
    public AccidentService getAccidentService() {
        return new AccidentService(getAccidentMem());
    }

    /*@Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        return engine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[] {".html"});
        return viewResolver;
    }*/
}
