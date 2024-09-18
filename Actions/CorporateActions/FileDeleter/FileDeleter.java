package Actions.CorporateActions.FileDeleter;

import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.FileReaderFactory;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReaderFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileDeleter {
    String fileName;
    String filePath;

    //щоб працювало у назві тре додавати txt
    //Додати реалізацію видалення назви файлу із гол. файлу, чекнути чи це взагалі працює
    public FileDeleter(String fileName, String filePath){
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public void deleteFile(){
        File deleteFile = new File(filePath + fileName);

        if(deleteFile.delete()){
            System.out.println("Файл \"" + fileName + "\" було успішно видалено!");
        }else {
            System.err.println("Не вдалося видалити файл \"" + fileName + "\"!");
        }
    }

    //окремий не приватний метод для видалення назв файлів із списку для вибору, бо не усі файли потребують таких видалень
    public void deleteNameOfFileFromMAinFile(String mainFile){
        int fileNameNumberInMainFile = readMainFileToDeleteNameFromList(mainFile);

        //отримання та видалення даних про видалений файл, перезапис іх у основний файл
        if(fileNameNumberInMainFile != -1){
            try {
                Path path = Path.of(filePath + mainFile);
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                lines.remove(fileNameNumberInMainFile);
                Files.write(Path.of(filePath + mainFile), lines, StandardCharsets.UTF_8);

            } catch (IOException e) {
                System.err.println("Сталася помилка при спробі видалення файлу зі списку!");
            }
        }else {
            System.err.println("Сталася помилка при спробі видалення файлу зі списку!");
        }
    }

    //Метод, що отримує список із головного файлу
    private int readMainFileToDeleteNameFromList(String mainFile){
        FileReaderFactory fileReaderFactory = new SystemFileReaderFactory(filePath);
        AbstractFileReader systemReader = fileReaderFactory.createFileReader();
        String [] content = systemReader.readFile(mainFile).split("\n");
        return findNumberInList(content);
    }

    //Метод, що знаходить номер файлу у списку
    private int findNumberInList(String[] list){
        int fileNameNumberInMainFile = -1;
        for (int i = 0; i < list.length; i++) {
            if(list[i].equals(fileName)){
                fileNameNumberInMainFile = i;
                break;
            }
        }
        return fileNameNumberInMainFile;
    }

}
