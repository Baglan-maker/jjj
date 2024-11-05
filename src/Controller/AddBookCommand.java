package Controller;

import Model.Book;
import Model.BookFactory;
import Model.BookService;
import java.util.Stack;




// AddBookCommand.java


public class AddBookCommand implements Command {
    private BookService bookService;
    private Book book;
    private static Stack<Book> history = new Stack<>(); // for undo operation
    public static Book lastAddedBook;


    public AddBookCommand(BookService bookService, Book book) {
        this.bookService = bookService;
        this.book = book;
        lastAddedBook = book; // Keep track of the last added book
    }

    public AddBookCommand(BookService bookService, String type, String id, String title, String author) {
        this.bookService = bookService;
        this.book = BookFactory.createBook(type, id, title, author);
        lastAddedBook = this.book; // Keep track of the last added book
    }



    @Override
    public void execute() {
        bookService.addBook(book);
    }
    @Override
    public void undo() {
        bookService.removeBook(book.getId());
    }


    }


