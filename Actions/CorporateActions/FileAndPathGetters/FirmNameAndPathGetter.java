package Actions.CorporateActions.FileAndPathGetters;

public class FirmNameAndPathGetter implements NameAndPathGetter{
    private final String projectPath = System.getProperty("user.dir");
    private final String relativeFilePath = "CourseProjectUserFiles\\FirmFiles\\";
    private final String filePath = projectPath + relativeFilePath;
    private final String mainFileName = "AboutFirmTables.txt";

    public String getFilePath(){
        return filePath;
    }

    public String getMainFileName() {
        return mainFileName;
    }
}
