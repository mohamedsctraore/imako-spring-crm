package ci.imako.imakospringcrm.bootstrap;

import ci.imako.imakospringcrm.domain.Categorie;
import ci.imako.imakospringcrm.domain.Contact;
import ci.imako.imakospringcrm.domain.Role;
import ci.imako.imakospringcrm.domain.User;
import ci.imako.imakospringcrm.repositories.CommandeRepository;
import ci.imako.imakospringcrm.repositories.ContactRepository;
import ci.imako.imakospringcrm.repositories.RendezVousRepository;
import ci.imako.imakospringcrm.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BootstrapCRM implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final CommandeRepository commandeRepository;
    private final RendezVousRepository rendezVousRepository;
    private final ContactRepository contactRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public BootstrapCRM(PasswordEncoder passwordEncoder,
                        UserRepository userRepository,
                        CommandeRepository commandeRepository,
                        RendezVousRepository rendezVousRepository,
                        ContactRepository contactRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.commandeRepository = commandeRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        userRepository.saveAll(listUsers());
        contactRepository.saveAll(listContacts());

        log.debug("Chargement des données de l'application");
    }

    private List<User> listUsers() {
        List<User> users = new ArrayList<>();

        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setNom("commercial");
        roles.add(role);

        User commercial1 = new User();
        commercial1.setNom("Traore Mohamed");
        commercial1.setEmail("mohamed.s.c.traore@gmail.com");
        commercial1.setPassword(passwordEncoder.encode("mangeonsbien"));
        commercial1.setRoles(roles);

        User commercial2 = new User();
        commercial2.setNom("Cisse Moctar");
        commercial2.setEmail("moctar.cisse@gmail.com");
        commercial2.setPassword(passwordEncoder.encode("mangeonsbien"));
        commercial2.setRoles(roles);

        users.add(commercial1);
        users.add(commercial2);

        return users;
    }

    private List<Contact> listContacts() {
        List<Contact> contacts = new ArrayList<>();

        Contact contact1 = new Contact();
        contact1.setNom("Frank Dusbosq");
        contact1.setEmail("frank.dubosq@gmail.com");
        contact1.setTelephone("08-75-35-94");
        contact1.setCategorie(Categorie.SUSPECT);

        Contact contact2 = new Contact();
        contact2.setNom("Antoine Kombouare");
        contact2.setEmail("antoine.kombouare@gmail.com");
        contact2.setTelephone("08-35-75-94");
        contact2.setCategorie(Categorie.PROSPECT);

        contacts.add(contact1);
        contacts.add(contact2);

        return contacts;
    }
}
