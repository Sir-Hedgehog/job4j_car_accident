package ru.job4j.accidents.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentData;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 11.0
 * @since 02.07.2020
 */

@Component
public class AccidentService {
    private final AccidentData data;
    private static final Logger LOG = LoggerFactory.getLogger(AccidentService.class);

    @Autowired
    public AccidentService(@Qualifier("accidentData") AccidentData data) {
        this.data = data;
    }

    /**
     * Метод передает контроллеру данные о зарегистрированных правонарушениях
     * @return - список правонарушений
     */

    public Map<Integer, Accident> getValidateAccidents() {
        return data.findAll().stream().collect(Collectors.toMap(Accident::getId, accident -> accident));
    }

    /**
     * Метод иммитирует работу бизнес-логики в контексте создания нового объявления о правонарушении
     * @param accident - новое правонарушение
     */

    public void createValidateAccident(Accident accident, String[] ruleIds) {
        this.setNameOfType(accident);
        this.setRules(accident, ruleIds);
        data.save(accident);
    }

    /**
     * Метод иммитирует работу бизнес-логики в контексте обновления уже существующего заявления о правонарушении
     * @param accident - обновленные данные для существующего правонарушения
     */

    public void updateValidateAccident(Accident accident, String[] ruleIds) {
        this.setNameOfType(accident);
        this.setRules(accident, ruleIds);
        //Получение уникального идентификатора старого значения типа
        Accident oldAccident = data.findById(accident.getId()).get();
        int oldAccidentTypeId = oldAccident.getType().getKey();
        //Обновление данных о правонарушении
        data.save(accident);
        //Удаление старого значения типа
        data.deleteNotActiveType(oldAccidentTypeId);
        //Удаление старых значений выбранных статей
        data.deleteAllNotActiveRules();
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
