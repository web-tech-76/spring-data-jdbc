package jpa.app.repositories;

import jpa.app.entities.book_author_shop.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor {

    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = """
                select  Author 
                from Author as author 
                where author.firstName like %:firstLike% 
                and   author.lastName like %:lastLike%   
            """)
    Optional<Author> findAuthorByQuery(String firstLike, String lastLike);


}
