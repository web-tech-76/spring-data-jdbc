package jpa.app.services;

import jakarta.transaction.Transactional;
import jpa.app.entities.book_author_shop.Author;
import jpa.app.entities.book_author_shop.Book;
import jpa.app.repositories.AuthorRepository;
import jpa.app.shared.dto.AuthorData;
import jpa.app.shared.dto.Books;
import jpa.app.shared.form.AuthorForm;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository, BookService bookService) {
        this.authorRepository = authorRepository;
    }

    private static AuthorData transformToAuthorData(Author author) {
        AuthorData authorData = new AuthorData();
        authorData.setId(author.getId());
        authorData.setFirstName(author.getFirstName());
        authorData.setLastName(author.getLastName());
        List<Books> list = new ArrayList<>();
        for (Book book : author.getBooks()) {
            Books books = new Books(book.getId(), book.getBookName());
            list.add(books);
        }
        authorData.setBooks(list);
        return authorData;
    }

    private AuthorData getAuthorData(Author author) throws SQLException {

        Optional<Author> optionalAuthor = authorRepository.findById(author.getId());
        AuthorData authorData =null;

        if(optionalAuthor.isPresent()){
               authorData = (AuthorData) Stream.of(optionalAuthor.get()).map(AuthorService::transformToAuthorData);
         }

         return authorData;
    }

    public List<AuthorData> findAll() throws SQLException{
        List<Author> authors = authorRepository.findAll();
        List<AuthorData> authorDataList =
                authors.stream().map (AuthorService::transformToAuthorData).toList();
        return  authorDataList;
    }

    public AuthorData createNew(AuthorForm authorForm) throws SQLException{
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Author author = modelMapper.map(authorForm,Author.class);
        author = authorRepository.save(author);
        var authorData =  transformToAuthorData(author);
        return authorData;
    }

    public AuthorData findAuthorById(Long authorID) throws SQLException {
        Optional<Author> optionalAuthor = authorRepository.findById(authorID);
        AuthorData authorData =null;

        if(optionalAuthor.isPresent()){
            authorData = transformToAuthorData(optionalAuthor.get());
        }

        return authorData;

    }

    public AuthorData findAuthorByName(String firstName, String lastName ) throws SQLException{
        Optional<Author> optionalAuthor = authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);
        AuthorData authorData =null;

        if(optionalAuthor.isPresent()){
            authorData = authorData = transformToAuthorData(optionalAuthor.get());;
        }

        return authorData;

    }

}
