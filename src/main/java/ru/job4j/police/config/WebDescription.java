package ru.job4j.police.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.05.2020
 */

public class WebDescription extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Метод формирует конфигурацию по корню существующего проекта
     * @return - массив классов существующего проекта
     */

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SpringRootConfig.class};
    }

    /**
     * Метод формирует конфигурацию по визуальной части проекта
     * @return - массив сущностей существующего проекта, отвечающих за визуализацию
     */

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringWebConfig.class};
    }

    /**
     * Метод возвращает отображения, которые используются при регистрации сервлета
     * @return - отображения
     */

    @Override
    protected String[] getServletMappings() {
        return new String[] {"*.do"};
    }
}
