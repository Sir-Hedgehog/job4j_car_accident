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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 04.07.2020
 */

@Controller
public class LoginController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    /**
     * Метод получает результат проверки на аутентификацию/авторизацию
     * @param error - успешность аутентификации
     * @param logout - успешность авторизации
     * @param model - модель с результатом проверки на аутентификацию/авторизацию для фронта
     * @return - перевод на страницу аутентификации/авторизации
     */

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Неверно указан логин/пароль!";
        }
        if (logout != null) {
            errorMessage = "Авторизация прошла успешно!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    /**
     * Метод формирует результат авторизации
     * @param request - запрос
     * @param response - ответ
     * @return - результат успешности авторизации
     */

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}
