package ru.job4j.accidents.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.07.2020
 */

@Entity(name = "Authority")
@Table(name = "authorities")
@EqualsAndHashCode
@ToString
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String authority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
