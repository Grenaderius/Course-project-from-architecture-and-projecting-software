import SingletonRunner.SingletonRunner;

public class Main {
    public static void main(String[] args) {
        //запуск БД через об'єкт класу SingletonRunner
        SingletonRunner runDB = SingletonRunner.getInstance();
        runDB.runDataBase();
    }
}