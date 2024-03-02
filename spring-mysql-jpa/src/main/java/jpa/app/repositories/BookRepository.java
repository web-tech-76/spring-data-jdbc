package jpa.app.repositories;

import jpa.app.entities.book_author_shop.Author;
import jpa.app.entities.book_author_shop.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor {

    List<Book> findBooksByAuthors(Author author);


}
