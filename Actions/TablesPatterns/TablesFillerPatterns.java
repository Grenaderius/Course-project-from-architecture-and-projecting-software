package Actions.TablesPatterns;

public class TablesFillerPatterns {
    private String pattern;

    public String returnGroupPattern(){
        pattern = "прізвище|ім'я|номер паспорта|стать|вік|діти|готель|тип|екскурсії|вантаж|прибуття|відбуття";
        return pattern;
    }

    public String returnPlanesPattern(){
        pattern = "Назва рейсу|тип літака|кількість пасажирських місць|кількість зайнятих місць|вага вантажу|об'єм вантажу|дата рейсу|назва файлу групи";
        return pattern;
    }

    public String returnStoragePattern(){
        pattern = "кільіксть місць|вага вантажу|кількість пасажирських літаків|кільіксть вантажних літаків|вид вантажу|дата";
        return pattern;
    }

}
