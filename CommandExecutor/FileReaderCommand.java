package CommandExecutor;

import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.FirmNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.FileReaderAbstractFactory.*;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;

import java.util.Scanner;

public class FileReaderCommand implements Command, FileChooserInterface{
    private final String userType;

    public FileReaderCommand(String userType){
        this.userType = userType;
    }

    @Override
    public void execute() {
        NameAndPathGetter nameAndPathGetter;
        FileReaderFactory tablesReaderFactory;

        if(userType.equals("Firm")){
            nameAndPathGetter = new FirmNameAndPathGetter();
            tablesReaderFactory = new FirmFileReaderFactory();
        }else {
            nameAndPathGetter = new AgencyNameAndPathGetter();
            tablesReaderFactory = new AgencyFileReaderFactory();
        }

        AbstractFileReader firmTablesReader = tablesReaderFactory.createFileReader();
        firmTablesReader.readFile(chooseFile(nameAndPathGetter.getMainFileName(), nameAndPathGetter.getFilePath()));
    }

    @Override
    public String chooseFile(String fileName, String filePath) {
        Scanner scanner = new Scanner(System.in);

        NumberOfExistsFilesFinderAndPrinter findFile = new NumberOfExistsFilesFinderAndPrinter(filePath, fileName);
        findFile.showContent();
        return findFile.returnNameOfFile(scanner.nextInt());
    }
}
