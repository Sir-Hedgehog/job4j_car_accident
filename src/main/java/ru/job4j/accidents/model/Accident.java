package ru.job4j.accidents.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 7.0
 * @since 02.07.2020
 */

@Entity(name = "Accident")
@Table(name = "accident")
@EqualsAndHashCode
@ToString
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Pattern(regexp = "((([A-Z])([a-z])+(\\s))(([A-Z])([a-z])+))|((([А-Я])([а-я])+(\\s))(([А-Я])([а-я])+))",
            message = "Поле должно содержать фамилию и имя с заглавной буквы!")
    @Column(name = "name")
    private String name;

    @Size(min = 20, message = "Текст происшествия должен содержать не менее 20 символов!")
    @Column(name = "text")
    private String text;

    @NotEmpty(message = "Введите адрес происшествия!")
    @Column(name = "address")
    private String address;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accident_type_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AccidentType type;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "accident_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Rule> rules;

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

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }
}
