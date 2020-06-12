package ru.job4j.accidents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import javax.validation.Valid;
import java.util.Set;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 04.06.2020
 */

@Controller
public class AccidentController {
    //private static final Logger LOG = LoggerFactory.getLogger(AccidentController.class);
    private final AccidentService accidents;

    @Autowired
    public AccidentController(AccidentService accidents) {
        this.accidents = accidents;
    }

    /**
     * Метод открывает страницу с созданием нового заявления
     * @return - страница создания нового заявления
     */

    @PostMapping(value = "/create")
    public String openCreationForm() {
        return "create";
    }

    /**
     * Метод сохраняет новое правонарушение в форме
     * @param accident - правонарушение
     * @param model - модель для передачи данных в зависимости от результата сохранения правонарушения
     * @return - страница с результатом сохранения
     */

    @PostMapping(value = "/save")
    public String addAccident(@Valid @ModelAttribute("accident") Accident accident, Model model, BindingResult bindingResult) {
        String result = "";
        if (bindingResult.hasErrors()) {
            result = "create";
            //model.addAttribute("error", "Введите корректные данные!");
        } else {
            accidents.createValidateAccident(accident);
            model.addAttribute("id", accident.getId());
            result = "successOfCreation";
        }

        return result;
    }

    /**
     * Метод открывает страницу с вводом идентификатора
     * @return - страница с вводом идентификатора
     */

    @PostMapping(value = "/checkId")
    public String openIdForm() {
        return "checkId";
    }

    /**
     * Метод открывает страницу с обновлением существующего заявления
     * @param enteredId - введенный идентификатор
     * @param model - модель для передачи данных в зависимости от результата ввода идентификатора
     * @return - страница обновления существующего заявления
     */

    @GetMapping(value = "/edit")
    public String openUpdateForm(@RequestParam("id") String enteredId, Model model) {
        String result = "checkId";
        Set<Integer> ids = accidents.getValidateAccidents().keySet();
        int id = Integer.parseInt(enteredId);
        if (ids.contains(id)) {
            Accident accident = accidents.getValidateAccidents().get(id);
            model.addAttribute("accident", accident);
            model.addAttribute("error", "");
            result = "update";
        } else {
            model.addAttribute("error", "Указан неверный идентификатор!");
        }
        return result;
    }

    /**
     * Метод сохраняет обновленное заявление
     * @param accident - заявление о правонарушении
     * @return - страница с результатом обновления
     */

    @PostMapping(value = "/update")
    public String updateAccident(@Valid @ModelAttribute("accident") Accident accident, BindingResult bindingResult) {
        String result = "update";
        if (!bindingResult.hasErrors()) {
            accidents.updateValidateAccident(accident);
            result = "successOfUpdate";
        }
        return result;
    }
}
