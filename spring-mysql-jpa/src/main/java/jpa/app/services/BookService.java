package jpa.app.services;

import jakarta.transaction.Transactional;
import jpa.app.entities.book_author_shop.Author;
import jpa.app.entities.book_author_shop.Book;
import jpa.app.exceptions.AppException;
import jpa.app.repositories.AuthorRepository;
import jpa.app.repositories.BookRepository;
import jpa.app.shared.dto.Authors;
import jpa.app.shared.dto.BookData;
import jpa.app.shared.form.BookForm;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private List<Authors> getAuthorsList(Book book) {
        return book
                .getAuthors()
                .stream()
                .map(author ->
                        new Authors(author.getId(), author.getFirstName(), author.getLastName())
                ).toList();
    }

    private BookData transFormToBookData(Book book) {
        var bookData = new BookData(book.getId(), book.getBookName(), getAuthorsList(book));
        return bookData;
    }

    public List<BookData> finaAll() {

        var bookList = bookRepository.findAll();
        var bookDataList = bookList
                .stream()
                .map(this::transFormToBookData).toList();
        return bookDataList;
    }


    public BookData findBookById(Long bookId) {
        var book = bookRepository.findById(bookId).get();
        return new BookData(book.getId(), book.getBookName(), getAuthorsList(book));
    }


    public List<BookData> findBooksByAuthorName(String firstName, String lastName, Boolean like) {

        Optional<Author> optionalAuthor = null;

        if (!like)
            optionalAuthor = authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);
        else
            optionalAuthor = authorRepository.findAuthorByQuery(firstName, lastName);

        var bookList = bookRepository.findBooksByAuthors(optionalAuthor.get());

        return bookList.stream().map(
                book -> new BookData(book.getId(), book.getBookName(), getAuthorsList(book))
        ).toList();
    }


    public BookData addNewBook(BookForm bookForm) throws SQLException, Exception {

        var authorList = Arrays
                .stream(bookForm.getAuthors())
                .mapToObj(authorId -> authorRepository.findById(authorId).get()).toList();

        var book = new Book();
        book.setBookName(bookForm.getBookName());
        book.setAuthors(authorList);
        book = bookRepository.save(book);

        var bookData = new BookData();
        bookData.setId(book.getId());
        bookData.setBookName(book.getBookName());
        bookData.setAuthors(getAuthorsList(book));
        return bookData;

    }

    public void deleteBook(Long bookId) throws AppException {
         bookRepository
                .findById(bookId)
                .ifPresentOrElse(book -> {
                    bookRepository.delete(book);
                }, () -> {
                    throw new AppException("", 500);
                });
    }

}
