package ru.job4j.accidents.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 23.06.2020
 */

/*@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }*/

    /**
     * Метод добавляет новое происшествие в базу данных
     * @param accident - происшествие
     */

    /*public void createAccident(Accident accident) {
        String addAccident = "INSERT INTO accident (id, name, text, address) VALUES (?, ?, ?, ?)";
        jdbc.update(addAccident, accident.getId(), accident.getName(), accident.getText(), accident.getAddress());
        String addAccidentType = "INSERT INTO accident_type (accident_id, type_id, type_name) VALUES (?, ?, ?)";
        jdbc.update(addAccidentType, accident.getId(), accident.getType().getId(), accident.getType().getName());
        String addAccidentRule = "INSERT INTO accident_rule (accident_id, rule_id, rule_name) VALUES (?, ?, ?)";
        for (Rule rule : accident.getRules()) {
            jdbc.update(addAccidentRule, accident.getId(), rule.getId(), rule.getName());
        }
    }*/

    /**
     * Метод формирует список существующих данных по происшествиям
     * @return - список данных
     */

    /*public List<Accident> getAccidents() {
        String allAccidents = "SELECT "
                                    + "accident.id, "
                                    + "accident.name, "
                                    + "accident_type.type_id, "
                                    + "accident_type.type_name, "
                                    + "accident_rule.rule_id, "
                                    + "accident_rule.rule_name, "
                                    + "accident.text, "
                                    + "accident.address "
                            + "FROM "
                                    + "accident, accident_type, accident_rule "
                            + "WHERE "
                                    + "accident.id = accident_type.accident_id "
                            + "AND "
                                    + "accident.id = accident_rule.accident_id;";
        return jdbc.query(allAccidents, rs -> {
            Set<Rule> rules = new HashSet<>();
            Map<Integer, Accident> mapOfAccidents = new HashMap<>();
            int index = 1;
            while (rs.next()) {
                if (index != rs.getInt("id")) {
                    index = rs.getInt("id");
                    rules = new HashSet<>();
                }
                //Получение данных о типе происшествия
                AccidentType type = new AccidentType();
                type.setId(rs.getInt("type_id"));
                type.setName(rs.getString("type_name"));
                //Получение данных о статьях
                Rule rule = new Rule();
                rule.setId(rs.getInt("rule_id"));
                rule.setName(rs.getString("rule_name"));
                rules.add(rule);
                //Получение данных о происшествии
                Accident accident = new Accident();
                accident.setId(rs.getInt("id"));
                accident.setName(rs.getString("name"));
                accident.setType(type);
                accident.setText(rs.getString("text"));
                accident.setAddress(rs.getString("address"));
                accident.setRules(rules);
                //формирование карты всех происшествий
                mapOfAccidents.put(rs.getInt("id"), accident);
            }
            return new ArrayList<>(mapOfAccidents.values());
        });
    }*/

    /**
     * Метод обновляет данные о происшествии
     * @param accident - происшествие
     */

    /*public void updateAccident(Accident accident) {
        String updateAccident = "UPDATE accident SET name = ?, text = ?, address = ? WHERE id = ?";
        jdbc.update(updateAccident, accident.getName(), accident.getText(), accident.getAddress(), accident.getId());
        String updateAccidentType = "UPDATE accident_type SET type_id = ?, type_name = ? WHERE accident_id = ?";
        jdbc.update(updateAccidentType, accident.getType().getId(), accident.getType().getName(), accident.getId());
        String deleteOldAccidentRule = "DELETE from accident_rule where accident_id = ?";
        jdbc.update(deleteOldAccidentRule, accident.getId());
        String updateAccidentRule = "INSERT INTO accident_rule (accident_id, rule_id, rule_name) VALUES (?, ?, ?)";
        for (Rule rule : accident.getRules()) {
            jdbc.update(updateAccidentRule, accident.getId(), rule.getId(), rule.getName());
        }
    }
}*/
