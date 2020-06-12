package ru.job4j.accidents.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 29.05.2020
 */

@EqualsAndHashCode
@ToString
public class Accident {
    private int id;

    @Pattern(regexp = "((([A-Z])([a-z])+(\\s))(([A-Z])([a-z])+))|((([А-Я])([а-я])+(\\s))(([А-Я])([а-я])+))",
            message = "Поле должно содержать фамилию и имя с заглавной буквы и пробелом между ними!")
    @NotNull(message = "Введите Ваше имя!")
    private String name;

    @Size(min = 10, message = "Текст происшествия должен содержать не менее 10 символов!")
    @NotNull(message = "Введите текст происшествия!")
    private String text;

    @NotNull(message = "Введите адрес происшествия!")
    private String address;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
