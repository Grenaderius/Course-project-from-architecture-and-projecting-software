package CommandExecutor.ListsCommands;

import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.AgencyActions.ListsPrinter.ListsPrinter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import CommandExecutor.Command;

import java.util.Scanner;

public class ListFromRaceWithAddingsCommand implements Command {
    NameAndPathGetter nameAndPathGetter = new AgencyNameAndPathGetter();
    String chosenFile = "PlanesFile.txt";
    InformationGetter informationGetter = new InformationGetter();

    @Override
    public void execute() {
        raceChooser();
    }


    private String returnListWithoutType(String startingList){
        String []startingListArr = startingList.split("\n");
        StringBuilder returningList = new StringBuilder();

        for (int i = 1; i < startingListArr.length; i++){
            returningList.append(startingListArr[i]).append("\n");
        }

        return returningList.toString();
    }

    private void raceChooser() {
        String list = informationGetter.getInfo(nameAndPathGetter.getFilePath(), chosenFile);
        list = returnListWithoutType(list);
        ListsPrinter listsPrinter = new ListsPrinter(list);
        listsPrinter.printList();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Уведіть номер рейсу, інформацію про туристів якого хочете побачити:");
        int chosenRace = scanner.nextInt();

        showGroup(getGroupFileInfo(list, chosenRace));
    }

    private void showGroup(String groupList){
        String groupInfo = informationGetter.getInfo(nameAndPathGetter.getFilePath(), groupList);
        String []infoArr = groupInfo.split("\n");
        StringBuilder newListBuilder = new StringBuilder();
        newListBuilder.append(infoArr[1]).append("|бирка|маркування\n");

        for (int i = 2; i < infoArr.length; i++) {
            String []lineArr = infoArr[i].split("\\|");
            String label = "Відправник: Представництво, Отримувач " + lineArr[1] + " " + lineArr[2];

            newListBuilder.append(infoArr[i]).append(label).append(markingChooser(lineArr)).append("\n");
        }

        ListsPrinter listsPrinter = new ListsPrinter(newListBuilder.toString());
        listsPrinter.printList();
    }

    private String markingChooser(String []lineArr){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Уведіть будь ласка маркування для вантажу туриста " + lineArr[1] + " " + lineArr[2] + ":");
        return "|" + scanner.nextLine();
    }

    private String getGroupFileInfo(String list, int chosenRace){
        String []listArr = list.split("\n");
        String []lineArr = listArr[chosenRace].split("\\|");

        return lineArr[8].trim();
    }
}