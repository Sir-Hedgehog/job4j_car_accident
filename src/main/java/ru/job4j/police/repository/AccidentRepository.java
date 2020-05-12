package ru.job4j.police.repository;

import liquibase.exception.LiquibaseException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.police.config.LiquibaseInit;
import ru.job4j.police.model.Accident;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.05.2020
 */

public class AccidentRepository {
    private SessionFactory factory;
    private static final AccidentRepository INSTANCE = new AccidentRepository();

    /**
     * В конструкторе инициализируется объект по формированию и запуску конфигурации Hibernate и Liquibase
     */

    public AccidentRepository() {
        try {
            factory = new LiquibaseInit().init();
        } catch (SQLException | LiquibaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод дает право создать единственный экземпляр класса для взаимосвязи с валидационным блоком
     * @return - экземпляр класса AccidentRepository
     */

    public static AccidentRepository getInstance() {
        return INSTANCE;
    }

    /**
     * Метод формирует результирующий список правонарушений
     * @return - список правонарушений
     */

    public List<Accident> getData() {
        return this.template(session -> session.createQuery("from Accident", Accident.class).list());
    }

    /**
     * Метод формирует общий шаблон для редактирования БД
     * @param command - данные созданной сессии с получением на выходе отредактированную таблицу или успешность операции
     * @return - отредактированная таблица или успешность операции (в зависимости от контекста)
     */

    private <T> T template(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T result = command.apply(session);
            transaction.commit();
            return result;
        } catch (final Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }
}
