package Actions.AgencyActions.BaggageStatistic;

import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BaggageStatisticFinder {
    private InformationGetter informationGetter = new InformationGetter();
    private NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    private final String fileName;
    private StringBuilder statistic = new StringBuilder();

    public BaggageStatisticFinder(String fileName){
        this.fileName = fileName;
    }

    public void getStatisticAndSpecificShares(){
        specificShareCycleMaker();
        System.out.println("Статистика за видами відправленого вантажу:");
        printTable(statistic.toString());
    }

    private void printTable(String table){
        String []tableArr = table.split("\n");
        Integer[] arrayOfCharactersCounters = getColumnWidth(tableArr);

        for (String line : tableArr){
                    String[] lineArr = line.split("\\|");

                    for (int i = 1; i < arrayOfCharactersCounters.length; i++) {
                        System.out.print(lineArr[i].trim());

                        for (int j = 1; j < arrayOfCharactersCounters[i] - lineArr[i].trim().length() + 1; j++) {
                            System.out.print(" ");
                        }
                        System.out.print("|");
                    }
                    System.out.println();

                }
    }

    private Integer[] getColumnWidth(String []tableArr){
        Integer []arrayOfCharactersCounters = new Integer[0];
        int flag = 0;

                    if(flag!=1){
                        flag = 1;
                        arrayOfCharactersCounters = new Integer[tableArr.length];
                        Arrays.fill(arrayOfCharactersCounters, 0);
                    }

                    for (int i = 1; i < arrayOfCharactersCounters.length; i++) {
                        if(arrayOfCharactersCounters[i]<tableArr[i].trim().length()){
                            arrayOfCharactersCounters[i] = tableArr[i].length();
                        }

                    }

                    return arrayOfCharactersCounters;
    }



    private void specificShareCycleMaker(){
        String []linesArr = (informationGetter.getInfo(nameAndPathGetter.getFilePath(), fileName)).split("\n");
        Set<String> typesSet = new HashSet<>();
        int totalCount = 0;
        double totalWeight = 0;

        String []elementsArrFirStat = linesArr[1].split("\\|");
        statistic.append("|").append(elementsArrFirStat[1]).append("|").append(elementsArrFirStat[2]).append("|").append(elementsArrFirStat[5]).append("\n");

        for (int i = 2; i < linesArr.length; i++) {
            String []elementsArr = linesArr[i].split("\\|");

            typesSet.add(elementsArr[5]);
            totalWeight += Double.parseDouble(elementsArr[2]);
            totalCount += Integer.parseInt(elementsArr[1]);
        }

        for(String type : typesSet){
            specificShareMaker(type, totalCount, totalWeight);
        }

        statistic.append("|").append(totalCount).append("|").append(totalWeight).append("|").append("Загально").append("\n");
    }

    private void specificShareMaker(String type, int totalCount, double totalWeight){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String []linesArr = (informationGetter.getInfo(nameAndPathGetter.getFilePath(), fileName)).split("\n");
        int placesCount = 0;
        double weight = 0;

        for (int i = 1; i < linesArr.length; i++) {
            String []elementsArr = linesArr[i].split("\\|");

            if(elementsArr[5].equals(type)){
                placesCount = Integer.parseInt(elementsArr[1].trim());
                weight = Double.parseDouble(elementsArr[2].trim());
            }
        }

        double countSpecificShare = (double) placesCount/totalCount;
        double weightSpecificShare = weight/totalWeight;
        double specificShare = (countSpecificShare + weightSpecificShare)/2;

        statistic.append("|").append(decimalFormat.format(placesCount)).append("|").append(decimalFormat.format(weight)).append("|").append(type).append("\n");


        System.out.println("Частака " + type + " у загальному вантажопотоці становить - " + decimalFormat.format(specificShare));
    }

}
