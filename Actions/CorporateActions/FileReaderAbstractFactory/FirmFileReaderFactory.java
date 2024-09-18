package Actions.CorporateActions.FileReaderAbstractFactory;

public class FirmFileReaderFactory extends FileReaderFactory{
    private final String projectPath = System.getProperty("user.dir");
    private final String relativeFilePath = "CourseProjectUserFiles\\FirmFiles\\";
    private final String filePath = projectPath + relativeFilePath;

    //передача шляху до файлу
    @Override
    public AbstractFileReader createFileReader() {
        return new UserFileReader(filePath);
    }
}
