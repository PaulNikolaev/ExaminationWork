package animal_registry.view.commands;

import animal_registry.view.ConsoleUI;

public class CommandAddAnimal extends Commands{
    public CommandAddAnimal(ConsoleUI consoleUI) {
        super("Добавить животное в реестр.", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().addAnimal();
    }
}
