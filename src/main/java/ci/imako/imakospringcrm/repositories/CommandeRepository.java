package ci.imako.imakospringcrm.repositories;

import ci.imako.imakospringcrm.domain.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
