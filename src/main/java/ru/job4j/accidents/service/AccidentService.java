package ru.job4j.accidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.mem.AccidentMem;
import ru.job4j.accidents.model.Rule;
import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 8.0
 * @since 19.06.2020
 */

@Component
public class AccidentService {
    private static int counter = 0;
    private final AccidentMem mem;

    @Autowired
    public AccidentService(AccidentMem mem) {
        this.mem = mem;
    }

    /**
     * Метод передает контроллеру данные о зарегистрированных правонарушениях
     * @return - успешность операции
     */

    public Map<Integer, Accident> getValidateAccidents() {
        return mem.getAccidents();
    }

    /**
     * Метод иммитирует работу бизнес-логики в контексте создания нового объявления о правонарушении
     * @param accident - новое правонарушение
     */

    public void createValidateAccident(Accident accident, String[] ruleIds) {
        accident.setId(++counter);
        this.setNameOfType(accident);
        this.setRules(accident, ruleIds);
        mem.createAccident(accident);
    }

    /**
     * Метод иммитирует работу бизнес-логики в контексте обновления уже существующего заявления о правонарушении
     * @param accident - обновленные данные для существующего правонарушения
     */

    public void updateValidateAccident(Accident accident, String[] ruleIds) {
        this.setNameOfType(accident);
        this.setRules(accident, ruleIds);
        mem.updateAccident(accident);
    }

    /**
     * Метод устанавливает имена для идентификаторов типа правонарушения (на сервер)
     * @param accident - правонарушение
     */

    private void setNameOfType(Accident accident) {
        if (accident.getType().getId() == 1) {
            accident.getType().setName("Две машины");
        } else if (accident.getType().getId() == 2) {
            accident.getType().setName("Машина и человек");
        } else if (accident.getType().getId() == 3) {
            accident.getType().setName("Машина и иной транспорт");
        } else if (accident.getType().getId() == 4) {
            accident.getType().setName("Другое");
        }
    }

    /**
     * Метод устанавливает имена для идентификаторов статей (на сервер)
     * @param accident - правонарушение
     * @param ruleIds - массив идентификаторов выбранных статей
     */

    private void setRules(Accident accident, String[] ruleIds) {
        Set<Rule> rules = new HashSet<>();
        for (String ruleId : ruleIds) {
            int current = Integer.parseInt(ruleId);
            Rule rule = new Rule();
            rule.setId(current);
            rules.add(rule);
        }
        for (Rule rule : rules) {
            if (rule.getId() == 1) {
                rule.setName("Статья №1 КоАП РФ");
            } else if (rule.getId() == 2) {
                rule.setName("Статья №2 КоАП РФ");
            } else if (rule.getId() == 3) {
                rule.setName("Статья №3 КоАП РФ");
            } else if (rule.getId() == 4) {
                rule.setName("Статья №4 КоАП РФ");
            } else if (rule.getId() == 5) {
                rule.setName("Статья №5 КоАП РФ");
            }
        }
        accident.setRules(rules);
    }
}
