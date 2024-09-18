package Actions.AgencyActions;

import java.util.HashSet;

public class CountOfRepeadsFinder {
    private final String list;
    private final int columnNum;

    public CountOfRepeadsFinder(int columnNum, String list){
        this.list = list;
        this.columnNum = columnNum;
    }

    public String getColumnWithNumberOfReplacedElements(){
        StringBuilder columnWithRepeads = new StringBuilder();
        String []arrList = list.split("\n");
        int counter = 0;
        HashSet<String> hashSet = new HashSet<>();

        for (String line : arrList){
            String []lineArr = line.split("\\|");

            if (counter>1){
               // columnWithRepeads.append(lineArr[columnNum]).append(": ").append(findRepeadsForElement(lineArr[columnNum])).append("\n");
                hashSet.add(lineArr[columnNum] + ": " + findRepeadsForElement(lineArr[columnNum]));
            }

            counter++;
        }

        for(String line : hashSet){
            columnWithRepeads.append(line).append("\n");
        }

        return columnWithRepeads.toString();
    }


    //знаходить кількість повторів для
    public int findRepeadsForElement(String element){
        int count = 0;
        String []arrList = list.split("\n");
        int counter = 0;

        for (String line : arrList){
            if(counter>1){
                String []lineArr = line.split("\\|");
                if(lineArr[columnNum].replace(" ", "").equals(element.replace(" ", ""))){
                    count++;
                }
            }
            counter++;
        }

        return count;
    }

}
