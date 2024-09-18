package CommandExecutor.FinancesCommand;

import Actions.AgencyActions.FinanceOperations.GroupAndCategoryFinancesCalculations;
import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import CommandExecutor.Command;

import java.text.DecimalFormat;


public class AgencyProfitabilityCommand implements Command {
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

    @Override
    public void execute() {
        returnFileForSum();
        printProfitability();
    }

    private void cashFinder(String fileName) {
        String list = informationGetter.getInfo(nameAndPathGetter.getFilePath(), fileName);
        GroupAndCategoryFinancesCalculations groupAndCategoryFinancesCalculations = new GroupAndCategoryFinancesCalculations(returnListWithoutType(list));
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

    private String returnListWithoutType(String startingList){
        String []startingListArr = startingList.split("\n");
        StringBuilder returningList = new StringBuilder();

        for (int i = 1; i < startingListArr.length; i++){
            returningList.append(startingListArr[i]).append("\n");
        }

        return returningList.toString();
    }

    private void returnFileForSum() {
        String list = informationGetter.getInfo(nameAndPathGetter.getFilePath(), chosenFile);
        String[] listArr = list.split("\n");

        for (int i = 2; i < listArr.length; i++) {
            String[] lineArr = listArr[i].split("\\|");
            cashFinder(lineArr[8].trim());
        }
    }

    public void printProfitability() {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        double income = sumForBaggage + sumForChildren + sumForExcursions + sumForTourists + sumForHotel;
        double cost = costForChildren + costForHotel + costForTourists + costForExcursions + planeCost;
        double profitability = income/cost;
        System.out.println("Доходи представництва -         " + decimalFormat.format(income) + "$\n"
                         + "Витрати представництва -        " + decimalFormat.format(cost) + "$\n"
                         + "Рентабельність представництва - " + decimalFormat.format(profitability) + "\n");
    }
}