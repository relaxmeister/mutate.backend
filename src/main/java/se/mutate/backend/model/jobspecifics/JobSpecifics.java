package se.mutate.backend.model.jobspecifics;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSpecifics {

    int id;
    String role;
    String[] jobDescription;
    String[] doing;
    String[] shouldHave;
    String[] bonus;
}
