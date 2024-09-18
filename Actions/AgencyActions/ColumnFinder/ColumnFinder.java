package Actions.AgencyActions.ColumnFinder;

import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;

public class ColumnFinder {
    private String fileName;
    private final String columnName;
    private final NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();

    public ColumnFinder(String columnName){
        this.columnName = columnName;
    }

    public ColumnFinder(String fileName, String columnName){
        this.fileName = fileName;
        this.columnName = columnName;
    }

    public String getList(){
        return infoGetter();
    }

    public String getColumn(){
        StringBuilder column = new StringBuilder();
        String []listArr = infoGetter().split("\n");
        int counter = 0;
        int columnNum = -1;

        for(String line : listArr){
            String []lineArr = line.split("\\|");

            if(columnNum > -1){
                column.append(lineArr[columnNum]).append("\n");
            }

            //знаходимо номер колонки
            if (counter == 1){
                columnNum = findNum(lineArr);
            }
            counter++;
        }

        return column.toString();
    }

    public int findNum(String []lineArr) {
        for (int i = 0; i < lineArr.length; i++) {
            if (lineArr[i].replace(" ", "").equalsIgnoreCase(columnName)) {
                return i;
            }
        }

        System.err.println("Виникла помилка при знаходженні номера колонки!");
        return -1;
    }

    private String infoGetter(){
        InformationGetter informationGetter = new InformationGetter();
        return informationGetter.getInfo(nameAndPathGetter.getFilePath(), fileName);
    }
}