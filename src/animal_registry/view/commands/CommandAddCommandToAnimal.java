package animal_registry.view.commands;

import animal_registry.view.ConsoleUI;

public class CommandAddCommandToAnimal extends Commands{

    public CommandAddCommandToAnimal(ConsoleUI consoleUI) {
        super("Добавить команду животному по ID.", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().addCommandToAnimal();
    }
}
