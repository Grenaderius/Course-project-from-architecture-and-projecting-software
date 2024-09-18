package Actions.AgencyActions.AddPlane;

import Actions.AgencyActions.ListsCreator.InformationGetter;
import Actions.CorporateActions.FileAndPathGetters.AgencyNameAndPathGetter;
import Actions.CorporateActions.FileAndPathGetters.NameAndPathGetter;
import Actions.CorporateActions.FileCreator.AgencyFileCreator;
import Actions.CorporateActions.FileCreator.FileInterface;
import Actions.CorporateActions.FileWriter.FileWriterInterface;
import Actions.CorporateActions.FileWriter.SimpleFileWriter;
import Actions.TablesPatterns.TablesFillerPatterns;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class PlanesAdder {
    private final String fileName = "PlanesFile";
    private final String fileType = "Planes";
    private String raceName;
    private String raceDate;
    private int passengersCount;
    private final String groupFile;
    private String planeType = "";

    private double cargoWeight = 0;
    private double cargoCapacity = 0;
    private int borrowedPassengersPlacesCount = 0;

    Scanner sc = new Scanner(System.in);
    AgencyNameAndPathGetter agencyNameAndPathGetter = new AgencyNameAndPathGetter();

    public PlanesAdder(String groupFile){
        this.groupFile = groupFile;
        createPlanesFile();
    }

    public void createPlane(){
        addPlaneToFile();
    }

    private void userInfoGetter(){
        System.out.println("Уведіть назву рейсу:");
        raceName = sc.nextLine();

        System.out.println("Уведіть дату рейсу(Наприклад: 11.11.2023):");
        raceDate = sc.nextLine();

        System.out.println("Уведіть кількість місць у літаку:");
        passengersCount = sc.nextInt();

        planeTypeChooser();
    }

    private void planeTypeChooser(){
        while (planeType.isEmpty()){
            System.out.println("Оберіть тип літака(1 - пасажирський, 2 - вантажний):");
            int typeNumb = sc.nextInt();

            if(typeNumb == 1) planeType = "пасажирський";
            else if(typeNumb == 2) planeType = "вантажний";
        }
    }

    private void createPlanesFile(){
        NameAndPathGetter agencyNameAndPathGetter = new AgencyNameAndPathGetter();
        File file = new File(agencyNameAndPathGetter.getFilePath() + fileName + ".txt");

        if(!file.exists()){
            FileInterface fileInterface = new AgencyFileCreator();
            fileInterface.createFile(fileName, fileType);

            TablesFillerPatterns tablesFillerPatterns = new TablesFillerPatterns();
            FileWriterInterface writerInterface = new SimpleFileWriter();
            writerInterface.writeToFile(fileName + ".txt", agencyNameAndPathGetter.getFilePath(), "№|" + tablesFillerPatterns.returnPlanesPattern() + "\n");
        }
    }

    private void addPlaneToFile(){
        userInfoGetter();
        getWeightAndPassengers();
        int planeInListNumber = numerationFinder();

        FileWriterInterface fileWriter = new SimpleFileWriter();
        fileWriter.writeToFile(fileName + ".txt", agencyNameAndPathGetter.getFilePath(), planeInListNumber + "|" + raceName + "|" + planeType + "|" + passengersCount + "|" + borrowedPassengersPlacesCount + "|" + cargoWeight + "|" + cargoCapacity + "|" + raceDate + "|" + groupFile + "\n");
    }

    private void getWeightAndPassengers(){
        String fileInfo = getInfoFromGroupFile();
        String infoArr[] = fileInfo.split("\n");
        int linesCounter = 0;

        for(String line : infoArr){
            String []lineArr = line.split("\\|");

            if(linesCounter > 1){
                cargoWeight += stringToDouble(lineArr[10]);
            }

            linesCounter++;
        }

        borrowedPassengersPlacesCount = linesCounter - 1;
        cargoWeight = roundToTwoDecimalPlaces(stringToDouble(cargoWeight + ""));
        cargoCapacity = roundToTwoDecimalPlaces(stringToDouble((cargoWeight * borrowedPassengersPlacesCount/10) + ""));
    }

    private String getInfoFromGroupFile(){
        InformationGetter informationGetter = new InformationGetter();
        return informationGetter.getInfo(agencyNameAndPathGetter.getFilePath(), groupFile);
    }

    private double stringToDouble(String element) {
        double doubleEl = 0;
        try {
            element = element.replace(',', '.');
            doubleEl = Double.parseDouble(element);
        } catch (NumberFormatException e) {
            return doubleEl;
        }
        return doubleEl;
    }

    private double roundToTwoDecimalPlaces(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private int numerationFinder(){
        int number = 1;
        String lastLine = readLastLine();
        String []lastLineArr = lastLine.split("\\|");
        System.out.println("Last line : " + lastLine);

        try {

          number = Integer.parseInt(lastLineArr[0]) + 1;

        } catch (NumberFormatException e) {
            return number;
        }

        return number;
    }

    private String readLastLine() {
        String filePath = agencyNameAndPathGetter.getFilePath() + fileName + ".txt";

        String lastLine = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                lastLine = currentLine;
            }
        } catch (IOException e) {
            return "";
        }
        return lastLine;
    }
}