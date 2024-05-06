import java.util.ArrayList;
import java.util.List;

public class NotificationController implements Observable {
    private List<Observer> observers;
    private static NotificationController instance; // Singleton instance

    public NotificationController() {
        observers = new ArrayList<>();
    }


    public static synchronized NotificationController getInstance() {
        if (instance == null) {
            instance = new NotificationController();
        }
        return instance;
    }

    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
