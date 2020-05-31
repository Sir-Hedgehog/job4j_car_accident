package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.AccidentService;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 30.05.2020
 */

@Controller
public class IndexController {
    private AccidentService accidents = new AccidentService();

    /**
     * Метод получает из валидационного блока список данных о правонарушениях и передает их на фронт
     * @param model - модель со списком данных (необходим для удобного парсинга)
     * @return - список данных о правонарушениях для jsp-файла (index.jsp)
     */

    @GetMapping(value = "/")
    public String showItems(ModelMap model) {
        model.addAttribute("accidents", accidents.validateGetData());
        return "index";
    }
}
