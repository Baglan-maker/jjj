package Service;

import Model.Book;

// Observer.java (interface)

// NotificationService.java (Observer)
public class NotificationService implements Observer {
    private static NotificationService instance;



    private NotificationService() {}

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    @Override
    public void update(Book book) {
        System.out.println("Notification: New book added - " + book.getTitle());
    }
}

