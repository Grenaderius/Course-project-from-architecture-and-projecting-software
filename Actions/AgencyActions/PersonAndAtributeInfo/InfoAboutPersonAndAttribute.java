package Actions.AgencyActions.PersonAndAtributeInfo;

import Actions.AgencyActions.DateActions.DateComparison.DateComparison;

public class InfoAboutPersonAndAttribute {
    private final String passNumb;
    private final String list;
    private final String attribute;
    private final String firstDate;
    private final String secondDate;
    private int lineNumb = 0;

    public InfoAboutPersonAndAttribute(String list, String passNumb, String attribute, String firstDate, String secondDate){
        this.attribute = attribute;
        this.passNumb = passNumb;
        this.list = list;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    //11.12
    public boolean findInfoWithDate(){
        if(findInfo()){
            DateComparison dateComparison = new DateComparison();
            String []arrList = list.split("\n");
            String []lineList = arrList[lineNumb].split("\\|");

            return dateComparison.compareDataAfter(lineList[11], firstDate) && dateComparison.compareDataBefore(lineList[12], secondDate);
        }
        return false;
    }



    public boolean findInfo(){
        String []arrList = list.split("\n");
        boolean isAttribute = false;

        for (String line : arrList){
            String []lineArr = line.split("\\|");

            if (lineNumb>0) {
                if(lineArr[3].replace(" ", "").equals(passNumb)){
                    isAttribute = findAttribute(lineArr);
                    break;
                }
            }
            lineNumb++;
        }

        return isAttribute;
    }

    private boolean findAttribute(String []lineArr){
        for (int i = 0; i < lineArr.length; i++) {
            if(lineArr[i].replace(" ", "").equals(attribute.replace(" ", ""))){
                return true;
            }
        }
        return false;
    }

}
