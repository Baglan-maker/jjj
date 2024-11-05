package Controller;
import Model.Book;
import Model.BookFactory;
import Model.BookService;

import java.util.List;

// BookController.java
public class BookController {
    private BookService bookService;

    public BookController() {
        this.bookService = BookService.getInstance();
    }

    public void addBook(String type, String id, String title, String author) {
        // Create an AddBookCommand with the new constructor
        AddBookCommand addCommand = new AddBookCommand(BookService.getInstance(), type, id, title, author);
        addCommand.execute();
    }

    public void undoAddBook() {
        AddBookCommand addCommand = new AddBookCommand(BookService.getInstance(), AddBookCommand.lastAddedBook);
        addCommand.undo(); // This will now reference the last added book
    }


    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}

