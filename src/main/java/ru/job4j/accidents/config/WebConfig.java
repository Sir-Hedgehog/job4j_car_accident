package ru.job4j.accidents.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;
import ru.job4j.accidents.service.AccidentService;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 5.0
 * @since 23.06.2020
 */

@Configuration
@ComponentScan("ru.job4j.accidents.controller")
public class WebConfig {

    /**
     * Метод формирует единственный экземпляр класса получения данных из хранилища
     * @return - созданный экземпляр класса получения данных из хранилища
     */

    @Bean
    public AccidentJdbcTemplate getAccidentJdbcTemplate() {
        return new AccidentJdbcTemplate(new JdbcTemplate());
    }

    /**
     * Метод формирует единственный экземпляр валидационного класса
     * @return - созданный экземпляр валидационного класса
     */

    @Bean
    public AccidentService getAccidentService() {
        return new AccidentService(getAccidentJdbcTemplate());
    }
}
