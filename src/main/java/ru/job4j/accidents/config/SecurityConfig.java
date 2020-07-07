package ru.job4j.accidents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 07.07.2020
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource ds;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Метод сохраняет логин и пароль конкретных пользователей в базу данных
     * @param authentication - аутентификация
     */

    @Override
    protected final void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery("select username, password, enabled "
                                                + "from users "
                                                + "where username = ?")
                .authoritiesByUsernameQuery("select u.username, a.authority "
                                                + "from authorities as a, users as u "
                                                + "where u.username = ? and u.authority_id = a.id");
    }

    /**
     * Метод формирует кодировку паролей
     * @return - кодировка паролей
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Метод конфигурирует настройки входа пользователя в приложение
     * @param http - защищаемый URL
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/login, /registration")
                    .permitAll()
                    .antMatchers("/")
                    .hasAnyRole("ADMIN", "USER")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error=true")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .permitAll()
                .and()
                    .csrf()
                    .disable();
    }
}
