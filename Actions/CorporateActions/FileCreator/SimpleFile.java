package Actions.CorporateActions.FileCreator;

import java.io.File;
import java.io.IOException;

public class SimpleFile implements FileInterface{
    String filePath;

    public SimpleFile(String filePath){
        this.filePath = filePath;
    }

    //Створення файлів для потреб системи
    @Override
    public void createFile(String fileName, String fileType) {
        checkPath();
        File newFile = new File(filePath + fileName);

        try{
            if(newFile.createNewFile()){
                System.out.println("System file with name \"" + fileName + "\" has been created");
            }
        }catch (IOException e){
            System.err.println("Exception while creating file \"" + fileName + "\"!");
        }

    }

    @Override
    public void writeNameOfFileInFileForUsage(String fileName, String fileType) {
        //немає необхідності в реалізації
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
