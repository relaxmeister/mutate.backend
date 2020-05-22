package se.mutate.backend.model.jobdetail;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.mutate.backend.model.jobspecifics.JobSpecifics;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    private String field;

    @OneToOne(cascade = CascadeType.ALL)//(fetch = FetchType.LAZY) // skapade problem med att serializable blabla
    @JoinColumn(name = "jobspecifics_id", referencedColumnName = "id") //this explains that the o
    private JobSpecifics jobSpecifics;
}
