package animal_registry.view.commands;

import animal_registry.view.ConsoleUI;

public class CommandLoadRegistry extends Commands{

    public CommandLoadRegistry(ConsoleUI consoleUI) {
        super("Загрузить реестр животных.", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().loadRegistry();
    }
}
