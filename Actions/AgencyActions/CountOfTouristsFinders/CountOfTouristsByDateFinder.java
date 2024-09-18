package Actions.AgencyActions.CountOfTouristsFinders;

import Actions.AgencyActions.DateActions.DateComparison.DateComparison;
import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;

public class CountOfTouristsByDateFinder {
    private final NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    private final String filePath = nameAndPathGetter.getFilePath();
    private final String fileName;
    private final String userDate1;
    private final String userDate2;
    private final DateComparison dateComparison = new DateComparison();

    public CountOfTouristsByDateFinder(String fileName, String userDate1, String userDate2){
        this.fileName = fileName;
        this.userDate1 = userDate1;
        this.userDate2 = userDate2;
    }

    public void printCount(){
        System.out.println("Кількість туристів, що побувала у країні за період від " + userDate1 + " і до " + userDate2 + " - " + getCountOfTourists(listByDataCreator()));
    }

    public int getCountOfTourists(String list){
        String []listArr = list.split("\n");
        System.out.println(list);
        return listArr.length;
    }

    public String listByDataCreator(){
        StringBuilder list = new StringBuilder();
        String []linesArr = divideInfo();
        short counter = 0;

        for(String line : linesArr){
            if(counter>1){
                String []columnsArr = line.split("\\|");
                if(dateComparison.compareDataAfter(columnsArr[11], userDate1) && dateComparison.compareDataBefore(columnsArr[12], userDate2)){
                    list.append(line).append("\n");
                }
                continue;
            }
            counter++;
        }

        return list.toString();
    }

    private String []divideInfo(){
        return infoGetter().split("\n");
    }

    private String infoGetter(){
        InformationGetter informationGetter = new InformationGetter();
        return informationGetter.getInfo( filePath, fileName);
    }
}
