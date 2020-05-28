package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 28.05.2020
 */

@Controller
public class IndexControl {

    /**
     * Метод обрабатывает запрос и передает данные в файл index.jsp
     * @param model - модель
     * @return - имя jsp-файла
     */

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
