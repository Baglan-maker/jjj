package Model;

// BookFactory.java Factory pattern
public class BookFactory {
    public static Book createBook(String type, String id, String title, String author) {
        if (type.equalsIgnoreCase("Science")) {
            return new ScienceBook(id, title, author);
        } else if (type.equalsIgnoreCase("Literature")) {
            return new LiteratureBook(id, title, author);
        } else {
            return new Book(id, title, author);
        }
    }
}

// Different types of books extending the Book class
class ScienceBook extends Book {
    public ScienceBook(String id, String title, String author) {
        super(id, title, author);
    }
}

class LiteratureBook extends Book {
    public LiteratureBook(String id, String title, String author) {
        super(id, title, author);
    }
}
