package Controller;


import Model.BookService;

public class RemoveBookCommand implements Command {
    private BookService bookService;
    private String bookId;

    public RemoveBookCommand(BookService bookService, String bookId) {
        this.bookService = bookService;
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        bookService.removeBook(bookId);
        bookService.setLastCommand(this);
    }

    @Override
    public void undo() {
        // Optionally, store the removed book and re-add it
    }
}
