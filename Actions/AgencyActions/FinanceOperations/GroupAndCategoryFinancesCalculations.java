package Actions.AgencyActions.FinanceOperations;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class GroupAndCategoryFinancesCalculations {
    private final String groupList;
    private long daysBtw = 0;

    //змінні сум доходу
    private long sumForTourists = 0;
    private long sumForChildren = 0;
    private double sumForBaggage = 0;
    private long sumForHotel = 0;
    private double sumForExcursions = 0;
    private double totalIncomeSum = 0;

    //змінні сум витрат
    private long costForTourists = 0;
    private long costForChildren = 0;
    private double costForExcursions = 0;
    private long costForHotel = 0;
    private double totalCosts = 0;

    public GroupAndCategoryFinancesCalculations(String groupList){
        this.groupList = groupList;
    }

    public void printReportForGroup(){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        getTotalSum();
        getTotalCosts();

        System.out.println("Фінансовий звіт по цій групі:\n"
                + "Прибуток за туристів:                  " + sumForTourists + "$\n"
                + "Прибуток за дітей туристів:            " + sumForChildren + "$\n"
                + "Прибуток за вантаж туристів:           " + decimalFormat.format(sumForBaggage) + "$\n"
                + "Прибуток за готелі:                    " + sumForHotel + "$\n"
                + "Прибуток за екскурсії:                 " + decimalFormat.format(sumForExcursions) + "$\n"

                + "\nВитрати на туристів:                   " + costForTourists + "$\n"
                + "Витрати на дітей туристів:             " + costForChildren + "$\n"
                + "Витрати на екскурсії:                  " + decimalFormat.format(costForExcursions) + "$\n"
                + "Витрати на готелі:                     " + costForHotel + "$\n"

                + "\nЗагальний прибуток становить:          " + decimalFormat.format(totalIncomeSum) + "$\n"
                + "Загальні витрати становлять:           " + decimalFormat.format(totalCosts) + "$\n"

                + "\nЗагальні доходи за групу становлять:   " + decimalFormat.format((totalIncomeSum-totalCosts)) + "$");
    }

    public void execute(){
        calculatePrices();
    }

    public long getSumForTourists() {
        return sumForTourists;
    }

    public long getSumForChildren() {
        return sumForChildren;
    }

    public double getSumForBaggage() {
        return sumForBaggage;
    }

    public long getSumForHotel() {
        return sumForHotel;
    }

    public double getSumForExcursions() {
        return sumForExcursions;
    }

    public long getCostForTourists() {
        return costForTourists;
    }

    public long getCostForChildren() {
        return costForChildren;
    }

    public double getCostForExcursions() {
        return costForExcursions;
    }

    public long getCostForHotel() {
        return costForHotel;
    }

    public double getCostForPlane() {
        return (double) costForChildren*costForTourists/100;
    }

    public double getTotalSumWithPlane(){
        findTotalSum();
        return totalIncomeSum + (double)costForChildren*costForTourists/100;
    }

    public double getTotalSum(){
        findTotalSum();
        return totalIncomeSum;
    }

    public double getTotalCosts(){
        findTotalCost();
        return totalCosts;
    }

    private void findTotalCost(){
        totalCosts = costForTourists + costForChildren + costForHotel + costForExcursions;
    }

    private void findTotalSum(){
        calculatePrices();
        totalIncomeSum = sumForTourists + sumForChildren + sumForBaggage + sumForHotel + sumForExcursions;
    }

    private void calculatePrices(){
        String []listArr = groupList.split("\n");
        int flag = 0;

        for(String line : listArr){
            String []lineArr = line.split("\\|");

            if(flag>0){
                if(lineArr.length>=11){
                    daysBtw = getHotelsSumForSingleTourist(lineArr[11].replace(" ", ""), lineArr[12].replace(" ", ""));
                    incomesFinder(lineArr);
                    costsFinder(lineArr);
                    continue;
                }
            }

            flag++;
        }
    }

    private void costsFinder(String []lineArr){
        costForTourists += 150;
        costForChildren += Integer.parseInt(lineArr[6].replace(" ", "")) * 50;
        costForHotel += daysBtw * 20;
        costForExcursions += daysBtw * 2.2;

    }

    private void incomesFinder(String []lineArr){
        sumForTourists += 450;
        sumForChildren += Integer.parseInt(lineArr[6].replace(" ", "")) * 350;
        sumForBaggage += Double.parseDouble(lineArr[10].replace(" ", "")) * 50;
        sumForHotel += daysBtw * 100;
        sumForExcursions += daysBtw * 8.2;
    }

    private long getHotelsSumForSingleTourist(String firstDate, String secondDate){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(firstDate, dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(secondDate, dateTimeFormatter);

        return Math.abs(ChronoUnit.DAYS.between(startDate, endDate));
    }
}