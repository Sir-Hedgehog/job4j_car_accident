package ru.job4j.accidents.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.AuthorityRepository;
import ru.job4j.accidents.repository.UserRepository;
import javax.validation.Valid;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.07.2020
 */

@Controller
public class RegistrationController {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public RegistrationController(PasswordEncoder encoder, UserRepository users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    /**
     * Метод переводит пользователя на страницу регистрации
     * @param model - модель
     * @return - страница регистрации
     */

    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    /**
     * Метод сохраняет данные пользователя на странице регистрации
     * @param user - данные пользователя
     * @param bindingResult - проверка на валидность
     * @return - редирект в зависимости от результата проверки на валидность
     */

    @PostMapping("/registration")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        String result = "/registration";
        if (!bindingResult.hasErrors()) {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorities.findByAuthority("ROLE_USER"));
            users.save(user);
            result = "redirect:/login";
        }
        return result;
    }
}
