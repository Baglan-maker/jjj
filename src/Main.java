import Controller.LibraryFacade;
import View.BookHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        LibraryFacade libraryFacade = new LibraryFacade();
        server.createContext("/books", new BookHandler(libraryFacade));
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server is running on http://localhost:8000/books");
    }
}
