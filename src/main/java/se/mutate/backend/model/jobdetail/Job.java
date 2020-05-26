package se.mutate.backend.model.jobdetail;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.mutate.backend.model.formdata.FormData;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    private String field;

    @Column(columnDefinition = "VARCHAR(max)")
    private String[] jobDescription;

    @Column
    private String[] doing;

    @Column(columnDefinition = "VARCHAR(max)")
    private String[] shouldHave;

    @Column
    private String[] bonus;

    //https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "job")
    private Set<FormData> formdata = new HashSet<>();
}
