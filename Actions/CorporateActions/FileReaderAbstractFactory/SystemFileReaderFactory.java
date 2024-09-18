package Actions.CorporateActions.FileReaderAbstractFactory;

public class SystemFileReaderFactory extends FileReaderFactory{
    //отримання та передача шляху до файлу
    private String filePath;

    public SystemFileReaderFactory(String filePath){
        this.filePath = filePath;
    }

    //Створення об'єкта читання файлу
    @Override
    public AbstractFileReader createFileReader() {
        return new SystemFileReader(filePath);
    }
}
