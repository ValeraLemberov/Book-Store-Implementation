package intro.bookservice.repository.book;

import intro.bookservice.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query("FROM Book b INNER JOIN FETCH b.categories bc WHERE bc.id = :categoryId")
    List<Book> findAllByCategoryId(Long categoryId);
}
