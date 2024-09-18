package SingletonRunner;

import AuthorizationFacade.AuthorizationFacade;

//Клас-реалізатор патерну сінглтон для доступу до бази даних лише з 1-го об'єкту класу
public class SingletonRunner {
    private static SingletonRunner instance;

    //приватний конструктор для забезпечення неможливості створення об'єкта за межами класу
    private SingletonRunner() {  }

    //метод-перевірка, чи об'єкт уже створений, через нього і створюється об'єкт, якщо його ще немає
    public static SingletonRunner getInstance() {
        if(instance == null){
            instance = new SingletonRunner();
        }
        return instance;
    }

    //Виклик методів авторизації
    public void runDataBase() {
        AuthorizationFacade authorizationFacade = new AuthorizationFacade();
        authorizationFacade.inputDataForAuthorizationAndCheck();
    }
}
