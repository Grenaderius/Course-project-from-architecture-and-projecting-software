package Actions.CorporateActions.FileReaderAbstractFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UserFileReader extends AbstractFileReader{
    private final String filePath;

    public UserFileReader(String filePath){
        this.filePath = filePath;
    }

    @Override
    public String readFile(String fileName) {
        checkPath();
        //читання файлу для користувачів з демонстрацією на екран
        File newFile = new File(filePath + fileName);

        if(newFile.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(newFile))){
                String line;
                while ((line = br.readLine()) != null){
                    System.out.println(line);
                }
            }catch (IOException e){
                System.err.println("Сталася помилка при спробі зчитати інформацію із файлу \"" + fileName + "\"!");
            }
        }else {
            System.err.println("Система не змогла знайти необхідний файл!");
        }

        return "";
    }

    public void checkPath(){
        File file = new File(filePath);

        if (!file.exists()) {
            // Створення папки, якщо вона не існує
            if (file.mkdirs()) {
                System.out.println("Папка \"" + filePath + "\" була створена.");
            } else {
                System.err.println("Не вдалося створити папку \"" + filePath + "\".");
            }
        }
    }
}
