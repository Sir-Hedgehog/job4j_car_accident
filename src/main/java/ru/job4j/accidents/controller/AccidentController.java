package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import javax.validation.Valid;
import java.util.Set;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 17.06.2020
 */

@Controller
@RequestMapping("/")
public class AccidentController {
    private final AccidentService accidentService;

    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    /**
     * Метод получает из блока бизнес-логики список данных о правонарушениях и передает их на фронт
     * @return - отображение списка данных о правонарушениях
     */

    @GetMapping("/")
    public ModelAndView showItems() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("accidents", accidentService.getValidateAccidents());
        return modelAndView;
    }

    /**
     * Метод открывает страницу с созданием нового заявления
     * @param model - модель создания нового заявления
     * @return - страница создания нового заявления
     */

    @PostMapping("/create")
    public String openCreationForm(Model model) {
        model.addAttribute("accident", new Accident());
        return "create";
    }

    /**
     * Метод сохраняет новое правонарушение в форме
     * @param accident - правонарушение
     * @param bindingResult - обработчик ошибок
     * @param model - модель для передачи данных в зависимости от результата сохранения правонарушения
     * @return - страница с результатом сохранения
     */

    @PostMapping("/save")
    public String addAccident(@Valid @ModelAttribute("accident") Accident accident, BindingResult bindingResult, Model model) {
        String result = "create";
        if (!bindingResult.hasErrors()) {
            accidentService.createValidateAccident(accident);
            model.addAttribute("id", accident.getId());
            result = "successOfCreation";
        }
        return result;
    }

    /**
     * Метод открывает страницу с вводом идентификатора
     * @return - страница с вводом идентификатора
     */

    @PostMapping("/checkId")
    public String openIdForm(Model model, String id) {
        model.addAttribute("id", id);
        model.addAttribute("error", "");
        return "checkId";
    }

    /**
     * Метод открывает страницу с обновлением существующего заявления
     * @param id - введенный идентификатор
     * @param model - модель для передачи данных в зависимости от результата ввода идентификатора
     * @return - страница обновления существующего заявления
     */

    @GetMapping("/edit")
    public String openUpdateForm(@RequestParam("id") String id, Model model) {
        String result = "checkId";
        Set<Integer> ids = accidentService.getValidateAccidents().keySet();
        if (id.matches("[\\d]+")) {
            int parsedId = Integer.parseInt(id);
            if (ids.contains(parsedId)) {
                Accident accident = accidentService.getValidateAccidents().get(parsedId);
                model.addAttribute("accident", accident);
                result = "update";
            } else {
                model.addAttribute("error", "Указанный идентификатор не существует!");
            }
        } else {
            model.addAttribute("error", "Идентификатор состоит только из цифр!");
        }
        return result;
    }

    /**
     * Метод сохраняет обновленное заявление
     * @param accident - заявление о правонарушении
     * @param bindingResult - обработчик ошибок
     * @return - страница с результатом обновления
     */

    @PostMapping("/update")
    public String updateAccident(@Valid @ModelAttribute("accident") Accident accident, BindingResult bindingResult) {
        String result = "update";
        if (!bindingResult.hasErrors()) {
            accidentService.updateValidateAccident(accident);
            result = "successOfUpdate";
        }
        return result;
    }
}
