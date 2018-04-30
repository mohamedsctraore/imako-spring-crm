package ci.imako.imakospringcrm.controllers;

import ci.imako.imakospringcrm.domain.Contact;
import ci.imako.imakospringcrm.repositories.ContactRepository;
import ci.imako.imakospringcrm.services.IContactService;
import ci.imako.imakospringcrm.validators.ContactValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/home")
@Slf4j
@Transactional
public class ContactController {

    private ContactRepository contactRepository;
    private ContactValidator contactValidator;
    private IContactService contactService;

    public ContactController(IContactService contactService,
                             ContactRepository contactRepository,
                             ContactValidator validator) {
        this.contactRepository = contactRepository;
        this.contactValidator = validator;
        this.contactService = contactService;
    }

    @GetMapping("/listContact")
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());
        return "home";
    }

    @GetMapping("/contact/show/{id}")
    public String voirContact(Model model, @PathVariable Long id) {
        Contact contact = contactRepository.findById(id).get();
        log.debug(contact.getId().toString());
        log.debug(contact.getNom());
        log.debug(contact.getEmail());
        log.debug(contact.getTelephone());

        model.addAttribute("contact", contact);
        return "contact/showcontact";
    }

    @PostMapping("/newcontact")
    public String creerContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) {
        contactValidator.validate(contact, result);
        if (result.hasErrors()) {
            return "contact/newcontact";
        }

        contactRepository.save(contact);

        log.debug(contact.getId().toString());
        log.debug(contact.getNom());
        log.debug(contact.getEmail());
        log.debug(contact.getTelephone());
        return "redirect:/home";
    }

    @GetMapping("/newcontact")
    public String creerContact(Model model) {
        model.addAttribute("contact", new Contact());
        log.debug("Affichage de la page création de contact");
        return "contact/newcontact";
    }

    @GetMapping("/contact/edit/{id}")
    public String modifierContact(@PathVariable Long id, Model model) {
        model.addAttribute("editcontact", contactRepository.findById(id));
        return "/contact/editcontact";
    }

    @PostMapping("/editcontact")
    @Transactional
    public String modifierContact(@Valid @ModelAttribute("editcontact") Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return "contact/editcontact";
        }

        contactRepository.update(contact.getId(), contact.getEmail(), contact.getNom(), contact.getTelephone());
        //contactRepository.save(contact);

        log.debug(contact.getNom());
        log.debug(contact.getEmail());
        log.debug(contact.getTelephone());
        log.debug("Modification du contact reussi");

        return "redirect:/home";
    }

    @GetMapping("/contact/delete/{id}")
    public String supprimerContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
        log.debug("Contact supprimé");
        return "redirect:/home";
    }

    @GetMapping("/contact/newpdf/{id}")
    public String genererReport(@PathVariable Long id) {
        return "";
    }
}
