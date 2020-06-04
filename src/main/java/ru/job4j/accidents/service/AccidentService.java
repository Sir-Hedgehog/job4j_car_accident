package ru.job4j.accidents.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.mem.AccidentMem;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 5.0
 * @since 04.06.2020
 */

@Component
public class AccidentService {
    private static final Logger LOG = LoggerFactory.getLogger(AccidentService.class);
    private static final Pattern CHECK_OF_NAME = Pattern.compile("((([A-Z])([a-z])+(\\s))(([A-Z])([a-z])+))|((([А-Я])([а-я])+(\\s))(([А-Я])([а-я])+))");
    private static final Pattern CHECK_OF_TEXT = Pattern.compile("((.){10,})");
    private static int counter = 0;
    private final AccidentMem mem;

    @Autowired
    public AccidentService(AccidentMem mem) {
        this.mem = mem;
    }

    /**
     * Метод проверяет введенное имя водителя на валидность
     * @param accident - данные по правонарушению
     * @return - валидные данные или нет
     */

    private boolean checkName(Accident accident) {
        Matcher matcher = CHECK_OF_NAME.matcher(accident.getName());
        return matcher.find();
    }

    /**
     * Метод проверяет введенное описание правонарушения на валидность
     * @param accident - данные по правонарушению
     * @return - валидные данные или нет
     */

    private boolean checkText(Accident accident) {
        Matcher matcher = CHECK_OF_TEXT.matcher(accident.getText());
        return matcher.find();
    }

    /**
     * Метод передает контроллеру данные о зарегистрированных правонарушениях
     * @return - успешность операции
     */

    public Map<Integer, Accident> getValidateAccidents() {
        return mem.getAccidents();
    }

    /**
     * Метод проверяет переданные данные на валидность для создания нового объявления о правонарушении
     * @param accident - новое правонарушение
     */

    public boolean createValidateAccident(Accident accident) {
        boolean result = false;
        if (this.checkName(accident) && this.checkText(accident)) {
            accident.setId(++counter);
            mem.createAccident(accident);
            result = true;
        }
        return result;
    }

    /**
     * Метод проверяет переданные данные на валидность для обновления уже существующего заявления о правонарушении
     * @param accident - обновленные данные для существующего правонарушения
     */

    public boolean updateValidateAccident(Accident accident) {
        boolean result = false;
        if (this.checkName(accident) && this.checkText(accident)) {
            mem.updateAccident(accident);
            result = true;
        }
        return result;
    }
}
