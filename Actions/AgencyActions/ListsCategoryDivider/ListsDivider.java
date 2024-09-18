package Actions.AgencyActions.ListsCategoryDivider;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ListsDivider {
    private final String list;
    private final String finder;

    public ListsDivider(String list, String finder){
        this.list = list;
        this.finder = finder;
    }

    public String returnListDivided(){
        return splitListDivided();
    }

    private String splitListDivided(){
        String category = userChoose(getDivision());
        StringBuilder listForCustomsByCategory = new StringBuilder();

        if(category.isEmpty()) System.err.println("Сталася помилка при виборі!"); else {
            String []linesList = list.split("\n");
            listForCustomsByCategory.append(linesList[0]).append("\n");


            int typesPlace = getPlaceOfType(linesList[0].split("\\|"));

            for (int i = 1; i < linesList.length; i++) {
                String []columnsArr = linesList[i].split("\\|");

                if(columnsArr[typesPlace].equals(category)){
                    listForCustomsByCategory.append(linesList[i]).append("\n");
                }
            }
        }
        return listForCustomsByCategory.toString();
    }

    private String userChoose(Set<String> typesSet){
        System.out.println("Оберіть, список за чим бажаєте створити:");

        int counter = 0;
        for (String category : typesSet){
            counter++;
            System.out.println(counter + ". " + category);
        }

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        String category = "";
        counter = 0;

        for (String type : typesSet) {
            counter++;
            if(counter == number){
                category = type;
            }
        }

        return category;
    }

    private Set<String> getDivision(){
        String []linesArr = list.split("\n");
        String []titleArr = linesArr[0].split("\\|");
        int typeNumber = getPlaceOfType(titleArr);
        Set<String> typesSet = new HashSet<>();

        if (typeNumber>=0){
            String []columnsArr;

            for (int i = 1; i < linesArr.length; i++) {
                columnsArr = linesArr[i].split("\\|");
                typesSet.add(columnsArr[typeNumber]);
            }

            return typesSet;
        } else return typesSet = new HashSet<>();
    }

    private int getPlaceOfType(String []titleArr){
        int place = -1;

        for (int i = 0; i < titleArr.length; i++) {
            if(titleArr[i].replace(" ", "").equals(finder)){
                place = i;
            }
        }

        return place;
    }
}