package Actions.AgencyActions.ListsCreator;

import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReader;

public class InformationGetter {
    public String getInfo(String filePath, String fileName) {
        AbstractFileReader fileReader = new SystemFileReader(filePath);
        return fileReader.readFile(fileName);
    }
}
