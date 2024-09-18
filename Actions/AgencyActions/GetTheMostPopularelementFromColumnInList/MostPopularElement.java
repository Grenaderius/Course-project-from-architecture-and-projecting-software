package Actions.AgencyActions.GetTheMostPopularelementFromColumnInList;

import Actions.AgencyActions.ColumnFinder.ColumnFinder;

import java.util.HashMap;
import java.util.Map;

public class MostPopularElement {
    private final String column;
    private final String list;
    private  final String columnTitle;

    public MostPopularElement(String column, String list, String columnTitle){
        this.column = column;
        this.list = list;
        this.columnTitle = columnTitle;
    }

    public String getPopular(){
        return findPopularEl(getMapElements());
    }

    public String findPopularEl(Map<String, Integer> elementsMap){
        String combination = "";
        int number = -1;
        String element = "";
        String []columnArr = column.split("\n");

        for (String columnElement : columnArr){
            if(number<elementsMap.get(columnElement)){
                element = columnElement;
                number = elementsMap.get(columnElement);
            }
        }

        combination = "\"" + element + "\" кількість виборів - " + number;
        return combination;
    }

    public Map<String, Integer> getMapElements(){
        String element = "";
        String []columnArr = column.split("\n");
        String []listArr = list.split("\n");
        int columnNumber = findColNumb(listArr[1]);
        Map<String, Integer> mapOfElAndNumb = new HashMap<>();
        int counter = 0;

        for(String line : listArr){
            if(counter>1){
                String []lineArr = line.split("\\|");
                int counterForNumber = 0;
                String colElement = "";

                for (String columnElement : columnArr){
                    if(lineArr[columnNumber].replace(" ", "").equals(columnElement.replace(" ", ""))){
                        counterForNumber++;
                        colElement = columnElement;
                    }
                }
                mapOfElAndNumb.put(colElement, counterForNumber);

                continue;
            }

            counter++;
        }

        return mapOfElAndNumb;
    }

    private int findColNumb(String line){
        ColumnFinder columnFinder = new ColumnFinder(columnTitle);
        String []lineArr = line.split("\\|");
        return columnFinder.findNum(lineArr);
    }
}
