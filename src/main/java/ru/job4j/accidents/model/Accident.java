package ru.job4j.accidents.model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 17.06.2020
 */

@EqualsAndHashCode
@ToString
public class Accident {
    private int id;

    @Pattern(regexp = "((([A-Z])([a-z])+(\\s))(([A-Z])([a-z])+))|((([А-Я])([а-я])+(\\s))(([А-Я])([а-я])+))",
            message = "Поле должно содержать фамилию и имя с заглавной буквы!")
    private String name;

    @Size(min = 20, message = "Текст происшествия должен содержать не менее 20 символов!")
    private String text;

    @NotEmpty(message = "Введите адрес происшествия!")
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
