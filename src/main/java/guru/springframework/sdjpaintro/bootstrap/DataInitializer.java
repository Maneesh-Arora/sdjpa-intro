package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(bookRepository.count() > 0)
        {
            bookRepository.deleteAll();
        }
        initData();

    }

    private void initData() {
        Book b1 = new Book("Title1","1234","RandomHouse", 1L);
        System.out.println("ID before Save" + b1.getId());
        Book savedBook = bookRepository.save(b1);
        System.out.println("ID after  Save" + b1.getId());
        Book b2 = new Book("Title2","5678","RandomHouse1", 2L);
        Book savedBookb2 = bookRepository.save(b2);

        bookRepository.findAll().forEach(book -> {
            System.out.println("Book ID" + book.getId());
            System.out.println("Book Title" + book.getTitle());
        });
    }
}
