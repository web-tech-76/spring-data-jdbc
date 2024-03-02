package jpa.app.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookData {

    private Long id;

    private String bookName;

    private List<Authors> authors;
}
