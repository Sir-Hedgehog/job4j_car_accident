package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Store.class);
        context.register(ConsoleInput.class);
        context.register(StartUI.class);
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ui.ask("Хотите сыграть в беспроигрышную лотерею?");
        ui.ask("Билет для участия стоит сущие копейки!");
        ui.add("Вращаем барабан!");
        ui.add("С Вас 100 баксов!");
        ui.print();
    }
}
