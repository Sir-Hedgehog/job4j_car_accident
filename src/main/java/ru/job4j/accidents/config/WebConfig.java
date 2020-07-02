package ru.job4j.accidents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.job4j.accidents.repository.AccidentData;
import ru.job4j.accidents.service.AccidentService;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 7.0
 * @since 02.07.2020
 */

@Configuration
@ComponentScan("ru.job4j.accidents.controller")
public class WebConfig {

    private final AccidentData accidentData;

    @Autowired
    public WebConfig(AccidentData accidentData) {
        this.accidentData = accidentData;
    }

    /**
     * Метод формирует единственный экземпляр класса получения данных из хранилища
     * @return - созданный экземпляр класса получения данных из хранилища
     */

    @Bean
    public AccidentData getAccidentData() {
        return accidentData;
    }

    /**
     * Метод формирует единственный экземпляр валидационного класса
     * @return - созданный экземпляр валидационного класса
     */

    @Bean
    public AccidentService getAccidentService() {
        return new AccidentService(getAccidentData());
    }
}
