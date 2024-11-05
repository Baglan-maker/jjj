package View;


import Controller.LibraryFacade;
import Model.Book;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class BookHandler implements HttpHandler {
    private LibraryFacade libraryFacade;

    public BookHandler(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response;

        if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            String query = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String[] params = query.split("&");
            String type = params[0].split("=")[1];
            String id = params[1].split("=")[1];
            String title = params[2].split("=")[1];
            String author = params[3].split("=")[1];

            // Call the controller to add the book
            libraryFacade.addBook(type, id, title, author);

            response = "<html><head><title>Library</title></head><body><p>Book added successfully!</p><a href=\"/books\">View Books</a></body></html>";
        } else if ("DELETE".equalsIgnoreCase(exchange.getRequestMethod())) {
            String query = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String[] params = query.split("&");
            String id = params[0].split("=")[1];

            // Call the controller to remove the book
            libraryFacade.removeBook(id);

            response = "<html><head><title>Library</title></head><body><p>Book removed successfully!</p><a href=\"/books\">View Books</a></body></html>";
        } else {
            StringBuilder html = new StringBuilder();
            html.append("<html><head><title>Library</title></head><body>")
                    .append("<h1>List of Books</h1><ul>");

            // Display all books
            for (Book book : libraryFacade.getBooks()) {
                html.append("<li>").append(book.getTitle()).append(" by ").append(book.getAuthor())
                        .append(" <form method=\"POST\" action=\"/books\"><input type=\"hidden\" name=\"id\" value=\"")
                        .append(book.getId()).append("\"><input type=\"submit\" value=\"Remove\"></form></li>");
            }

            html.append("</ul>")
                    .append("<h2>Add a Book</h2>")
                    .append("<form method=\"POST\" action=\"/books\">")
                    .append("Type: <input type=\"text\" name=\"type\"><br>")
                    .append("ID: <input type=\"text\" name=\"id\"><br>")
                    .append("Title: <input type=\"text\" name=\"title\"><br>")
                    .append("Author: <input type=\"text\" name=\"author\"><br>")
                    .append("<input type=\"submit\" value=\"Add\">")
                    .append("</form></body></html>");

            response = html.toString();
        }

        // Send the response
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}
