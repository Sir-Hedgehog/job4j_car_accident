package ru.job4j.accidents.mem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.accidents.model.Accident;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 04.06.2020
 */

@Component
public class AccidentMem {
    //private static final Logger LOG = LoggerFactory.getLogger(AccidentMem.class);
    private Map<Integer, Accident> accidents;

    @Autowired
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
