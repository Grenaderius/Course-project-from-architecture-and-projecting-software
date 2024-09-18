package CommandExecutor;

import Actions.CorporateActions.FileCreator.AgencyFileCreator;
import Actions.CorporateActions.FileCreator.FileInterface;
import Actions.CorporateActions.FileCreator.FirmFileCreator;

import java.util.Scanner;

public class FileCreatorCommand implements Command{
    //додаткові параметри або методи для предачі параметрів до функції execute!!!
    private final String userType;

    public FileCreatorCommand(String userType){
        this.userType = userType;
    }

    @Override
    public void execute() {
        //виклик методів-реалізаторів
        Scanner scanner = new Scanner(System.in);
        FileInterface newFile1 = userType.equals("Firm") ? new FirmFileCreator() : new AgencyFileCreator();

            System.out.println("Будь ласка, уведіть назву файлу:");

            String newFileName = scanner.nextLine();
            String fileType = typeChooser();

            if(!fileType.isEmpty()) newFile1.createFile(newFileName, fileType);
        }

    private String typeChooser(){
        String fileType;

        if (userType.equals("Firm")) {
                fileType = "groupTable";
        }else if(userType.equals("Storage")){
            fileType = "storage";
        }else {
            fileType = "none";
        }

        return fileType;
    }

}
