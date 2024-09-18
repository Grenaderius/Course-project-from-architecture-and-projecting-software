package CommandExecutor;

import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.FirmNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.FileDeleter.FileDeleter;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;

import java.util.Scanner;

public class FileDeleterCommand implements Command, FileChooserInterface{
    private final String userType;

    public FileDeleterCommand(String userType){
        this.userType = userType;
    }

    @Override
    public void execute() {
        NameAndPathGetter firmNameAndPathGetter = userType.equals("Firm") ? new FirmNameAndPathGetter() : new AgencyNameAndPathGetter();
        String userChoose = chooseFile(firmNameAndPathGetter.getMainFileName(), firmNameAndPathGetter.getFilePath());

        FileDeleter fileDeleter = new FileDeleter(userChoose, firmNameAndPathGetter.getFilePath());
        fileDeleter.deleteFile();
        fileDeleter.deleteNameOfFileFromMAinFile(firmNameAndPathGetter.getMainFileName());
    }

    @Override
    public String chooseFile(String fileName, String filePath) {
        Scanner scanner = new Scanner(System.in);

        NumberOfExistsFilesFinderAndPrinter findFile = new NumberOfExistsFilesFinderAndPrinter(filePath, fileName);
        findFile.showContent();
        return findFile.returnNameOfFile(scanner.nextInt());
    }
}
