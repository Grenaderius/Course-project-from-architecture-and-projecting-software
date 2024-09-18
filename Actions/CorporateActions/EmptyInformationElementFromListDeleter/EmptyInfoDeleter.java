package Actions.CorporateActions.EmptyInformationElementFromListDeleter;

public class EmptyInfoDeleter {
    private final String column;
    private final String list;

    public EmptyInfoDeleter(String column, String list){
        this.column = column;
        this.list = list;
    }

    public String returnListWithEmptiesDeleted(){
        String []arrList = list.split("\n");
        StringBuilder listWithoutEmpties = new StringBuilder();
        boolean flag = false;
        int columnNumber = -1;
        int counterForArrList = 0;

        for (String line : arrList){
            String []lineArr = line.split("\\|");

            if(flag && !lineArr[columnNumber].trim().isEmpty()){
                listWithoutEmpties.append(arrList[counterForArrList]).append("\n");
            }

            if (!flag){
                columnNumber = getColumnNumber(lineArr);
                flag = true;

                if(columnNumber < 0){
                    System.err.println("Помилка, відповідну колонку не знайдено!!!");
                    break;
                }
            }

            counterForArrList++;
        }

        return listWithoutEmpties.toString();
    }

    private int getColumnNumber(String []lineArr){
        int number = -1;

        for (int i = 0; i < lineArr.length; i++) {
            if(lineArr[i].replace(" ", "").equals(column)){
                number = i;
            }
        }

        return number + 1;
    }
}
