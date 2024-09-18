package Actions.AgencyActions.DateActions.DateComparison;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//тут завжди порівнюється чи перша дата до другої, тобто чи перша була раніше ніж друга і чи перша була пізніше ніж друга
public class DateComparison {

    public boolean compareDataBefore(String firstDateString, String secondDateString){
        LocalDate firstDate = makeDataFromString(firstDateString);
        LocalDate secondDate = makeDataFromString(secondDateString);

        if(firstDate != null && secondDate != null){
            return firstDate.isBefore(secondDate);
        }else return false;
    }

    public boolean compareDataAfter(String firstDateString, String secondDateString){
        LocalDate firstDate = makeDataFromString(firstDateString);
        LocalDate secondDate = makeDataFromString(secondDateString);

        if(firstDate != null && secondDate != null){
            return firstDate.isAfter(secondDate);
        }else return false;
    }

    private LocalDate makeDataFromString(String dateString){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = null;

        try {
            date = LocalDate.parse(dateString, formater);
        }catch (DateTimeParseException e){
            System.err.println("Не правильний формат дати - \"" + dateString + "\"");
        }

        return date;
    }
}


