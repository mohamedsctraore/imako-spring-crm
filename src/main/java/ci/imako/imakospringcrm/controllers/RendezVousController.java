package ci.imako.imakospringcrm.controllers;

import ci.imako.imakospringcrm.domain.Categorie;
import ci.imako.imakospringcrm.domain.Contact;
import ci.imako.imakospringcrm.domain.RendezVous;
import ci.imako.imakospringcrm.repositories.ContactRepository;
import ci.imako.imakospringcrm.repositories.RendezVousRepository;
import ci.imako.imakospringcrm.services.IContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/rendezvous")
@Slf4j
public class RendezVousController {

    private RendezVousRepository rendezVousRepository;
    private ContactRepository contactRepository;
    private IContactService contactService;

    @Autowired
    public RendezVousController(IContactService contactService,
                                ContactRepository contactRepository,
                                RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
        this.contactRepository = contactRepository;
        this.contactService = contactService;
    }

    @GetMapping("/newrendezvous")
    public String creerRendezvous(Model model) {
        model.addAttribute("rendezvous", new RendezVous());
        return "rendezvous/newrendezvous";
    }

    @PostMapping("/newrendezvous/{id}")
    public String creerRendezVous(@Valid @ModelAttribute("rendezvous") RendezVous rendezVous,
                                  @PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        String status = contactService.changeStatusContact(contact.get());

        log.debug(status);

        if (status.equals(Categorie.SUSPECT.toString())) {
            contact.get().setCategorie(Categorie.SUSPECT);
        } else if (status.equals(Categorie.PROSPECT.toString())) {
            contact.get().setCategorie(Categorie.PROSPECT);
        } else if (status.equals(Categorie.CLIENT.toString())) {
            contact.get().setCategorie(Categorie.CLIENT);
        } else {
            contact.get().setCategorie(Categorie.VIP);
        }

        rendezVous.setContact(contact.get());

        log.debug(contact.get().getId().toString());
        log.debug(rendezVous.getId().toString());
        log.debug(rendezVous.getNoteRendezVous());
        log.debug(rendezVous.getDateRendezVous().toString());

        rendezVousRepository.save(rendezVous);

        log.debug("Enregistrement effectué");

        return "redirect:/home";
    }

    @GetMapping("/showrendezvous/{id}")
    public String voirRendezvous(@PathVariable Long id, Model model) {
        model.addAttribute("monrdv", rendezVousRepository.findById(id));
        return "rendezvous/showrendezvous";
    }

    @GetMapping("/listrendezvous/{id}")
    public String listRendezvous(@PathVariable Long id, Model model) {

        return "commande/listcommande";
    }
}
