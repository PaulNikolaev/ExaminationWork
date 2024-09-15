package animal_registry.view.commands;

import animal_registry.view.ConsoleUI;

public class CommandAnimalRegistrySize extends Commands {

    public CommandAnimalRegistrySize(ConsoleUI consoleUI) {
        super("Получить информацию о количестве животных в питомнике.", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().animalRegistrySize();
    }
}
