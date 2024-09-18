package CommandExecutor.FinancesCommand;

import Actions.AgencyActions.DateActions.ListByDateFinder.ListByDateForGroupFinder;
import Actions.AgencyActions.FinanceOperations.GroupAndCategoryFinancesCalculations;
import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import CommandExecutor.Command;

import java.text.DecimalFormat;
import java.util.Scanner;

public class FinancesForPeriodCommand implements Command {
    NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    String chosenFile = "PlanesFile.txt";
    InformationGetter informationGetter = new InformationGetter();

    //доходи
    private long sumForTourists = 0;
    private long sumForChildren = 0;
    private double sumForBaggage = 0;
    private long sumForHotel = 0;
    private double sumForExcursions = 0;

    //змінні сум витрат
    private long costForTourists = 0;
    private long costForChildren = 0;
    private double costForExcursions = 0;
    private long costForHotel = 0;
    private double planeCost = 0;

    private final String firstDate;
    private final String secondDate;

    public FinancesForPeriodCommand(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Будь ласка, уведіть початкову дату(приклад 12.04.2023):");
        firstDate = scanner.nextLine();

        System.out.println("Будь ласка, уведіть кінцеву дату(приклад 30.05.2023):");
        secondDate = scanner.nextLine();
    }

    @Override
    public void execute() {
        returnFileForSum();
        printReportForPeriod();
    }

    private void divideListByData(String list){
        ListByDateForGroupFinder listByDateForGroupFinder = new ListByDateForGroupFinder(informationGetter.getInfo(nameAndPathGetter.getFilePath(), list), firstDate, secondDate);
        cashFinder(listByDateForGroupFinder.getListByDateDivided());
    }

    private void cashFinder(String list){
        GroupAndCategoryFinancesCalculations groupAndCategoryFinancesCalculations = new GroupAndCategoryFinancesCalculations(list);
        groupAndCategoryFinancesCalculations.execute();

        sumForTourists += groupAndCategoryFinancesCalculations.getSumForTourists();
        sumForChildren += groupAndCategoryFinancesCalculations.getSumForChildren();
        sumForBaggage += groupAndCategoryFinancesCalculations.getSumForBaggage();
        sumForHotel += groupAndCategoryFinancesCalculations.getSumForHotel();
        sumForExcursions += groupAndCategoryFinancesCalculations.getSumForExcursions();

        costForTourists += groupAndCategoryFinancesCalculations.getCostForTourists();
        costForChildren += groupAndCategoryFinancesCalculations.getCostForChildren();
        costForExcursions += groupAndCategoryFinancesCalculations.getCostForExcursions();
        costForHotel += groupAndCategoryFinancesCalculations.getCostForHotel();
        planeCost += groupAndCategoryFinancesCalculations.getCostForPlane();
    }

    private void returnFileForSum(){
        String list = informationGetter.getInfo(nameAndPathGetter.getFilePath(), chosenFile);
        String []listArr = list.split("\n");

        for (int i = 2; i < listArr.length; i++) {
            String []lineArr = listArr[i].split("\\|");
            divideListByData(lineArr[8].trim());
        }
    }

    public void printReportForPeriod(){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        System.out.println("Доходи та витрати по цій групі:\n"
                + "Прибуток за туристів:                  " + sumForTourists + "$\n"
                + "Прибуток за дітей туристів:            " + sumForChildren + "$\n"
                + "Прибуток за вантаж туристів:           " + decimalFormat.format(sumForBaggage) + "$\n"
                + "Прибуток за готелі:                    " + sumForHotel + "$\n"
                + "Прибуток за екскурсії:                 " + decimalFormat.format(sumForExcursions) + "$\n"

                + "\nВитрати на туристів:                   " + costForTourists + "$\n"
                + "Витрати на дітей туристів:             " + costForChildren + "$\n"
                + "Витрати на екскурсії:                  " + decimalFormat.format(costForExcursions) + "$\n"
                + "Витрати на готелі:                     " + costForHotel + "$\n"
                + "Витрати на обслуговування літкака:     " + decimalFormat.format(planeCost)+ "$");
    }

}
