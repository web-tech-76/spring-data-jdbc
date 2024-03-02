package jpa.app.web;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jpa.app.exceptions.AppException;
import jpa.app.services.AuthorService;
import jpa.app.shared.dto.AuthorData;
import jpa.app.shared.form.AuthorForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorData>> allAuthors() {
        try {
            var authorDataList = authorService.findAll();
            return new ResponseEntity<>(authorDataList, HttpStatus.OK);
        } catch (SQLException sqe) {
            throw new AppException("Sql Exception Occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @GetMapping("/find/{authorId}")
    public ResponseEntity<AuthorData> findAuthorById(@PathVariable Long authorId) {
        try {
            var authorData = authorService.findAuthorById(authorId);

            return new ResponseEntity<>(authorData, HttpStatus.OK);
        } catch (SQLException sqe) {
            throw new AppException("Sql Exception Occurred" + sqe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @GetMapping("/author")
    public ResponseEntity<AuthorData> findAuthorByName(@RequestBody @Valid AuthorForm authorForm) {
        try {
            var authorData = authorService.findAuthorByName(authorForm.getFirstName(), authorForm.getLastName());
            return new ResponseEntity<>(authorData, HttpStatus.OK);
        } catch (SQLException sqe) {
            throw new AppException("Sql Exception Occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/new")
    public ResponseEntity<AuthorData> newAuthor(@RequestBody @Valid AuthorForm authorForm) {
        try {
            var authorData = authorService.createNew(authorForm);
            return new ResponseEntity<>(authorData, HttpStatus.OK);
        } catch (SQLException sqe) {
            throw new AppException("Sql Exception Occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


}
