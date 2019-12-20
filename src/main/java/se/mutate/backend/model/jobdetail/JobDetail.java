package se.mutate.backend.model.jobdetail;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDetail {

    //eventuellt DB-id
    int id;
    String role;
    String field;
}
