package UserStrategy;

import CommandExecutor.*;
import java.util.Scanner;

public class FirmStrategy implements UserStrategy{
    Scanner scanner = new Scanner(System.in);

    @Override
    //метод, що викликає патерн Command для передачі команд до нього, введених користувачем
    public void orderAction(String keyWord) {
        Command command;
        final String actionType ="Firm";
        String enteredCommand = "";

        //реалізація запитів користувача через патерн Command та switch-case
        while (!enteredCommand.equals("exit")) {
            System.out.println("\nУведіть ключове слово для вибору необхідної дії або введіть \"?\" для показу доступних команд:");
            enteredCommand = scanner.nextLine();

            switch (enteredCommand) {
                case "createFile":
                    command = new FileCreatorCommand(actionType);
                    command.execute();
                    break;

                case "viewTable":
                    command = new TablesReaderCommand(actionType);
                    command.execute();
                    break;

                case "viewFile":
                    command = new FileReaderCommand(actionType);
                    command.execute();
                    break;

                case "fillTable":
                    command = new TablesFillerCommand(actionType);
                    command.execute();
                    break;

                case "deleteFile":
                    command = new FileDeleterCommand(actionType);
                    command.execute();
                    break;

                case "sendFile":
                    command = new FileSenderCommand(actionType);
                    command.execute();
                    break;

                case "exit":
                    command = new ExitCommand();
                    command.execute();
                    break;

                case "?":
                    System.out.println("""
                    Вам доступні такі команди:
                    "createFile" - створення файлу
                    "deleteFile" - видалення файлу
                    "sendFile"   - надсилання файлу до агентства
                    "viewFile"   - перегляд інформації з файлу так, як вона в ньому записана
                    "viewTable"  - перегляд інформації із таблиці
                    "fillTable"  - заповнення таблиці
                    "exit"       - вихід із облікового запису фірми
                    """);
                    break;

                default:
                    System.err.println("Команда не відповідає жодній із можливих!");
                    break;
            }
        }
    }
}
