package CommandExecutor.CountsCommand;

import Actions.AgencyActions.CountOfTouristsFinders.CountOfTouristsByDateFinder;
import Actions.AgencyActions.ListsCreator.AdditionalChoosingForListsCreation.TypeForDividerChooser;
import Actions.CorporateActions.EmptyInformationElementFromListDeleter.EmptyInfoDeleter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReader;
import Actions.TablesPatterns.TablesFillerPatterns;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;
import CommandExecutor.Command;
import CommandExecutor.FileChooserInterface;

import java.util.Scanner;

public class CountOfTouristsThatBookedExcursionsByDate implements Command, FileChooserInterface {
    NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    String chosenFile = chooseFile(nameAndPathGetter.getMainFileName(), nameAndPathGetter.getFilePath());
    TypeForDividerChooser typeForDividerChooser = new TypeForDividerChooser();

    @Override
    public void execute() {
        if(checkFileType()){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Будь ласка, уведіть початкову дату(приклад 12.04.2023):");
            String firstDate = scanner.nextLine();
            System.out.println("Будь ласка, уведіть кінцеву дату(приклад 30.05.2023):");
            String secondDate = scanner.nextLine();

            CountOfTouristsByDateFinder countOfTouristsByDateFinder = new CountOfTouristsByDateFinder(chosenFile, firstDate, secondDate);
            EmptyInfoDeleter emptyInfoDeleter = new EmptyInfoDeleter(typeForDividerChooser.getTypeForExcursions(), countOfTouristsByDateFinder.listByDataCreator());

            int countOfTourists = countOfTouristsByDateFinder.getCountOfTourists(emptyInfoDeleter.returnListWithEmptiesDeleted());
            System.out.println("Кількість туристів, що замовляли екскурсії за період від " + firstDate + " і до " + secondDate + " є: " + countOfTourists);

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
