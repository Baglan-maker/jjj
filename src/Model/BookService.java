package Model;

// BookService.java (Singleton)
import java.util.ArrayList;
import java.util.List;

import Controller.Command;
import Service.Observer;

public class BookService {
    private static BookService instance;
    private List<Book> books;
    private Command lastCommand;


    private BookService() {
        books = new ArrayList<>();
    }

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
        notifyObservers(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }
    public void removeBook(String id) {
        books.removeIf(book -> book.getId().equals(id));
    }

    public void undoLastAction() {
        if (lastCommand != null) {
            lastCommand.undo();
        }
    }

    public void setLastCommand(Command command) {
        this.lastCommand = command;
    }

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(Book book) {
        for (Observer observer : observers) {
            observer.update(book);
        }
    }
}
