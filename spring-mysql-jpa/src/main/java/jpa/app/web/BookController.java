package jpa.app.web;


import jakarta.validation.Valid;
import jpa.app.services.BookService;
import jpa.app.shared.dto.BookData;
import jpa.app.shared.form.BookForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/new")
    public ResponseEntity<BookData> newBook(@RequestBody @Valid BookForm bookForm) {
        var response = new ResponseEntity<>(new BookData(), HttpStatus.OK);
        try {
            var bookData = bookService.addNewBook(bookForm);
            response = new ResponseEntity<>(bookData, HttpStatus.OK);
        } catch (Exception sqe) {
            System.out.println("Exception  Occurred= " + sqe);
        }
        return response;

    }

    @GetMapping("/all")
    public ResponseEntity<List<BookData>> findAll() {
        ResponseEntity<List<BookData>> response = new ResponseEntity<>(null, HttpStatus.OK);
        try {
            var bookList = bookService.finaAll();
            response = new ResponseEntity<List<BookData>>(bookList, HttpStatus.OK);
        } catch (Exception sqe) {
            System.out.println("Exception  Occurred= " + sqe);
        }
        return response;

    }

    @GetMapping("/find")
    public ResponseEntity<BookData> findBookById(@RequestBody Long bookID) {
        ResponseEntity<BookData> response = new ResponseEntity<>(null, HttpStatus.OK);
        try {
            var bookData = bookService.findBookById(bookID);
            response = new ResponseEntity<BookData>(bookData, HttpStatus.OK);
        } catch (Exception sqe) {
            System.out.println("Exception  Occurred= " + sqe);
        }
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestBody Long bookID) {
        ResponseEntity<String> response = new ResponseEntity<>(null, HttpStatus.OK);
        try {
            bookService.deleteBook(bookID);
            response = new ResponseEntity<>("Book with Id - " + bookID + " Deleted ... ", HttpStatus.OK);
        } catch (Exception sqe) {
            System.out.println("Exception  Occurred= " + sqe);
        }
        return response;

    }


}
