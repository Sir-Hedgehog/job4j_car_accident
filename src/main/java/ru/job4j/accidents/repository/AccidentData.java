package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.model.Accident;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 02.07.2020
 */

public interface AccidentData extends CrudRepository<Accident, Integer> {

    /**
     * Метод задает конкретную коллекцию, в которой будут храниться правонарушения
     * @return - коллекция
     */
    List<Accident> findAll();

    /**
     * Метод удаляет старое значение типа из базы данных
     * @param oldAccidentTypeId - идентификатор старого значения типа
     */

    @Modifying
    @Transactional
    @Query(value = "DELETE from accident_type WHERE id = :typeId", nativeQuery = true)
    void deleteNotActiveType(@Param("typeId") int oldAccidentTypeId);

    /**
     * Метод удаляет старые значения выбранных статей из базы данных
     */

    @Modifying
    @Transactional
    @Query(value = "DELETE from accident_rule WHERE accident_id IS NULL", nativeQuery = true)
    void deleteAllNotActiveRules();
}
