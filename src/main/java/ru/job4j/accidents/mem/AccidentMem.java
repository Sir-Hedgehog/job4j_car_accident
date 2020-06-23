package ru.job4j.accidents.mem;

import ru.job4j.accidents.model.Accident;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 6.0
 * @since 23.06.2020
 */

public class AccidentMem {

    private Map<Integer, Accident> accidents;

    public AccidentMem(Map<Integer, Accident> accidents) {
        this.accidents = accidents;
    }

    /**
     * Метод формирует результирующий список правонарушений
     * @return - список правонарушений
     */

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }

    /**
     * Метод вставляет данные о правонарушении в хранилище
     */

    public void createAccident(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    /**
     * Метод обновляет данные о правонарушении в хранилище
     */

    public void updateAccident(Accident accident) {
        accidents.put(accident.getId(), accident);
    }
}
