package se.mutate.backend.model.formdata;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormData {

    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String city;
    private String resume; // filnamn?
    private String coverletter; // fil
    private String reasoning;

}
