package Controller;

import Model.Book;
import Model.BookFactory;
import Model.BookService;
import Service.NotificationService;

import java.util.List;

// LibraryFacade.java
public class LibraryFacade {
    private BookService bookService;
    private NotificationService notificationService;

    public LibraryFacade() {
        bookService = BookService.getInstance();
        notificationService = NotificationService.getInstance();
        bookService.addObserver(notificationService);
    }

    public void addBook(String type, String id, String title, String author) {
        Book book = BookFactory.createBook(type, id, title, author);
        AddBookCommand addCommand = new AddBookCommand(bookService, book);
        addCommand.execute();
    }

    public void removeBook(String id) {
        RemoveBookCommand removeCommand = new RemoveBookCommand(bookService, id);
        removeCommand.execute();
    }

    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
}
