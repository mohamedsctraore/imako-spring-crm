package ci.imako.imakospringcrm.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Les commandes prises par les @{@link Contact} de l'application
 */
@Entity
@Table(name = "commandes")
@Getter
@Setter
@NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String noteCommande;

    @ManyToOne
    private Contact contact;
}
