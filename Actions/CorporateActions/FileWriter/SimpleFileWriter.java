package Actions.CorporateActions.FileWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleFileWriter implements FileWriterInterface{

    @Override
    public void writeToFile(String fileName, String filePath, String text) {
        File file = new File(filePath + fileName);
        if(!file.exists()){
            System.err.println("Система не змогла знайти файл " + fileName + "!");
        }else {
            try {
                FileWriter writer = new FileWriter(file, true);
                writer.write(text);
                writer.close();
                System.out.println("Необхідну інформацію було записано! " + fileName + "!");
            } catch (IOException e) {
                System.err.println("Система не може додати інформацію до файлу " + fileName + "!");
            }

        }
    }
}
