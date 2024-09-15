package animal_registry.view.commands;

import animal_registry.view.ConsoleUI;

public class CommandGetAnimalListInfo extends Commands{

    public CommandGetAnimalListInfo(ConsoleUI consoleUI) {
        super("Получить информации о всех животных в реестре.", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().getAnimalListInfo();
    }
}
