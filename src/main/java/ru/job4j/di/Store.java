package ru.job4j.di;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.05.2020
 */

public class Store {
    private List<String> data = new ArrayList<>();

    /**
     * Метод добавляет значение в список
     * @param value - значение
     */

    public void add(String value) {
        data.add(value);
    }

    /**
     * Метод печатает существующие значения
     */

    public List<String> getAll() {
        return data;
    }
}
