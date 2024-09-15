package animal_registry.view.commands;

import animal_registry.view.ConsoleUI;

public class CommandSortByName extends Commands{

    public CommandSortByName(ConsoleUI consoleUI) {
        super("Отсортировать животных по имени.", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().sortByName();
    }
}
