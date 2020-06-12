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
 * @version 5.0
 * @since 04.06.2020
 */

@Component
public class AccidentService {
    //private static final Logger LOG = LoggerFactory.getLogger(AccidentService.class);
    private static int counter = 0;
    private final AccidentMem mem;

    @Autowired
    public AccidentService(AccidentMem mem) {
        this.mem = mem;
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

    public void createValidateAccident(Accident accident) {
        accident.setId(++counter);
        mem.createAccident(accident);
    }

    /**
     * Метод проверяет переданные данные на валидность для обновления уже существующего заявления о правонарушении
     * @param accident - обновленные данные для существующего правонарушения
     */

    public void updateValidateAccident(Accident accident) {
        mem.updateAccident(accident);
    }
}
