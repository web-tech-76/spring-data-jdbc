package jpa.app.shared.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorForm {


    @NotNull(message = "First Name shouldn't be Null")
    @NotBlank(message = "First Name shouldn't be empty or blank")
    private String firstName;


    @NotNull(message = "Last Name shouldn't be Null")
    @NotBlank(message = "Last Name shouldn't be empty or blank")
    private String lastName;



}
