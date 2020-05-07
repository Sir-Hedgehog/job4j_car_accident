package ru.job4j.di;

import java.util.Scanner;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.05.2020
 */

public class ConsoleInput {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод задает вопрос пользователю с целью получения обратной связи
     * @param question - вопрос
     * @return - ответ
     */

    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
