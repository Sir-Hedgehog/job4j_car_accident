package ru.job4j.accidents.mem;

import ru.job4j.accidents.model.Accident;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 30.05.2020
 */

public class AccidentMem {
    private static final AccidentMem INSTANCE = new AccidentMem();
    private Map<Integer, Accident> accidents = new HashMap<>();
    private static int counter = 0;

    /**
     * Метод дает право создать единственный экземпляр класса для взаимосвязи с валидационным блоком
     * @return - экземпляр класса AccidentMem
     */

    public static AccidentMem getInstance() {
        return INSTANCE;
    }

    /**
     * Метод формирует результирующий список правонарушений
     * @return - список правонарушений
     */

    public Map<Integer, Accident> getData() {
        this.imitateData();
        return accidents;
    }

    /**
     * Метод имитирует вставку правонарушений в хранилище
     */

    private void imitateData() {
        Map<Integer, Accident> current = new HashMap<>();
        Accident accident1 = new Accident();
        accident1.setId(++counter);
        accident1.setName("Кузнецов Иван");
        accident1.setText("Превышение скорости");
        accident1.setAddress("г. Москва, ул. Адмирала Лазарева, д. 35");
        Accident accident2 = new Accident();
        accident2.setId(++counter);
        accident2.setName("Наталья Петрова");
        accident2.setText("Пересечение двойной сплошной");
        accident2.setAddress("г. Москва, ул. Профсоюзная, д. 17");
        Accident accident3 = new Accident();
        accident3.setId(++counter);
        accident3.setName("Ринат Магомедов");
        accident3.setText("Не пристегнул ремень безопасности");
        accident3.setAddress("г. Москва, ул. Академика Волгина, д. 67");
        current.put(accident1.getId(), accident1);
        current.put(accident2.getId(), accident2);
        current.put(accident3.getId(), accident3);
        accidents = current;
    }
}
