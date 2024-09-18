package CommandExecutor.ListsCommands;

import Actions.AgencyActions.ListsCategoryDivider.ListsDivider;
import Actions.AgencyActions.ListsCreator.AdditionalChoosingForListsCreation.ColumnsChooser;
import Actions.AgencyActions.ListsCreator.AdditionalChoosingForListsCreation.TypeForDividerChooser;
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

public class HotelsListsCreatorCommand implements Command, FileChooserInterface {
    NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    String chosenFile = chooseFile(nameAndPathGetter.getMainFileName(), nameAndPathGetter.getFilePath());
    ColumnsChooser columnsChooser = new ColumnsChooser();
    TypeForDividerChooser typeForDividerChooser = new TypeForDividerChooser();

    @Override
    public void execute() {
        if(checkFileType()){
            ListsCreator listsCreator = new ListsCreator(chosenFile, columnsChooser.getColumnsForHotels());
            ListsDivider listsDivider = new ListsDivider(listsCreator.createList(), typeForDividerChooser.getTypeForHotels());
            ListsPrinter listsPrinter = new ListsPrinter(listsDivider.returnListDivided());
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
