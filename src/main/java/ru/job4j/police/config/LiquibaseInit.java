package ru.job4j.police.config;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import ru.job4j.police.model.Accident;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.05.2020
 */

public class LiquibaseInit {
    public SessionFactory init() throws SQLException, LiquibaseException {
        //Подготовка hibernate-конфигурации
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("accident.cfg.xml").build();
        MetadataSources meta = new MetadataSources(registry);
        //получение соединения к базе данных
        Connection connection = meta.getServiceRegistry().getService(ConnectionProvider.class).getConnection();
        JdbcConnection jdbc = new JdbcConnection(connection);
        //инициализация Liquibase
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbc);
        Liquibase liquibase = new Liquibase("db/master.xml", new ClassLoaderResourceAccessor(), database);
        //запуск обновления
        liquibase.update("accident");
        //создание SessionFactory
        Metadata metadata = meta.addAnnotatedClass(Accident.class).buildMetadata();
        return metadata.buildSessionFactory();
    }
}
