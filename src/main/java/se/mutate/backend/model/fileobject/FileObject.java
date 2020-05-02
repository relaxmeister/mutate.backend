package se.mutate.backend.model.fileobject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FileObject {

    @Id
    @Column(name="id")
    private Long id;

    //@Column(name="os")
    private String system;

    @Lob
    @Column(name = "file", columnDefinition="BLOB")
    private byte[] file;
}
