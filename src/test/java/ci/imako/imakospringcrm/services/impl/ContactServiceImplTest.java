package ci.imako.imakospringcrm.services.impl;

import ci.imako.imakospringcrm.domain.Categorie;
import ci.imako.imakospringcrm.domain.Commande;
import ci.imako.imakospringcrm.domain.Contact;
import ci.imako.imakospringcrm.domain.RendezVous;
import ci.imako.imakospringcrm.repositories.CommandeRepository;
import ci.imako.imakospringcrm.repositories.ContactRepository;
import ci.imako.imakospringcrm.repositories.RendezVousRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ContactServiceImplTest {

    ContactServiceImpl contactService;
    @Mock
    ContactRepository contactRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        contactService = new ContactServiceImpl(contactRepository);
    }

    @Test
    public void changeStatusContact() throws Exception {
        // given
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setNom("Contact 1");
        contact.setEmail("email1");
        contact.setTelephone("telephone1");
        contact.setCategorie(Categorie.SUSPECT);

        List<RendezVous> rendezVousList = new ArrayList<>();
        RendezVous rendezVous = new RendezVous();
        rendezVous.setId(2L);
        rendezVous.setDateRendezVous(new Date());
        rendezVous.setNoteRendezVous("monrendezvous");
        rendezVous.setContact(contact);

        rendezVousList.add(rendezVous);

        List<Commande> commandeList = new ArrayList<>();
        Commande commande = new Commande();
        commande.setId(3L);
        commande.setNoteCommande("macommande");
        commande.setContact(contact);

        // when
        for (int i = 0; i < 10; i++) {
            Commande c = new Commande();
            commandeList.add(c);
        }

        contact.setRendezVousList(rendezVousList);
        contact.setCommandes(commandeList);

        String status = contactService.changeStatusContact(contact);

        // then

        Assert.assertEquals(Categorie.VIP.name(), status);

    }

}