package Service;

import Model.Book;

public interface Observer {
    void update(Book book);
}