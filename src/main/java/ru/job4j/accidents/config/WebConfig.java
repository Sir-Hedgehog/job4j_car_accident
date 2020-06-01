package ru.job4j.accidents.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.job4j.accidents.mem.AccidentMem;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import java.util.HashMap;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 01.06.2020
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

    /**
     * Метод осуществляет конфигурирование сущностей, отвечающих за визуализацию
     * @return - результат конфигурирования
     */

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
