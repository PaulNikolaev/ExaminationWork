package animal_registry.view.commands;

import animal_registry.view.ConsoleUI;

public class CommandFinish extends Commands{

    public CommandFinish(ConsoleUI consoleUI) {
        super("Завершение работы без сохранения.", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().finish();
    }
}
