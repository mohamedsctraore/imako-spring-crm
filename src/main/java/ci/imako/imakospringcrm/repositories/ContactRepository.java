package ci.imako.imakospringcrm.repositories;

import ci.imako.imakospringcrm.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findContactByEmail(String email);
    Optional<Contact> findContactByTelephone(String telephone);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Contact c SET c.email = :email, c.nom = :nom, c.telephone = :telephone WHERE c.id = :id")
    void update(@Param("id") Long id, @Param("email") String email, @Param("nom") String nom,
               @Param("telephone") String telephone);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Contact c SET c.categorie = :categorie WHERE c.id = :id" )
    void updateContact(@Param("id") Long id, @Param("categorie") String categorie);
}
