package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 28.05.2020
 */

@Controller
public class IndexControl {

    /**
     * Метод обрабатывает запрос и передает данные в файл index.jsp
     * @param model - модель
     * @return - список событий для jsp-файла (index.jsp)
     */

    @GetMapping("/")
    public String index(Model model) {
        List<String> event1 = List.of("Превышение скорости", "Вуди Аллен", "а345во", "Париж");
        List<String> event2 = List.of("Проезд на красный свет", "Златан Ибрагимович", "м701ук", "Стокгольм");
        List<String> event3 = List.of("Пересечение двойной сплошной", "Наполеон Бонапарт", "о700оо", "Марсель");
        List<String> event4 = List.of("Не пристегнул ремень безопасности", "Дон Кихот", "н690ке", "Мадрид");
        List<String> event5 = List.of("Управление авто в нетрезвом виде", "Кристофор Колумб", "м123уу", "Мехико");
        List<List<String>> events = List.of(event1, event2, event3, event4, event5);
        model.addAttribute("events", events);
        return "index";
    }
}
