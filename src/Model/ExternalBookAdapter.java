package Model;

// ExternalBook.java (Simulating an external API's book format)
class ExternalBook {
    private String uniqueCode;
    private String bookName;
    private String bookAuthor;

    public ExternalBook(String uniqueCode, String bookName, String bookAuthor) {
        this.uniqueCode = uniqueCode;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    public String getUniqueCode() { return uniqueCode; }
    public String getBookName() { return bookName; }
    public String getBookAuthor() { return bookAuthor; }
}

// ExternalBookAdapter.java
public class ExternalBookAdapter extends Book {
    public ExternalBookAdapter(ExternalBook externalBook) {
        super(externalBook.getUniqueCode(), externalBook.getBookName(), externalBook.getBookAuthor());
    }
}

