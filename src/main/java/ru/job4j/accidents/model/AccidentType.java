package ru.job4j.accidents.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 02.07.2020
 */

@Entity(name = "AccidentType")
@Table(name = "accident_type")
@EqualsAndHashCode(exclude = "accident")
@ToString(exclude = "accident")
public class AccidentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int key;

    @OneToOne(mappedBy = "type", cascade = CascadeType.ALL)
    private Accident accident;

    @DecimalMin(value = "1", message = "Выберите тип происшествия!")
    @Column(name = "type_id", nullable = false)
    private int id;

    @Column(name = "type_name", nullable = false)
    private String name;

    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.id = id;
        type.name = name;
        return type;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Accident getAccident() {
        return accident;
    }

    public void setAccident(Accident accident) {
        this.accident = accident;
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
