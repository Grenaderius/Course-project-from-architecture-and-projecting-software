package Actions.AgencyActions.DateActions.ListByDateFinder;

import Actions.AgencyActions.DateActions.DateComparison.DateComparison;

public class ListByDateForGroupFinder {
    private final String list;
    private final String firstDate;
    private final String secondDate;

    public ListByDateForGroupFinder(String list, String firstDate, String secondDate){
        this.list = list;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    public String getListByDateDivided(){
        return divideList();
    }

    private String divideList(){
        StringBuilder list = new StringBuilder();
        String []listArr = this.list.split("\n");
        DateComparison dateComparison = new DateComparison();
        list.append(listArr[1]).append("\n");

        for(int i = 2; i < listArr.length; i++){
            String []lineArr = listArr[i].split("\\|");

            if(dateComparison.compareDataBefore(lineArr[12], secondDate) && dateComparison.compareDataAfter(lineArr[11], firstDate)){
                list.append(listArr[i]).append("\n");
            }
        }

        return list.toString();
    }

}
