package Actions.AgencyActions.ListsPrinter;

import java.util.Arrays;

public class ListsPrinter {
    private final String text;

    public ListsPrinter(String text){
        this.text = text;
    }

    public void printList(){
        String []linesArr = text.split("\n");
        int counterForNull = 0;
        int []elementsSizeArr = null;

        for(String line : linesArr){
            String []elementsArr = line.split("\\|");

            if(counterForNull != 1) {
                elementsSizeArr = new int[elementsArr.length];
                Arrays.fill(elementsSizeArr, 0);
                counterForNull = 1;
            }

            for (int i = 0; i < elementsArr.length; i++) {
                if(elementsSizeArr[i] < elementsArr[i].length()){
                    elementsSizeArr[i] = elementsArr[i].length();
                }
            }
        }

        printer(elementsSizeArr, linesArr);
    }

    private void printer(int[] elementsSizeArr, String []linesArr){
        for(String line : linesArr) {
            String[] elementsArr = line.split("\\|");

            for (int i = 0; i < elementsArr.length; i++) {
                System.out.print(elementsArr[i]);

                for(int j = 0; j<elementsSizeArr[i] - elementsArr[i].length(); j++){
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

}
