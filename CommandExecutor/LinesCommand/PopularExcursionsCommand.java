package CommandExecutor.LinesCommand;

import Actions.AgencyActions.ColumnFinder.ColumnFinder;
import Actions.AgencyActions.GetTheMostPopularelementFromColumnInList.MostPopularElement;
import Actions.AgencyActions.ListsCreator.AdditionalChoosingForListsCreation.TypeForDividerChooser;
import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReader;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;
import CommandExecutor.Command;
import CommandExecutor.FileChooserInterface;

import java.util.Scanner;

public class PopularExcursionsCommand implements Command, FileChooserInterface{
    NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    String chosenFile = chooseFile(nameAndPathGetter.getMainFileName(), nameAndPathGetter.getFilePath());
    TypeForDividerChooser typeForDividerChooser = new TypeForDividerChooser();

    @Override
    public void execute() {
        if(checkFileType()){
            ColumnFinder columnFinder = new ColumnFinder(chosenFile, typeForDividerChooser.getTypeForExcursions());
            String column = columnFinder.getColumn();
            MostPopularElement mostPopularElement = new MostPopularElement(column, columnFinder.getList(), typeForDividerChooser.getTypeForExcursions());
            System.out.println("Найбільш популярною екскурсією є - " + mostPopularElement.getPopular() + "!");



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