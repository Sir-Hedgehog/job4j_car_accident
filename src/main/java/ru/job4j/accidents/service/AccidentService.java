package ru.job4j.accidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.mem.AccidentMem;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 7.0
 * @since 18.06.2020
 */

@Component
public class AccidentService {
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
     * Метод иммитирует работу бизнес-логики в контексте создания нового объявления о правонарушении
     * @param accident - новое правонарушение
     */

    public void createValidateAccident(Accident accident) {
        accident.setId(++counter);
        this.setNameOfType(accident);
        mem.createAccident(accident);
    }

    /**
     * Метод иммитирует работу бизнес-логики в контексте обновления уже существующего заявления о правонарушении
     * @param accident - обновленные данные для существующего правонарушения
     */

    public void updateValidateAccident(Accident accident) {
        this.setNameOfType(accident);
        mem.updateAccident(accident);
    }

    /**
     * Метод устанавливает имена для идентификаторов типа правонарушения (на сервер)
     * @param accident - правонарушение
     */

    private void setNameOfType(Accident accident) {
        if (accident.getType().getId() == 1) {
            accident.getType().setName("Две машины");
        } else if (accident.getType().getId() == 2) {
            accident.getType().setName("Машина и человек");
        } else if (accident.getType().getId() == 3) {
            accident.getType().setName("Машина и иной транспорт");
        } else if (accident.getType().getId() == 4) {
            accident.getType().setName("Другое");
        }
    }
}
