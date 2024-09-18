package Actions.AgencyActions.ListsCreator;

import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;

public class ListsCreator implements  ListCreatorInterface{
    NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    private final String filePath = nameAndPathGetter.getFilePath();
    private final String fileName;
    private final int []columnsArr;

    public ListsCreator(String fileName, int []columnsArr){
        this.fileName = fileName;
        this.columnsArr = columnsArr;
    }

    @Override
    public String createList() {
        return listCreator();
    }

    private String listCreator(){
        StringBuilder list = new StringBuilder();
        String []lines = divideInfo();
        String []columns;
        int columnsChecker = 0;

        for(int i = 1; i < lines.length; i++){
                columns = lines[i].split("\\|");

                for (int j = 0; j < columns.length; j++) {
                    for (int value : columnsArr) {
                        if (j == value) {
                            columnsChecker = 1;
                            break;
                        }
                    }

                    if (columnsChecker == 0) list.append(columns[j]).append(" |");
                    columnsChecker = 0;

                }

                list.append("\n");
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
