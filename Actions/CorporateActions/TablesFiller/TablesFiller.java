package Actions.CorporateActions.TablesFiller;

import Actions.CorporateActions.FileWriter.FileWriterInterface;
import Actions.CorporateActions.FileWriter.SimpleFileWriter;
import Actions.TablesPatterns.TablesFillerPatterns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TablesFiller {
    private String fileName;
    private String filePath;
    TablesFillerPatterns tablesFillerPatterns = new TablesFillerPatterns();
    FileWriterInterface fileWriter = new SimpleFileWriter();

    public TablesFiller(String fileName, String filePath){
        this.fileName = fileName;
        this.filePath = filePath;
    }

    //метод для запису інформації у файл за шаблоном
    public void writeToFile() {
        //додати реалізацію для запису інфи по рядках, беручи назви рядків для введення із самих патернів
        String [] patternArr = createPatternArr();
        String line;
        String listener = "";

        NumerationGetter numerationGetter = new NumerationGetter(filePath, fileName);
        int counterForID = numerationGetter.getNumeration();

        if(counterForID<1){
            writePattern();
        }

        Scanner sc = new Scanner(System.in);
        while (!listener.equals("так")){
            counterForID++;
            line = counterForID + "|";

            for (int i = 0; i < patternArr.length; i++) {
                if(!patternArr[i].isEmpty()){
                    System.out.println("Уведіть будь ласка " + patternArr[i] + ":");
                    line += sc.nextLine() + "|";
                }
            }
            line += "\n";
            System.out.println(line);
            fileWriter.writeToFile(fileName, filePath, line);
            System.out.println("Хочете завершити запис інформації до таблиці?(так/ні)");

            listener = sc.nextLine();
        }
    }

    //метод запису шаблону, має відбуватися при створенні файлу
    private void writePattern(){
        String pattern = getPattern() + "\n";
        fileWriter.writeToFile(fileName, filePath, "№|" + pattern);
    }

    private String[] createPatternArr(){
        String pattern = getPattern();
        String [] patternArr;

        if(!pattern.isEmpty()){
            patternArr = pattern.split("\\|");
            return patternArr;
        }else return null;

    }

    private String getPattern(){
        String pattern = "";
        String type = getFileType();

        if(type.equals("Тип файлу: groupTable")){
            pattern = tablesFillerPatterns.returnGroupPattern();
        } else if (type.equals("Тип файлу: storage")) {
            pattern = tablesFillerPatterns.returnStoragePattern();
        }

        System.out.println(pattern);

        return pattern;
    }

    //метод, що визначає тип файлу
    private String getFileType(){
        String type = "none";
        File newFile = new File(filePath + fileName);

        if(newFile.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(newFile))){
                type = br.readLine();
            }catch (IOException e){
                System.err.println("Сталася помилка при спробі зчитати інформацію із файлу \"" + fileName + "\"!");
            }
        }else {
            System.err.println("Система не змогла знайти необхідний файл!");
        }

        System.out.println("type: " + type);

        return type;
    }

}
