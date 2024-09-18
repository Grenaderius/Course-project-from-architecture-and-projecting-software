package UserStrategy;

public class StrategyChooser {
    //об'єкт інтерфейсу, що реалізуватиме якийсь із класі-стратегій
    private UserStrategy userStrategy;

    //Вибір класу-стратегії для об'єкта
    public StrategyChooser(UserStrategy userStrategy){
        this.userStrategy = userStrategy;
    }

    public void chooseActions(){
        //це викликає метод із реалізацією залежно від переданої конструктором
        userStrategy.orderAction("Передавати ключові слова");
    }
}
