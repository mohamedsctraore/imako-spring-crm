package ci.imako.imakospringcrm.controllers;

import ci.imako.imakospringcrm.domain.Categorie;
import ci.imako.imakospringcrm.domain.Commande;
import ci.imako.imakospringcrm.domain.Contact;
import ci.imako.imakospringcrm.repositories.CommandeRepository;
import ci.imako.imakospringcrm.repositories.ContactRepository;
import ci.imako.imakospringcrm.services.IContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/home")
public class CommandeController {

    private CommandeRepository commandeRepository;
    private ContactRepository contactRepository;
    private IContactService contactService;

    @Autowired
    public CommandeController(IContactService contactService,
                              CommandeRepository commandeRepository,
                              ContactRepository contactRepository) {
        this.contactService = contactService;
        this.commandeRepository = commandeRepository;
        this.contactRepository = contactRepository;
    }

    @GetMapping("/newcommande/{id}")
    public String creerCommande(Model model, @PathVariable String id) {
        model.addAttribute("commande", new Commande());
        model.addAttribute("id_contact", id);
        log.debug("Affichage formulaire de création de commande");
        return "commande/newcommande";
    }

    @PostMapping("/newcommande/{id}")
    public String creerCommande(@Valid @ModelAttribute("commande") Commande commande,
                                @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return "commande/newcommande";
        }

        log.debug("ID recherché : " + id.toString());
        Optional<Contact> contact = contactRepository.findById(id);

        log.debug("ID du contact recupéré : " + contact.get().getId().toString());
        log.debug("NOM du contact recupéré : " + contact.get().getNom());
        log.debug("EMAIL du contact recupéré : " + contact.get().getEmail());
        log.debug("CATEGORIE du contact recupéré : " + contact.get().getCategorie().toString());

        commande.setContact(contact.get());

        log.debug(commande.getId().toString());
        log.debug(commande.getNoteCommande());
        log.debug(commande.getContact().getNom());
        log.debug(commande.getContact().getTelephone());

        commandeRepository.save(commande);

        String status = contactService.changeStatusContact(contact.get());

        log.debug(status);

        if (status.equals(Categorie.SUSPECT.name())) {
            contact.get().setCategorie(Categorie.SUSPECT);
        } else if (status.equals(Categorie.PROSPECT.name())) {
            contact.get().setCategorie(Categorie.PROSPECT);
        } else if (status.equals(Categorie.CLIENT.name())) {
            contact.get().setCategorie(Categorie.CLIENT);
        } else if (status.equals(Categorie.VIP.name())) {
            contact.get().setCategorie(Categorie.VIP);
        }

        log.debug(contact.get().getCategorie().name());

        contactRepository.save(contact.get());

        return "redirect:/home";
    }

    @GetMapping("/showcommande/{id}")
    public String voirCommande(@PathVariable Long id, Model model) {
        model.addAttribute("macommande", commandeRepository.findById(id));
        return "commande/showcommande";
    }

    @GetMapping("/listcommande/{id}")
    public String listCommande(@PathVariable Long id, Model model) {
        
        return "commande/listcommande";
    }
}
