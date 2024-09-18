package CommandExecutor.LinesCommand;

import Actions.AgencyActions.GetInfoAboutOneTourist.GetSingleTouristsInfo;
import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.AgencyTableReader;
import Actions.CorporateActions.FileReaderAbstractFactory.FileReaderFactory;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReader;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;
import CommandExecutor.Command;
import CommandExecutor.FileChooserInterface;

import java.util.Scanner;

public class SingleTouristInfoCommand implements Command, FileChooserInterface {
    NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    String chosenFile = chooseFile(nameAndPathGetter.getMainFileName(), nameAndPathGetter.getFilePath());

    @Override
    public void execute() {
        if (checkFileType()) {
            InformationGetter informationGetter = new InformationGetter();
            GetSingleTouristsInfo getSingleTouristsInfo = new GetSingleTouristsInfo(informationGetter.getInfo(nameAndPathGetter.getFilePath(), chosenFile));

            System.out.println("виберіть номер зі списку:");
            FileReaderFactory readerFactory = new AgencyTableReader();
            AbstractFileReader tablesReader = readerFactory.createFileReader();
            tablesReader.readFile(chosenFile);

            getSingleTouristsInfo.printTouristsInfo();

        } else {
            System.err.println("Вибрано файл не того типу!!!");
        }
    }

    private boolean checkFileType() {
        AbstractFileReader fileReader = new SystemFileReader(nameAndPathGetter.getFilePath());
        String fileInfo = fileReader.readFile(chosenFile);
        String[] fileInfoArr = fileInfo.split("\n");

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