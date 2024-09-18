package Actions.SystemActions.FilesWithNamesOfTablesOperation;

import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.FileReaderFactory;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReaderFactory;

//Клас, що зчитує інформацію із файлів у яких зберігаються назви таблиць
//для бачення їх користувачем у вигляді нумерованого списку
// + метод, який буде повертати назву файлу за вибором номеру користувачем
//Працює як для фірми так і для агентства
public class NumberOfExistsFilesFinderAndPrinter {

    //такі змінні можна робити final, оскільки після надання їм значення система вже не дозволить їх змінити
    private final String filePath;
    private final String fileName;

    public NumberOfExistsFilesFinderAndPrinter(String filePath, String fileName){
        this.filePath = filePath;
        this.fileName = fileName;
    }

    //метод, що використовує SystemFileReaderFactory для зічитування та повернення інфи
    private String getFileData(){
        String fileData;

        //Використання абстрактної фабрики для читання інформації з файлу
        FileReaderFactory fileReaderFactory = new SystemFileReaderFactory(filePath);
        AbstractFileReader abstractFileReader = fileReaderFactory.createFileReader();
        fileData = abstractFileReader.readFile(fileName);

        return fileData;
    }

    //Метод, що перетворює дані, отримані з файлу на масив
    private String[] makeDataToArray(){
        return getFileData().split("\n");
    }

    //Метод, що виводить дані для користувача
    public void showContent(){
        String [] arrayOfData = makeDataToArray();
        int counter = 0;

        System.out.println("Наразі наявні такі таблиці:");
        for(String line : arrayOfData){
            counter++;
            System.out.println(counter + ". " + line);
        }
    }

    //Метод, що повертає назву файлу за номером
    public String returnNameOfFile(int number){
        String nameOfFile = null;
        String [] arrayOfData = makeDataToArray();
        int counter = 0;

        if(!checkNumber(number, arrayOfData)){
            return "Eror";
        }

        for(String line : arrayOfData){
            counter++;

            if(counter == number) nameOfFile = line;
        }

        return nameOfFile;
    }

    //Метод, що перевіряє чи номер користувача правильний
    private boolean checkNumber(int number, String[] arrayOfData){
        if(number < 1 || number > arrayOfData.length + 1){
            System.err.println("Уведене вами число не відповідає жодному із наведених файлів!");
            return false;
        }else return true;
    }

}
