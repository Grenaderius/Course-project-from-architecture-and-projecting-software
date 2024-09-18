package Actions.CorporateActions.FileReaderAbstractFactory;

public class AgencyFileReaderFactory extends FileReaderFactory{
    private final String projectPath = System.getProperty("user.dir");
    private final String relativeFilePath = "CourseProjectUserFiles\\AgencyFiles\\";
    private final String filePath = projectPath + relativeFilePath;

    //передача шляху до файлу
    @Override
    public AbstractFileReader createFileReader() {
        return new UserFileReader(filePath);
    }
}
