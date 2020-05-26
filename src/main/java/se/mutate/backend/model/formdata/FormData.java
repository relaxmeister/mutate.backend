package se.mutate.backend.model.formdata;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;
import se.mutate.backend.model.jobdetail.Job;
//import se.mutate.backend.model.jobspecifics.JobSpecifics;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) //nu skiter den i cv/pb som inte finns i denna POJO
@Entity
public class FormData {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = MIN_LENGTH, max = MAX_LENGTH)
    private String name;

    @NotNull(message = "CANT LEAVE THIS BLANK ROFL")
    @Size(min = MIN_LENGTH, max = MAX_LENGTH)
    private String lastname;

    @NotNull
    private String phone;

    @NotNull
    @Size(min = MIN_LENGTH, max = MAX_LENGTH)
    private String city;
    //optional atm

    @Column(columnDefinition = "VARCHAR(max)")
    private String reasoning;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = MIN_LENGTH, max = MAX_LENGTH)
    private String role;
    //DENNA ÄR NU FEL


    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

}
