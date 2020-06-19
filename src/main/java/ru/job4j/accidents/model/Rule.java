package ru.job4j.accidents.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 19.06.2020
 */

@EqualsAndHashCode
@ToString
public class Rule {
    private int id;
    private String name;

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.id = id;
        rule.name = name;
        return rule;
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
