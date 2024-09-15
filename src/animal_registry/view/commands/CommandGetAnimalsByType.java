package animal_registry.view.commands;

import animal_registry.view.ConsoleUI;

public class CommandGetAnimalsByType extends Commands{

    public CommandGetAnimalsByType(ConsoleUI consoleUI) {
        super("Получить информации о животных по видам.", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().getAnimalsByType();
    }
}
