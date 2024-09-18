package Actions.AgencyActions.GetInfoAboutOneTourist;

import Actions.TablesPatterns.TablesFillerPatterns;

import java.util.Scanner;

public class GetSingleTouristsInfo {
    private final String list;
    private final TablesFillerPatterns tablesFillerPatterns = new TablesFillerPatterns();

    public GetSingleTouristsInfo(String list){
        this.list = list;
    }

    public void printTouristsInfo(){
        String groupPattern = tablesFillerPatterns.returnGroupPattern();
        String []infoType = ("№|" + groupPattern).split("\\|");
        String touristsLine = getTouristInformation();
        String []touristArr = touristsLine.split("\\|");

        if(touristArr.length == infoType.length){
            System.out.println("Інформація про туриста: ");

            for (int i = 0; i < touristArr.length; i++){
                System.out.println(infoType[i] + ": " + touristArr[i]);
            }

        }else{
            System.err.println("Інформація про даного туриста не відповідає заголовку таблиці, система виводить не відформатовану інформацію:\n" + touristsLine);
        }

    }


    public String getTouristInformation(){
        int touristsPlace = placeChooser() + 1;
        String []arrList = list.split("\n");

        while (touristsPlace>arrList.length) {
            if(touristsPlace<=arrList.length)break;
            touristsPlace = placeChooser() + 1;
        }

        return arrList[touristsPlace];
    }

    private int placeChooser(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            if(scanner.hasNextInt()) return scanner.nextInt(); else System.out.println("Уведіть число:");
        }
    }

}
