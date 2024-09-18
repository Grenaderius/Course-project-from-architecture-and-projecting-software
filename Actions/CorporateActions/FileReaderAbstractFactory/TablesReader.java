package Actions.CorporateActions.FileReaderAbstractFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class TablesReader extends AbstractFileReader{
    private final String filePath;

    public TablesReader(String filePath){
        this.filePath = filePath;
    }
    
    @Override
    public String readFile(String fileName) {

        //читання файлу для користувачів з демонстрацією на екран
        File newFile = new File(filePath + fileName);
        printTable(getColumnWidth(newFile, fileName), newFile, fileName);

        return "";
    }

    private void printTable(Integer[] arrayOfCharactersCounters, File newFile, String fileName){

        if(newFile.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(newFile))){
                String line;
                br.readLine();

                while ((line = br.readLine()) != null){
                     String[] lineArr = line.split("\\|");
                    //printRow(arrayOfCharactersCounters);

                    for (int i = 0; i < arrayOfCharactersCounters.length; i++) {
                        System.out.print(lineArr[i]);
                        
                        for (int j = 0; j < arrayOfCharactersCounters[i] - lineArr[i].length() + 1; j++) {
                            System.out.print(" ");
                        }
                        System.out.print("|");
                    }
                    System.out.println();

                }

            }catch (IOException e){
                System.err.println("Сталася помилка при спробі зчитати інформацію із файлу \"" + fileName + "\"!");
            }
        }else {
            System.err.println("Система не змогла знайти необхідний файл!");
        }
    }

    private Integer[] getColumnWidth(File newFile, String fileName){
        Integer []arrayOfCharactersCounters = new Integer[0];

        if(newFile.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(newFile))){
                String line;
                //пропуск рядка типу
                br.readLine();

                int flag = 0;
                while ((line = br.readLine()) != null){
                    String []lineArr = line.split("\\|");

                    if(flag!=1){
                        flag = 1;
                        arrayOfCharactersCounters = new Integer[lineArr.length];
                        Arrays.fill(arrayOfCharactersCounters, 0);
                    }

                    for (int i = 0; i < arrayOfCharactersCounters.length; i++) {
                        if(arrayOfCharactersCounters[i]<lineArr[i].length()){
                            arrayOfCharactersCounters[i] = lineArr[i].length();
                        }

                    }
                }
            }catch (IOException e){
                System.err.println("Сталася помилка при спробі зчитати інформацію із файлу \"" + fileName + "\"!");
            }
        }else {
            System.err.println("Система не змогла знайти необхідний файл!");
        }
        return arrayOfCharactersCounters;
    }
}
