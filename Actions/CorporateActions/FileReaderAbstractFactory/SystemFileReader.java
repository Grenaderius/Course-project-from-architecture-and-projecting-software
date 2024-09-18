package Actions.CorporateActions.FileReaderAbstractFactory;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class SystemFileReader extends AbstractFileReader{
    private final String filePath;

    public SystemFileReader(String filePath){
        this.filePath = filePath;
    }

    @Override
    public String readFile(String fileName){
        //методи для читання системних файлів, має реалізовуватися через збереження інфи до стрінги, які будуть передаватися або викликатися методом для подальших операцій
        StringBuilder contentBuilder = new StringBuilder();
        File file = new File(filePath + fileName);

        if(file.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                String line;
                while ((line = br.readLine()) != null){
                    contentBuilder.append(line).append("\n");
                }
            }catch (IOException e){
                System.err.println("Сталася помилка при спробі зчитати інформацію із файлу \"" + fileName + "\"!");
            }
        }else {
            System.err.println("Система не змогла знайти необхідний файл!");
        }

        return contentBuilder.toString();
    }
}
