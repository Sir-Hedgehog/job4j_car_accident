package ru.job4j.accidents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.accidents.service.AccidentService;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 04.06.2020
 */

@Controller
@RequestMapping("/")
public class IndexController {
    private final AccidentService accidents;

    @Autowired
    public IndexController(AccidentService accidents) {
        this.accidents = accidents;
    }

    /**
     * Метод получает из валидационного блока список данных о правонарушениях и передает их на фронт
     * @param modelMap - модель со списком данных (необходим для удобного парсинга)
     * @return - отображение списка данных о правонарушениях
     */

    @GetMapping(value = "/")
    public String showItems(ModelMap modelMap) {
        modelMap.addAttribute("accidents", accidents.getValidateAccidents());
        return "index";
    }
}
