package ru.job4j.accidents.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.validation.constraints.DecimalMin;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 18.06.2020
 */

@EqualsAndHashCode
@ToString
public class AccidentType {

    @DecimalMin(value = "1", message = "Выберите тип происшествия!")
    private int id;

    private String name;

    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.id = id;
        type.name = name;
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
