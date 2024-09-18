package AuthorizationFacade;

import UserStrategy.AgencyStrategy;
import UserStrategy.FirmStrategy;
import UserStrategy.StrategyChooser;

import java.util.Scanner;

public class AuthorizationFacade {
    //Внутрішні змінні, доступні тільки в межах пакету через модифікатор
    //доступу package
    final String firmAuthorizationLogin = "Firm";
    final String firmAuthorizationPass = "Firm";
    final String agencyAuthorizationLogin = "Agency";
    final String agencyAuthorizationPass = "Agency";
    String enteredLogin;
    String enteredPass;

    Scanner sc = new Scanner(System.in);

    public AuthorizationFacade() {}

    //Метод для вводу даних для авторизації
    public void inputDataForAuthorizationAndCheck() {
        //Блок виводу
        System.out.println("""
                Вітаємо в АІС представництва туристичної фірми.
                Будь ласка авторизуйтесь або введіть ключове слово "exit" для виходу з програми!
                Уведіть логін:""");


        enteredLogin = sc.nextLine();

        if(!enteredLogin.equalsIgnoreCase("exit")){
            System.out.println("Будь ласка уведіть пароль:");
            enteredPass = sc.nextLine();
        }

        checkEnteredData();
    }

    //Метод, що перевіряє результат перевірки даних на відповідність обліковим записам фірми та представництва
    public void checkEnteredData() {
        if(checkDataForFirm()){
            System.out.print("Вітаємо! Ви увійшли в акаунт фірми!");
            //виклик стратегії фірми
            StrategyChooser firmStrategy = new StrategyChooser(new FirmStrategy());
            firmStrategy.chooseActions();
        }else if(checkDataForAgency()){
            System.out.print("Вітаємо! Ви увійшли в акаунт представництва!");
            //виклик стратегії представництва
            StrategyChooser agencyStrategy = new StrategyChooser(new AgencyStrategy());
            agencyStrategy.chooseActions();
        }else if(checkDataForExit()){
            System.out.println("Дякую, що обрали наш додаток!");
        }else {
            System.err.println("Уведені вами дані є не правильними, спробуйте будь-ласка ще раз!");
            inputDataForAuthorizationAndCheck();
        }
    }

    //Методи, що виконують перевірку даних на відповідність
    private boolean checkDataForFirm() {
        return enteredLogin.equals(firmAuthorizationLogin) && enteredPass.equals(firmAuthorizationPass);
    }

    private boolean checkDataForAgency() {
        return enteredLogin.equals(agencyAuthorizationLogin) && enteredPass.equals(agencyAuthorizationPass);
    }

    private boolean checkDataForExit(){
        return enteredLogin.equalsIgnoreCase("exit") || enteredPass.equalsIgnoreCase("exit");
    }

}
