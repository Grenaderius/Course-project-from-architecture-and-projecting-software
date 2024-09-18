package Actions.CorporateActions.TablesFiller;

import Actions.CorporateActions.FileReaderAbstractFactory.AbstractFileReader;
import Actions.CorporateActions.FileReaderAbstractFactory.FileReaderFactory;
import Actions.CorporateActions.FileReaderAbstractFactory.SystemFileReaderFactory;

public class NumerationGetter {
    final String filePath;
    final String fileName;

    public  NumerationGetter(String filePath, String fileName){
        this.filePath = filePath;
        this.fileName = fileName;
    }

    private String[] checkTableFilling(){
        FileReaderFactory tablesReaderFactory = new SystemFileReaderFactory(filePath);
        AbstractFileReader firmTablesReader = tablesReaderFactory.createFileReader();
        String fileInfo = firmTablesReader.readFile(fileName);

        String[] linesArr = fileInfo.split("\n");

        if(linesArr.length > 1) return linesArr;
        return new String[0];
    }

    public Integer getNumeration(){
        int numeration = 0;

        String []linesArr = checkTableFilling();
        if(linesArr.length > 1){
            String lastLine = linesArr[linesArr.length-1];
            String[] lastLineArr = lastLine.split("\\|");
            numeration = Integer.parseInt(lastLineArr[0]);
        }

        return numeration;
    }

}
