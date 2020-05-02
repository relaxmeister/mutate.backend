package se.mutate.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.mutate.backend.model.fileobject.FileObject;

public interface DownloadReposity extends JpaRepository<FileObject, Long> {

  @Query("SELECT u FROM FileObject u WHERE LOWER(u.system) = LOWER(:os)") // är korrekt
  FileObject findBySystem(@Param("os") String os);
    //FileObject findById(String os);

//    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
//    public List<Person> find(@Param("lastName") String lastName);

    //FileObject findBySystem(String os); //funkar bra
}
