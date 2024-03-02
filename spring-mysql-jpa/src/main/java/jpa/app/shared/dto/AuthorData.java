package jpa.app.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorData {

    private Long id;

    private String firstName;

    private String lastName;

    private List<Books> books;


}
