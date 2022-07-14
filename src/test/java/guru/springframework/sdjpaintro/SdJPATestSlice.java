package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.assertThat;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = "guru.springframework.sdjpaintro.bootstrap")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SdJPATestSlice {

    @Autowired
    BookRepository bookRepository;

    @Order(1)
    @Test
    @Commit
    void testBookRepositorySlice() {
        long count = bookRepository.count();

        System.out.println("Count is " + count);

        bookRepository.save(new Book("Title 3", "454545", "dsel", 1L));
        count = bookRepository.count();

        System.out.println("Count after save is " + count);
        assertThat(count).isGreaterThan(0);
    }

    @Order(2)
    @Test
    void testBookRepositorySliceYTransaction() {
        long count = bookRepository.count();

        System.out.println("Count is " + count);

        assertThat(count).isEqualTo(3);
    }
}
