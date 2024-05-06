

public class Main {
    public static void main(String[] args) {
        View view = new View();
        SchoolController sc = new SchoolController();
        NotificationController notificationController = NotificationController.getInstance();

        // Register View as an observer to receive notifications
        notificationController.registerObserver(view);
        Controller controller = new Controller(view,sc,notificationController);
        controller.execute();
    }


}