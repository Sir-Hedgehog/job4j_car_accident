package ru.job4j.di;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.05.2020
 */

public class StartUI {
    private Store store;
    private ConsoleInput input;

    public StartUI(Store store, ConsoleInput input) {
        this.store = store;
        this.input = input;
    }

    /**
     * Метод добавляет значение в список
     * @param value - значение
     */

    public void add(String value) {
        store.add(value);
    }

    /**
     * Метод печатает существующие значения
     */

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }

    /**
     * Метод задает вопрос пользователю с целью получения обратной связи
     * @param question - вопрос
     * @return - ответ
     */

    public String ask(String question) {
        return input.ask(question);
    }
}
