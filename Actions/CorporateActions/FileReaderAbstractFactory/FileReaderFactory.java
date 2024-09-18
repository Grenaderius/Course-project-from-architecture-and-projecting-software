package Actions.CorporateActions.FileReaderAbstractFactory;

public abstract class FileReaderFactory {
    //абстрактна фабрика для передачі об'єктів, прив'язаних до шляху для читання файлу
    public abstract AbstractFileReader createFileReader();
}
