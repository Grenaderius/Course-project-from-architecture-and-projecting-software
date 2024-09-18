package Actions.AgencyActions.DateActions.FindInfoByDate;

import Actions.AgencyActions.ListsCreator.InformationGetter;

public class FindInfoByDate {
    private final String fileName;
    private final String filePath;
    private final String raceDate;
    private final String column;

    public FindInfoByDate(String filePath, String fileName, String raceDate, String column){
        this.fileName = fileName + ".txt";
        this.filePath = filePath;
        this.raceDate = raceDate;
        this.column = column;
    }

    public String getLineInfo(){
        return getLineFromList();
    }

    public void infoForPlaneWriter(){
        String []line = getLineFromList().split("\\|");
        if(line.length>5){
            System.out.println("Рейс літака на вашу дату:"
                    + "\nНомер у списку: " + line[0]
                    + "\nНазва рейсу: " + line[1]
                    + "\nТип: " + line[2]
                    + "\nКількість пасажирських місць: " + line[3]
                    + "\nКількість перевезених туристів: " + line[4]
                    + "\nВага вантажу: " + line[5]
                    + "\nОб'єм вантажу: " + line[6]
                    + "\nДата рейсу: " + line[7]);
        }else System.err.println("Виникла помилка при спробі знайти рейс на необхідну дату, будь ласка спробуйте ще раз!");
    }

    private String getLineFromList(){
        String []linesArr = getFileInfo().split("\n");
        int columnPlace = findColumnPlace();
        int counter = 0;

        if(columnPlace > -1){
            for(String line : linesArr){

                if(counter > 1){
                    String []lineArr = line.split("\\|");

                    if(lineArr[columnPlace].replace(" ", "").equals(raceDate)){
                        return line;
                    }

                }

                counter++;
            }
        }

        return "Не знайдено!";
    }

    private int findColumnPlace(){
        String []linesArr = getFileInfo().split("\n");
        String []lineArr = linesArr[1].split("\\|");

        for (int i = 0; i < lineArr.length; i++){
            if(lineArr[i].replace(" ", "").equals(column.replace(" ", ""))) return i;
        }

        return -1;
    }

    private String getFileInfo(){
        InformationGetter informationGetter = new InformationGetter();
        return informationGetter.getInfo(filePath, fileName);
    }

}
