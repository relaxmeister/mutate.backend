package se.mutate.backend.model.jobspecifics;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.mutate.backend.model.formdata.FormData;
import se.mutate.backend.model.jobdetail.JobDetail;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobSpecifics {
/**https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
skulle även kunna använda @mapsId annotation, då blir foreign key = primary key vilket skulle funka
        utmärkt*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToOne(mappedBy = "jobSpecifics")//(fetch = FetchType.LAZY) // skapade problem med att serializable blabla
    //@JoinColumn(name = "jobdetail_id") //this explains that the o
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "jobSpecifics")
    private JobDetail jobdetail;

    private String role;

    @Column(columnDefinition = "VARCHAR(max)")
    private String[] jobDescription;

    private String[] doing;

    @Column(columnDefinition = "VARCHAR(max)")
    private String[] shouldHave;

    private String[] bonus;

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "jobSpecifics")
    private Set<FormData> formdata = new HashSet<>();

}
