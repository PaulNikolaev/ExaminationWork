package animal_registry.view.commands;

import animal_registry.view.ConsoleUI;

public class CommandFinishAndSave extends Commands{

    public CommandFinishAndSave(ConsoleUI consoleUI) {
        super("Завершить работу и сохраненить изменения.", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().finishAndSave();
    }
}

