package CommandExecutor.FinancesCommand;

import Actions.AgencyActions.FinanceOperations.GroupAndCategoryFinancesCalculations;
import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReader;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;
import CommandExecutor.Command;
import CommandExecutor.FileChooserInterface;

import java.util.Scanner;

public class GroupFinancesFinderCommand implements Command, FileChooserInterface {
    NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    String chosenFile = chooseFile(nameAndPathGetter.getMainFileName(), nameAndPathGetter.getFilePath());
    InformationGetter informationGetter = new InformationGetter();

    @Override
    public void execute() {
        if(checkFileType()){
            GroupAndCategoryFinancesCalculations groupAndCategoryFinancesCalculations = new GroupAndCategoryFinancesCalculations(returnListWithoutType());
            groupAndCategoryFinancesCalculations.printReportForGroup();
        }else {
            System.err.println("Вибрано файл не того типу!!!");
        }
    }

    private String returnListWithoutType(){
        String startingList = informationGetter.getInfo(nameAndPathGetter.getFilePath(), chosenFile);
        String []startingListArr = startingList.split("\n");
        StringBuilder returningList = new StringBuilder();

        for (int i = 1; i < startingListArr.length; i++){
            returningList.append(startingListArr[i]).append("\n");
        }

        return returningList.toString();
    }

    private boolean checkFileType(){
        AbstractFileReader fileReader = new SystemFileReader(nameAndPathGetter.getFilePath());
        String fileInfo = fileReader.readFile(chosenFile);
        String []fileInfoArr = fileInfo.split("\n");

        System.out.println(fileInfoArr[0]);
        return fileInfoArr[0].equals("Тип файлу: groupTable");
    }

    @Override
    public String chooseFile(String fileName, String filePath) {
        Scanner scanner = new Scanner(System.in);

        NumberOfExistsFilesFinderAndPrinter findFile = new NumberOfExistsFilesFinderAndPrinter(filePath, fileName);
        findFile.showContent();
        return findFile.returnNameOfFile(scanner.nextInt());
    }
}
