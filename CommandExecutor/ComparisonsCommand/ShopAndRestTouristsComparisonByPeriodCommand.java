package CommandExecutor.ComparisonsCommand;

import Actions.AgencyActions.CountOfTouristsFinders.CountOfTouristsByDateFinder;
import Actions.AgencyActions.ListsCategoryDivider.SystemListCategoryDivider;
import Actions.AgencyActions.ListsCreator.AdditionalChoosingForListsCreation.TypeForDividerChooser;
import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReader;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;
import Actions.TablesPatterns.TablesFillerPatterns;
import CommandExecutor.Command;
import CommandExecutor.FileChooserInterface;

import java.util.Scanner;

public class ShopAndRestTouristsComparisonByPeriodCommand implements Command, FileChooserInterface {
    NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    String chosenFile = chooseFile(nameAndPathGetter.getMainFileName(), nameAndPathGetter.getFilePath());
    TypeForDividerChooser typeForDividerChooser = new TypeForDividerChooser();
    TablesFillerPatterns tablesFillerPatterns = new TablesFillerPatterns();

    @Override
    public void execute() {
        if(checkFileType()){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Будь ласка, уведіть початкову дату(приклад 12.04.2023):");
            String firstDate = scanner.nextLine();
            System.out.println("Будь ласка, уведіть кінцеву дату(приклад 30.05.2023):");
            String secondDate = scanner.nextLine();

            CountOfTouristsByDateFinder countOfTouristsByDateFinder = new CountOfTouristsByDateFinder(chosenFile, firstDate, secondDate);
            String startingList = countOfTouristsByDateFinder.listByDataCreator();

            SystemListCategoryDivider listsDivider = new SystemListCategoryDivider(("№|" + tablesFillerPatterns.returnGroupPattern() + "\n" + startingList), typeForDividerChooser.getTypeForCategory());
            String restList = listsDivider.returnListDivided("відпочиваючий");
            String shopList = listsDivider.returnListDivided("покупець");

            int restTouristsCount = countOfTouristFinder(restList);
            int shopTouristsCount = countOfTouristFinder(shopList);

            double relation = ((double) (restTouristsCount/shopTouristsCount)) * 100;
            System.out.println("Відсоткове відношення відпочиваючих туристів до туристів шоп-турів становить - " + relation + "%");

        }else {
            System.err.println("Вибрано файл не того типу!!!");
        }
    }

    private int countOfTouristFinder(String list){
        int countOfTourist = 0;
        String []listArr = list.split("\n");
        for (int i = 1; i<listArr.length; i++){
            countOfTourist++;
        }

        if(countOfTourist<=0){
            return 1;
        }

        return countOfTourist;
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
