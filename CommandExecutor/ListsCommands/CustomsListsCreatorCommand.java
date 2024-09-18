package CommandExecutor.ListsCommands;

import Actions.AgencyActions.ListsCreator.AdditionalChoosingForListsCreation.ColumnsChooser;
import Actions.AgencyActions.ListsCreator.ListCreatorInterface;
import Actions.AgencyActions.ListsCreator.ListsCreator;
import Actions.AgencyActions.ListsPrinter.ListsPrinter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReader;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;
import CommandExecutor.Command;
import CommandExecutor.FileChooserInterface;

import java.util.Scanner;

public class CustomsListsCreatorCommand implements Command, FileChooserInterface {
    NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    String chosenFile = chooseFile(nameAndPathGetter.getMainFileName(), nameAndPathGetter.getFilePath());
    ColumnsChooser columnsChooser = new ColumnsChooser();

    @Override
    public void execute() {
        if(checkFileType()){
            ListCreatorInterface listsCreator = new ListsCreator(chosenFile, columnsChooser.getColumnsForCustoms());
            ListsPrinter listsPrinter = new ListsPrinter(listsCreator.createList());
            listsPrinter.printList();
        }else {
            System.err.println("Вибрано файл не того типу!!!");
        }
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
