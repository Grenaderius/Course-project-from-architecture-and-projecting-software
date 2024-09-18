package CommandExecutor;

import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.FirmNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.AgencyTableReader;
import Actions.CorporateActions.FileReaderAbstractFactory.FileReaderFactory;
import Actions.CorporateActions.FileReaderAbstractFactory.FirmTableReader;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;

import java.util.Scanner;

public class TablesReaderCommand implements Command, FileChooserInterface{
    private final String userType;

    public TablesReaderCommand(String userType){
        this.userType = userType;
    }

    @Override
    public void execute() {
        NameAndPathGetter nameAndPathGetter;
        FileReaderFactory readerFactory;

        if(userType.equals("Firm")){
            nameAndPathGetter = new FirmNameAndPathGetter();
            readerFactory = new FirmTableReader();

        }else{
            nameAndPathGetter = new AgencyNameAndPathGetter();
            readerFactory = new AgencyTableReader();
        }

        String userChoose = chooseFile(nameAndPathGetter.getMainFileName(), nameAndPathGetter.getFilePath());
        System.out.println(userChoose);
        AbstractFileReader tablesReader = readerFactory.createFileReader();
        tablesReader.readFile(userChoose);
    }

    @Override
    public String chooseFile(String fileName, String filePath) {
        Scanner scanner = new Scanner(System.in);

        NumberOfExistsFilesFinderAndPrinter findFile = new NumberOfExistsFilesFinderAndPrinter(filePath, fileName);
        findFile.showContent();
        return findFile.returnNameOfFile(scanner.nextInt());
    }
}
