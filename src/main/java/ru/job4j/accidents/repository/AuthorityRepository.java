package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Authority;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.07.2020
 */

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
