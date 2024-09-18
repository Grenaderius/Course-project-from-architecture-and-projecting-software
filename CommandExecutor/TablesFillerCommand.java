package CommandExecutor;

import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.FirmNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.TablesFiller.TablesFiller;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;

import java.util.Scanner;

public class TablesFillerCommand implements Command, FileChooserInterface{
    private final String userType;

    public TablesFillerCommand(String userType){
        this.userType = userType;
    }

    @Override
    public void execute() {
            NameAndPathGetter nameAndPathGetter;

        if(userType.equals("Firm")){
            nameAndPathGetter = new FirmNameAndPathGetter();
        }else {
            nameAndPathGetter = new AgencyNameAndPathGetter();
        }

        TablesFiller tablesFiller = new TablesFiller(chooseFile(nameAndPathGetter.getMainFileName(), nameAndPathGetter.getFilePath()), nameAndPathGetter.getFilePath());
        tablesFiller.writeToFile();
    }

    @Override
    public String chooseFile(String fileName, String filePath) {
        Scanner scanner = new Scanner(System.in);

        NumberOfExistsFilesFinderAndPrinter findFile = new NumberOfExistsFilesFinderAndPrinter(filePath, fileName);
        findFile.showContent();
        return findFile.returnNameOfFile(scanner.nextInt());
    }
}
