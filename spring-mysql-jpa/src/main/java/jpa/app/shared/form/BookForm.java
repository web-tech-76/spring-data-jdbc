package jpa.app.shared.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jpa.app.shared.dto.AuthorData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookForm {

    @NotNull(message = "Book Name shouldn't be Null")
    @NotBlank(message = "Book Name shouldn't be empty or blank")
    private String bookName;
    private long[] authors;
}
