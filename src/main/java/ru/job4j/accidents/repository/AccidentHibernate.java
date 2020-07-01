package ru.job4j.accidents.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 01.07.2020
 */

@Repository
public class AccidentHibernate {
    private final SessionFactory sessionFactory;

    public AccidentHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Метод сохраняет данные о новом правонарушении в базу данных
     * @param accident - данные о правонарушении
     */

    public void createAccident(Accident accident) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
            session.close();
        }
    }

    /**
     * Метод отображает существующие данные о всех правонарушениях
     * @return - данные о правонарушениях
     */

    public List<Accident> getAccidents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Accident", Accident.class).list();
        }
    }

    /**
     * Метод обновляет данные о правонарушении
     * @param accident - данные о правонарушении
     */

    public void updateAccident(Accident accident) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //Получение уникального идентификатора старого значения типа
            Accident oldAccident = session.createQuery("from Accident where id = " +  accident.getId(), Accident.class).list().get(0);
            int oldAccidentTypeId = oldAccident.getType().getKey();
            session.flush();
            session.clear();
            //обновление данных о правонарушении
            session.update(accident);
            session.flush();
            session.clear();
            //удаление старого значения типа
            session.createNativeQuery("DELETE from accident_type WHERE id = " + oldAccidentTypeId).executeUpdate();
            session.flush();
            session.clear();
            //удаление старых значений выбранных статей
            session.createNativeQuery("DELETE from accident_rule WHERE accident_id ISNULL").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }
}
