package CommandExecutor.LinesCommand;

import Actions.AgencyActions.DateActions.FindInfoByDate.FindInfoByDate;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import CommandExecutor.Command;

import java.util.Scanner;

public class PlaneRaceByDateFinderCommand implements Command {
    NameAndPathGetter agencyNameAndPathGetter = new AgencyNameAndPathGetter();
    Scanner scanner = new Scanner(System.in);
    private final String fileName = "PlanesFile";
    private final String filePath = agencyNameAndPathGetter.getFilePath();

    @Override
    public void execute() {
        System.out.println("Будь ласка уведіть дату, рейс на яку бажаєте побачити(наприклад: 11.11.2023):");
        String raceDate = scanner.nextLine();
        FindInfoByDate findInfoByDate = new FindInfoByDate(filePath, fileName, raceDate, "дата рейсу");
        findInfoByDate.infoForPlaneWriter();
    }
}
