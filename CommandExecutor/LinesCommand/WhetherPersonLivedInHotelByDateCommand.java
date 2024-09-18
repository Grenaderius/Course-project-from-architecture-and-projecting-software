package CommandExecutor.LinesCommand;

import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.AgencyActions.PersonAndAtributeInfo.InfoAboutPersonAndAttribute;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReader;
import Actions.SystemActions.FilesWithNamesOfTablesOperation.NumberOfExistsFilesFinderAndPrinter;
import CommandExecutor.Command;
import CommandExecutor.FileChooserInterface;

import java.util.Scanner;

public class WhetherPersonLivedInHotelByDateCommand implements Command, FileChooserInterface {
    NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    String chosenFile = chooseFile(nameAndPathGetter.getMainFileName(), nameAndPathGetter.getFilePath());

    @Override
    public void execute() {
        if(checkFileType()){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Будь ласка, уведіть номер паспорта турисита:");
            String passNumb = scanner.nextLine();

            System.out.println("Будь ласка, уведіть назву готелю:");
            String hotelName = scanner.nextLine();

            System.out.println("Будь ласка, уведіть початкову дату(приклад 12.04.2023):");
            String firstDate = scanner.nextLine();

            System.out.println("Будь ласка, уведіть кінцеву дату(приклад 30.05.2023):");
            String secondDate = scanner.nextLine();



            InfoAboutPersonAndAttribute infoAboutPersonAndAttribute = new InfoAboutPersonAndAttribute(findList(), passNumb, hotelName, firstDate, secondDate);
            boolean check = infoAboutPersonAndAttribute.findInfoWithDate();

            if(check){
                System.out.println("Людина із номером паспорта " + passNumb +  " проживала у готелі " + hotelName + " у період з " + firstDate + " і до " + secondDate + "!");
            }else {
                System.out.println("Людина із номером паспорта " + passNumb +  " не проживала у готелі " + hotelName + " у період з " + firstDate + " і до " + secondDate + "!");
            }

        }else {
            System.err.println("Вибрано файл не того типу!!!");
        }
    }

    private String findList(){
        InformationGetter informationGetter = new InformationGetter();
        return informationGetter.getInfo(nameAndPathGetter.getFilePath(), chosenFile);
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