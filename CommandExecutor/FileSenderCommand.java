package CommandExecutor;

import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.FirmNameAndPathGetter;
import Actions.CorporateActions.FileCreator.AgencyFileCreator;
import Actions.CorporateActions.FileCreator.FileInterface;
import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.FileReaderFactory;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReaderFactory;
import Actions.CorporateActions.FileWriter.FileWriterInterface;
import Actions.CorporateActions.FileWriter.SimpleFileWriter;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;

import java.util.Scanner;

public class FileSenderCommand implements Command, FileChooserInterface{
    private final String userType;


    public FileSenderCommand(String userType){
        this.userType = userType;
    }


    @Override
    public void execute() {
        if(userType.equals("Firm")){
            FirmNameAndPathGetter firmNameAndPathGetter = new FirmNameAndPathGetter();
            AgencyNameAndPathGetter agencyNameAndPathGetter = new AgencyNameAndPathGetter();

            String userChoose = chooseFile(firmNameAndPathGetter.getMainFileName(), firmNameAndPathGetter.getFilePath());
            String []userChooseArr = userChoose.split("\\.txt");

            //видалення першого рядка і його отримання, запис типу у файл
            FileReaderFactory fileReaderFactory = new SystemFileReaderFactory(firmNameAndPathGetter.getFilePath());
            AbstractFileReader abstractFileReader = fileReaderFactory.createFileReader();
            String fileInfo = abstractFileReader.readFile(userChooseArr[0] + ".txt");
            fileInfo = deleteFirstLine(fileInfo);

            //створення нового файлу у файловій системі агентства
            FileInterface fileInterface = new AgencyFileCreator();
            fileInterface.createFile(userChooseArr[0], "groupTable");

            FileWriterInterface fileWriterInterface = new SimpleFileWriter();
            fileWriterInterface.writeToFile(userChooseArr[0] + ".txt", agencyNameAndPathGetter.getFilePath(), fileInfo);
        }else{

        }
    }

    @Override
    public String chooseFile(String fileName, String filePath) {
        Scanner scanner = new Scanner(System.in);

        NumberOfExistsFilesFinderAndPrinter findFile = new NumberOfExistsFilesFinderAndPrinter(filePath, fileName);
        findFile.showContent();
        return findFile.returnNameOfFile(scanner.nextInt());
    }

    //видалення першого рядочка, що містить тип файлу
    private String deleteFirstLine(String text){
        String []textArr = text.split("\n");

        StringBuilder textBuilder = new StringBuilder();
        for (int i = 1; i < textArr.length; i++) {
            textBuilder.append(textArr[i]).append("\n");
        }

        return textBuilder.toString();
    }
}
