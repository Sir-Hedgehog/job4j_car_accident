package ru.job4j.accidents.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 07.07.2020
 */

@Controller
public class LoginController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    /**
     * Метод получает результат проверки на аутентификацию/авторизацию
     * @param error - успешность аутентификации
     * @param model - модель с результатом проверки на аутентификацию/авторизацию для фронта
     * @return - перевод на страницу аутентификации/авторизации
     */

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Неверно указан логин/пароль!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    /**
     * Метод конфигурирует настройки выхода из системы
     * @param request - запрос
     * @param response - ответ
     * @return - перевод на страницу аутентификации
     */

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}
