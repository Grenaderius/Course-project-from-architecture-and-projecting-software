package CommandExecutor;

import AuthorizationFacade.AuthorizationFacade;

public class ExitCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Виконуємо вихід із облікового запису...");
        AuthorizationFacade authorizationFacade = new AuthorizationFacade();
        authorizationFacade.inputDataForAuthorizationAndCheck();
    }
}
