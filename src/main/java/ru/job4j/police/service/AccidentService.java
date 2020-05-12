package ru.job4j.police.service;

import ru.job4j.police.model.Accident;
import ru.job4j.police.repository.AccidentRepository;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.05.2020
 */

public class AccidentService {
    private static final Pattern CHECK_OF_NAME = Pattern.compile("((([A-Z])([a-z])+(\\s))(([A-Z])([a-z])+))|((([А-Я])([а-я])+(\\s))(([А-Я])([а-я])+))");
    private static final Pattern CHECK_OF_NUMBER = Pattern.compile("^([авекмнорстху])([0-9]){3}([авекмнорстху]){2}$");
    private static final Pattern CHECK_OF_TEXT = Pattern.compile("((\\w|\\s){10,})");
    private static final AccidentService INSTANCE = new AccidentService();
    private final AccidentRepository database = AccidentRepository.getInstance();

    /**
     * Метод дает право создать единственный экземпляр класса для взаимосвязи с контроллером
     * @return - экземпляр класса AccidentService
     */

    public static AccidentService getInstance() {
        return INSTANCE;
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
     * Метод проверяет введенный номер автомобиля на валидность
     * @param accident - данные по правонарушению
     * @return - валидные данные или нет
     */

    private boolean checkNumber(Accident accident) {
        Matcher matcher = CHECK_OF_NUMBER.matcher(accident.getNumber());
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

    public List<Accident> validateGetData() {
        return database.getData();
    }
}
