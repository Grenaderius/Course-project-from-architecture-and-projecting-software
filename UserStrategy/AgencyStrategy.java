package UserStrategy;

import CommandExecutor.*;
import CommandExecutor.ComparisonsCommand.BaggageStatisticAndProportionCommand;
import CommandExecutor.ComparisonsCommand.ShopAndRestTouristsComparisonByPeriodCommand;
import CommandExecutor.ComparisonsCommand.ShopAndRestTouristsComparisonCommand;
import CommandExecutor.CountsCommand.CountOfTouristsByDateAndCategoryCommand;
import CommandExecutor.CountsCommand.CountOfTouristsByDateFinderCommand;
import CommandExecutor.CountsCommand.CountOfTouristsThatBookedExcursionsByDate;
import CommandExecutor.FinancesCommand.AgencyProfitabilityCommand;
import CommandExecutor.FinancesCommand.FinancesForPeriodCommand;
import CommandExecutor.FinancesCommand.GroupCategoryFinancesFinderCommand;
import CommandExecutor.FinancesCommand.GroupFinancesFinderCommand;
import CommandExecutor.LinesCommand.PlaneRaceByDateFinderCommand;
import CommandExecutor.LinesCommand.PopularExcursionsCommand;
import CommandExecutor.LinesCommand.SingleTouristInfoCommand;
import CommandExecutor.LinesCommand.WhetherPersonLivedInHotelByDateCommand;
import CommandExecutor.ListsCommands.*;

import java.util.Scanner;

public class AgencyStrategy implements UserStrategy{
    Scanner scanner = new Scanner(System.in);

    @Override
    //метод, що викликає патерн Command для передачі команд до нього, введених користувачем
    public void orderAction(String keyWord) {
        Command command;
        final String actionType ="Agency";
        String enteredCommand = "";

        //реалізація запитів користувача через патерн Command та switch-case
        while (!enteredCommand.equals("exit")) {
            System.out.println("\nУведіть ключове слово для вибору необхідної дії або введіть \"?\" для показу доступних ключових слів:");
            enteredCommand = scanner.nextLine();

            switch (enteredCommand) {
                case "createStorageFile":
                    command = new FileCreatorCommand("Storage");
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

                case "createListForCustoms":
                    command = new CustomsListsCreatorCommand();
                    command.execute();
                    break;

                case "createListForCustomsByCategory":
                    command = new CustomListByCategoryCommand();
                    command.execute();
                    break;
                case "createListForHotels":
                    command = new HotelsListsCreatorCommand();
                    command.execute();
                    break;

                case "createListForHotelsByCategory":
                    command = new HotelsListsCreatorByCategoryCommand();
                    command.execute();
                    break;

                case "getCountOfTouristsByPeriod":
                    command = new CountOfTouristsByDateFinderCommand();
                    command.execute();
                    break;

                case "getCountOfTouristsByPeriodAndCategory":
                    command = new CountOfTouristsByDateAndCategoryCommand();
                    command.execute();
                    break;

                case "getCountOfTouristsByExcursionAndDate":
                    command = new CountOfTouristsThatBookedExcursionsByDate();
                    command.execute();
                    break;

                case "getSingleTouristsInfo":
                    command = new SingleTouristInfoCommand();
                    command.execute();
                    break;

                case "getHotelsAndFlatsList":
                    command = new HotelsListAndBookedFlatsCommand();
                    command.execute();
                    break;

                case "checkWhetherPersonLivedInHotelByPeriod":
                    command = new WhetherPersonLivedInHotelByDateCommand();
                    command.execute();
                    break;

                case "getPopularExcursion":
                    command = new PopularExcursionsCommand();
                    command.execute();
                    break;

                case "addPlaneRace":
                    command = new AddPlaneCommand();
                    command.execute();
                    break;

                case "viewGroupFinanceReport":
                    command = new GroupFinancesFinderCommand();
                    command.execute();
                    break;

                case "viewGroupCategoryFinanceReport":
                    command = new GroupCategoryFinancesFinderCommand();
                    command.execute();
                    break;

                case "findPlaneRaceByDate":
                    command = new PlaneRaceByDateFinderCommand();
                    command.execute();
                    break;

                case "findRestToShopTouristsRelation":
                    command = new ShopAndRestTouristsComparisonCommand();
                    command.execute();
                    break;

                case "findRestToShopTouristsRelationByDate":
                    command = new ShopAndRestTouristsComparisonByPeriodCommand();
                    command.execute();
                    break;

                case "getIncomeAndCostForPeriod":
                    command = new FinancesForPeriodCommand();
                    command.execute();
                    break;

                case "getStatisticAndSpecificSharesForBaggage":
                    command = new BaggageStatisticAndProportionCommand();
                    command.execute();
                    break;

                case "viewAgencyProfitability":
                    command = new AgencyProfitabilityCommand();
                    command.execute();
                    break;

                case "getListFromRace":
                    command = new ListFromRaceWithAddingsCommand();
                    command.execute();
                    break;

                case "?":
                    System.out.println("""
                    Вам доступні такі команди:
                    "createStorageFile"                       - створення файлу для таблиці, де зберігатиметься інформація про наповнення складу
                    "deleteFile"                              - видалення файлу
                    "viewFile"                                - перегляд інформації з файлу так, як вона в ньому записана
                    "viewTable"                               - перегляд інформації із таблиці
                    "fillTable"                               - заповнення таблиці
                    "createListForCustoms"                    - створення та перегляд списку туристів для митниці
                    "createListForCustomsByCategory"          - створення та перегляд списку туристів для митниці по категоріях
                    "getCountOfTouristsByPeriodAndCategory"   - отримати кількість туристів, що побувала у країні за певний період і категорією
                    "getCountOfTouristsByExcursionAndDate"    - отримати кількість туристів за екскурсіями і датами
                    "createListForHotels"                     - створення списків на розселення у готелі
                    "createListForHotelsByCategory"           - створення списків на розселення у готелі по категоріях
                    "getCountOfTouristsByPeriod"              - отримати кількість туристів, що побувала у країні за певний період
                    "getSingleTouristsInfo"                   - отримати інформацію про конкретного туриста
                    "getHotelsAndFlatsList"                   - отримати список доступних готелів із кількістю зайнятих кімнат
                    "checkWhetherPersonLivedInHotelByPeriod"  - перевірити чи людина жила у конкретному готелі у певний період
                    "getPopularExcursion"                     - отримати найпопулярнішу екскурсію
                    "addPlaneRace"                            - додати рейс літака до таблиці із рейсами
                    "viewGroupFinanceReport"                  - отримати фінансовий звіт по певній групі туристів
                    "viewGroupCategoryFinanceReport"          - отримати фінансовий звіт по певній групі туристів по категорії
                    "findPlaneRaceByDate"                     - знайти рейс літака за датою
                    "findRestToShopTouristsRelation"          - знайти відношення відпочиваючих туристів до туристів шоп-турів
                    "findRestToShopTouristsRelationByDate"    - знайти відношення відпочиваючих туристів до туристів шоп-турів за певний період
                    "getIncomeAndCostForPeriod"               - отримати прибутки та витрати представництва за певний період
                    "getStatisticAndSpecificSharesForBaggage" - отримати статистику по видам відправленого вантажу та їх частку у загальному вантажопотоці
                    "viewAgencyProfitability"                 - переглянути інформацію про прибутковість представництва
                    "getListFromRace"                         - переглянути групу туристів, що була відправлена конкретним рейсом літака
                    "exit"                                    - вихід із облікового запису агентства
                    """);
                    break;

                case "exit":
                    command = new ExitCommand();
                    command.execute();
                    break;

                default:
                    System.err.println("Команда не відповідає жодній із можливих!");
                    break;
            }
        }
    }
}
