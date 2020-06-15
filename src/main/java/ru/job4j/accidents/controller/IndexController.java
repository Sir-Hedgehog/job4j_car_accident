package ru.job4j.accidents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.accidents.service.AccidentService;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 04.06.2020
 */

@Controller
@RequestMapping("/")
public class IndexController {
    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
    private final AccidentService accidentService;

    public IndexController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    /**
     * Метод получает из валидационного блока список данных о правонарушениях и передает их на фронт
     *
     * @return - отображение списка данных о правонарушениях
     */

    @GetMapping(value = "/")
    public ModelAndView showItems() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("accidents", accidentService.getValidateAccidents());
        return modelAndView;
    }
}
