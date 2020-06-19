package ru.job4j.accidents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 19.06.2020
 */

@Controller
@RequestMapping("/")
public class AccidentController {
    private final AccidentService accidentService;
    private static final Logger LOG = LoggerFactory.getLogger(AccidentController.class);

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
        this.getListOfTypes(model);
        this.getListOfRules(model);
        model.addAttribute("error", "");
        return "create";
    }

    /**
     * Метод сохраняет новое правонарушение в форме
     * @param accident - правонарушение
     * @param bindingResult - обработчик ошибок
     * @param request - запрос, необходимый для получения списка выбранных статей
     * @param model - модель для передачи данных в зависимости от результата сохранения правонарушения
     * @return - страница с результатом сохранения
     */

    @PostMapping("/save")
    public String addAccident(@Valid @ModelAttribute("accident") Accident accident,
                              BindingResult bindingResult,
                              HttpServletRequest request,
                              Model model) {
        String result = "create";
        String[] ruleIds = request.getParameterValues("ruleIds");
        if (!bindingResult.hasErrors() && ruleIds != null) {
            accidentService.createValidateAccident(accident, ruleIds);
            model.addAttribute("id", accident.getId());
            result = "successOfCreation";
        } else if (ruleIds == null || ruleIds.length == 0) {
            model.addAttribute("error", "Выберите статьи, соответствующие Вашему происшествию!");
            this.getListOfTypes(model);
            this.getListOfRules(model);
        } else {
            this.getListOfTypes(model);
            this.getListOfRules(model);
        }
        return result;
    }

    /**
     * Метод открывает страницу с вводом идентификатора
     * @param model - модель для передачи необходимых атрибутов
     * @param id - идентификатор для модели
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
                this.getListOfTypes(model);
                this.getListOfRules(model);
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
     * @param request - запрос, необходимый для получения списка выбранных статей
     * @param model - модель передает необходимые атрибутов на фронт
     * @return - страница с результатом обновления
     */

    @PostMapping("/update")
    public String updateAccident(@Valid @ModelAttribute("accident") Accident accident,
                                 BindingResult bindingResult,
                                 HttpServletRequest request,
                                 Model model) {
        String result = "update";
        String[] ruleIds = request.getParameterValues("ruleIds");
        if (ruleIds == null || ruleIds.length == 0) {
            model.addAttribute("error", "Выберите статьи, соответствующие Вашему происшествию!");
            this.getListOfTypes(model);
            this.getListOfRules(model);
        } else if (!bindingResult.hasErrors()) {
            accidentService.updateValidateAccident(accident, ruleIds);
            result = "successOfUpdate";
        } else {
            this.getListOfTypes(model);
            this.getListOfRules(model);
        }
        return result;
    }

    /**
     * Метод формирует содержимое модели, необходимой для выбора типа правонарушения (на фронт)
     * @param model - модель
     */

    private void getListOfTypes(Model model) {
        List<AccidentType> types = new ArrayList<>();
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и иной транспорт"));
        types.add(AccidentType.of(4, "Другое"));
        model.addAttribute("types", types);
    }

    /**
     * Метод формирует содержимое, необходимой для выбора типа статей (на фронт)
     * @param model - модель
     */

    private void getListOfRules(Model model) {
        List<Rule> rules = new ArrayList<>();
        rules.add(Rule.of(1, "Статья №1 КоАП РФ"));
        rules.add(Rule.of(2, "Статья №2 КоАП РФ"));
        rules.add(Rule.of(3, "Статья №3 КоАП РФ"));
        rules.add(Rule.of(4, "Статья №4 КоАП РФ"));
        rules.add(Rule.of(5, "Статья №5 КоАП РФ"));
        model.addAttribute("rules", rules);
    }
}
