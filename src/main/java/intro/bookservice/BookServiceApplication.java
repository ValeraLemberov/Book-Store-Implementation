package intro.bookservice;

import intro.bookservice.model.Book;
import intro.bookservice.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookServiceApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("Sample Book 1");
            book.setAuthor("Author C");
            book.setIsbn("9789876543212");
            book.setPrice(BigDecimal.valueOf(25.99));
            book.setDescription("Another sample book description.");
            book.setCoverImage("http://example.com/cover2.jpg");

            bookService.save(book);

            System.out.println(bookService.findAll());
        };
    }
}
