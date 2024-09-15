package animal_registry.view;

import animal_registry.model.animal.PetType;
import animal_registry.presenter.Presenter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleUI implements View {
    private Scanner scanner;
    private boolean work;
    private Presenter presenter;
    private MainMenu menu;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        work = true;
        presenter = new Presenter(this);
        menu = new MainMenu(this);
    }

    @Override
    public void start() {
        System.out.println("Программа для работы с реестром питомника.");
        while (work) {
            System.out.println(menu.menu());
            menu.execute(getCheckMenuSize());
        }
    }

    // Завершение работы без сохранения
    public void finish() {
        work = false;
        scanner.close();
        System.out.println("Досвидания!");
    }

    // Завершение работы и сохранение изменений
    public void finishAndSave() {
        work = false;
        scanner.close();
        presenter.saveRegistry();
        System.out.println("Досвидания!");
    }

    // Сортировка реестра по имени
    public void sortByName() {
        presenter.sortByName();
    }

    // Получение информации о животных по видам
    public void getAnimalsByType() {
        PetType petType = null;
        while (petType == null) {
            System.out.print("Введите вид животного (собака/кот/хомяк): ");
            String petTypeInput = scanner.nextLine().trim().toLowerCase();
            switch (petTypeInput) {
                case "собака":
                    petType = PetType.Dog;
                    break;
                case "кот":
                    petType = PetType.Cat;
                    break;
                case "хомяк":
                    petType = PetType.Hamster;
                    break;
                default:
                    System.out.println("Ошибка: некорректный ввод. Введите собака, кот или хомяк.");
            }
        }

        String result = presenter.getAnimalsByType(petType);
        System.out.println(result);
    }

    // Получение информации о всех животных в реестре
    public void getAnimalListInfo() {
        presenter.getAnimalListInfo();
    }

    //Добавление команды животному по ID
    public void addCommandToAnimal() {
        System.out.print("Введите ID животного (или 'нет' для отмены): ");
        String idStr = scanner.nextLine().trim().toLowerCase();

        if (idStr.equals("нет")) {
            System.out.println("Операция отменена.");
            return;
        }

        try {
            long id = Long.parseLong(idStr);
            System.out.print("Введите команду для животного: ");
            String command = scanner.nextLine().trim();

            if (command.isEmpty()) {
                System.out.println("Ошибка: команда не может быть пустой.");
                return;
            }

            presenter.addCommandToAnimal(id, command);
            System.out.println("Команда успешно добавлена.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введен некорректный ID.");
        }
    }

    // Удаление животного из реестра по ID
    public void removeAnimalById() {
        System.out.print("Введите ID животного, которое нужно удалить (или напишите 'нет' для отмены): ");
        String idStr = scanner.nextLine().trim().toLowerCase();

        if ("нет".equals(idStr)) {
            System.out.println("Операция отменена.");
            return;
        }

        try {
            long id = Long.parseLong(idStr);
            presenter.removeAnimalById(id);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введен некорректный ID.");
        }
    }

    // Получить информацию о количестве животных в питомнике
    public void animalRegistrySize() {
        int registrySize = presenter.animalRegistrySize();
        System.out.printf("В питомнике сейчас находится %d животных.%n", registrySize);
    }

    // Добавление животного в реестр
    public void addAnimal() {
        System.out.println("Укажите кличку животного");
        String name = scanner.nextLine();
        PetType petType = getPetType();
        LocalDate birthdate = getLocalDate();

        try {
            presenter.addAnimal(name, petType, birthdate);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Загрузка реестра
    public void loadRegistry() {
        presenter.loadRegistry();
    }

    private PetType getPetType() {
        while (true) {
            System.out.println("Укажите вид животного (собака/кот/хомяк): ");
            String genderStr = scanner.nextLine();
            if (genderStr.equals("собака")) {
                return PetType.Dog;
            } else if (genderStr.equals("кот")) {
                return PetType.Cat;
            }  else if (genderStr.equals("хомяк")) {
                return PetType.Hamster;
            } else {
                System.out.println("Вид введен неправильно.");
            }
        }
    }

    private LocalDate getLocalDate() {
        while (true) {
            System.out.println("Введите дату рождения (dd.MM.yyyy): ");
            String birthDateStr = scanner.nextLine();
            if (isValidDate(birthDateStr)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                return LocalDate.parse(birthDateStr, formatter);
            } else {
                System.out.println("Дата введена неправильно.");
            }
        }
    }

    private boolean isValidDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private int getCheckMenuSize() {
        while (true) {
            String choiceStr = scanner.nextLine();
            int choice = Integer.parseInt(choiceStr);
            if (choice > 0 && choice <= menu.menuSize() ) {
                return choice;
            } else {
                System.out.println("Такого пункта меню нет");
                System.out.println(menu.menu());
            }
        }
    }

    @Override
    public void printAnswer(String answer) {
        System.out.println(answer);
    }
}
