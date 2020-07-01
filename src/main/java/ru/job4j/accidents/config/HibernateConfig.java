package ru.job4j.accidents.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 01.07.2020
 */

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class HibernateConfig {

    /**
     * Метод формирует пул соединений для выбранной в конфигурационном файле базы данных
     * @param driver - драйвер представителя
     * @param url - url базы данных
     * @param username - имя пользователя
     * @param password - пароль
     * @return - пул соединений
     */

    @Bean
    public DataSource getDataSource(@Value("${jdbc.driver}") String driver,
                         @Value("${jdbc.url}") String url,
                         @Value("${jdbc.username}") String username,
                         @Value("${jdbc.password}") String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    /**
     * Метод формирует фабрику сессий и подключает дополнительные настройки для базы данных
     * @param dialect - диалект базы данных
     * @param getDataSource - пул соединений
     * @return - фабрика сессий
     */

    @Bean
    public LocalSessionFactoryBean sessionFactory(@Value("${hibernate.dialect}") String dialect,
                                                  DataSource getDataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource);
        sessionFactory.setPackagesToScan("ru.job4j.accidents.model");
        Properties cfg = new Properties();
        cfg.setProperty("hibernate.dialect", dialect);
        sessionFactory.setHibernateProperties(cfg);
        return sessionFactory;
    }

    /**
     * Метод устанавливает вид менеджера транзакций для будущих сессий
     * @param sessionFactory - фабрика сессий
     * @return - менеджер транзакций
     */

    @Bean
    public PlatformTransactionManager htx(SessionFactory sessionFactory) {
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactory);
        return manager;
    }
}
