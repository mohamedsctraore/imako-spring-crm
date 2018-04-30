package ci.imako.imakospringcrm.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Les rendez-vous pris par les @{@link User} de l'application avec les @{@link Contact}
 */
@Entity
@Table(name = "rendezvous")
@Getter
@Setter
@NoArgsConstructor
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String dateRendezVous;

    @Lob
    @Column(nullable = false)
    private String noteRendezVous;

    @ManyToOne
    private Contact contact;
}
