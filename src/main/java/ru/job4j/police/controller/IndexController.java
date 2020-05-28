package ru.job4j.police.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.police.service.AccidentService;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.05.2020
 */

@Controller
public class IndexController {
    private final AccidentService service = AccidentService.getInstance();

    /**
     * Метод получает из валидационного блока список данных о правонарушениях и передает их на фронт
     * @param model - модель со списком данных (необходим для удобного парсинга)
     * @return - список данных о правонарушениях для jsp-файла (accidents.jsp)
     */

    @RequestMapping(value = "/accidents", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        model.addAttribute("accidents", this.service.validateGetData());
        return "accidents";
    }
}
