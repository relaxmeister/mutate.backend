package se.mutate.backend.model.formdata;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class FormData {

    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String city;
    private MultipartFile resume; // filnamn?
    private MultipartFile coverletter; // fil
    private String reasoning;

}
