package Actions.CorporateActions.FileAndPathGetters;

public class AgencyNameAndPathGetter implements NameAndPathGetter{
    private final String projectPath = System.getProperty("user.dir");
    private final String relativeFilePath = "CourseProjectUserFiles\\AgencyFiles\\";
    private final String filePath = projectPath + relativeFilePath;
    private final String mainFileName = "AboutAgencyTables.txt";

    public String getFilePath() {
        return filePath;
    }

    public String getMainFileName() {
        return mainFileName;
    }
}
