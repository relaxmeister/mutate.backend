package se.mutate.backend.model.userresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponse {


    private String username;
    private String claims; // admin kontra normal
    private String jwt;

}
