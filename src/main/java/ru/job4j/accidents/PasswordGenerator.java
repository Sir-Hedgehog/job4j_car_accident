package ru.job4j.accidents;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.07.2020
 */

public class PasswordGenerator {

    /**
     * Метод генерирует пароль для администратора
     */

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("electricity");
        System.out.println(password);
    }
}
