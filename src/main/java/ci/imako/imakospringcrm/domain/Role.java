package ci.imako.imakospringcrm.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Les roles des differents utilisateurs de l'application
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 150, unique = true)
    private String nom;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
