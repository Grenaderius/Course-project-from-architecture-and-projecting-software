package Actions.CorporateActions.FileCreator;

import Actions.CorporateActions.FileDeleter.FileDeleter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AgencyFileCreator implements FileInterface{
    private final String projectPath = System.getProperty("user.dir");
    private final String relativeFilePath = "CourseProjectUserFiles\\AgencyFiles\\";
    private final String filePath = projectPath + relativeFilePath;
    private String fileName;

    //Створення файлів для Агентства
    @Override
    public void createFile(String fileName, String fileType) {
        checkPath();
        File newFile = new File(filePath + fileName + ".txt");

        try{
            if(newFile.createNewFile()){
                this.fileName = fileName;
                System.out.println("Файл \"" + fileName + "\" було вдало створено!");
                writeNameOfFileInFileForUsage(fileName + ".txt", "Тип файлу: " + fileType);
                writeNameOfFileInFileForUsage("AboutAgencyTables.txt", fileName + ".txt");
            }else {
                System.err.println("Файл з іменем \"" + fileName + "\" уже існує!");
            }
        }catch (IOException e){
            System.err.println("Виникла помилка при створенні файлу \"" + fileName + "\"!");
        }
    }

    private void checkPath(){
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

    @Override
    public void writeNameOfFileInFileForUsage(String fileName, String text) {
        File writeFile = new File(filePath, fileName);

            try {
                // Створення FileWriter для файлу "AboutAgencyTables.txt"
                FileWriter newWriter = new FileWriter(writeFile, true);

                //для типу додат напис Тип файлу: type, а для назви .txt
                newWriter.write(text + "\n");
                newWriter.close();
            } catch (IOException e) {
                System.err.println("Сталася помилка при спробі додавання файлу в систему!" +
                        "\nФайл \"" + this.fileName + "\" буде видалено! Спробуйте будь ласка створити файл ще раз!");

                //Видаляє файл при невдалому створенні
                FileDeleter fileDeleter = new FileDeleter(this.fileName, filePath);
                fileDeleter.deleteFile();
            }
    }

}
