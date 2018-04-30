package ci.imako.imakospringcrm.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

/**
 * Les contacts gérés par l'application
 */
@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 150)
    private String nom;
    @Column(nullable = false, length = 150)
    @Email
    private String email;
    @Column(nullable = false, length = 25, unique = true)
    private String telephone;

    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    private Categorie categorie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
    private List<Commande> commandes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
    private List<RendezVous> rendezVousList;
}
