package ci.imako.imakospringcrm.controllers;

import ci.imako.imakospringcrm.domain.Commande;
import ci.imako.imakospringcrm.domain.Contact;
import ci.imako.imakospringcrm.repositories.CommandeRepository;
import ci.imako.imakospringcrm.repositories.ContactRepository;
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
@RequestMapping("/commande")
public class CommandeController {

    private CommandeRepository commandeRepository;
    private ContactRepository contactRepository;

    @Autowired
    public CommandeController(CommandeRepository commandeRepository,
                              ContactRepository contactRepository) {
        this.commandeRepository = commandeRepository;
        this.contactRepository = contactRepository;
    }

    @GetMapping("/newcommande")
    public String creerCommande(Model model) {
        model.addAttribute("commande", new Commande());
        log.debug("Affichage formulaire de création de commande");
        return "commande/newcommande";
    }

    @PostMapping("/newcommande/{id}")
    public String creerCommande(@Valid @ModelAttribute("commande") Commande commande,
                                @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return "commande/newcommande";
        }

        Optional<Contact> contact = contactRepository.findById(id);

        commande.setContact(contact.get());

        log.debug(commande.getId().toString());
        log.debug(commande.getNoteCommande());
        log.debug(commande.getContact().getNom());
        log.debug(commande.getContact().getTelephone());

        commandeRepository.save(commande);

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
