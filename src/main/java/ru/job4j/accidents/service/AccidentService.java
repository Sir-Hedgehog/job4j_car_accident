package ru.job4j.accidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.mem.AccidentMem;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 31.05.2020
 */

@Component
public class AccidentService {
    private static final Pattern CHECK_OF_NAME = Pattern.compile("((([A-Z])([a-z])+(\\s))(([A-Z])([a-z])+))|((([А-Я])([а-я])+(\\s))(([А-Я])([а-я])+))");
    private static final Pattern CHECK_OF_TEXT = Pattern.compile("((\\w|\\s){10,})");
    private final AccidentMem mem = new AccidentMem();

    @Autowired
    public AccidentService() {}

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

    public Map<Integer, Accident> validateGetData() {
        return mem.getData();
    }
}
