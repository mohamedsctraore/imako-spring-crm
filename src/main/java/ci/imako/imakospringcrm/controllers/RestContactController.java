package ci.imako.imakospringcrm.controllers;

import ci.imako.imakospringcrm.domain.Contact;
import ci.imako.imakospringcrm.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@Slf4j
public class RestContactController {

    private ContactRepository contactRepository;

    @Autowired
    public RestContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/contact/{id}/show")
    public Contact showContact(@PathVariable Long id) {
        log.debug("affichage du contact au format json");
        return contactRepository.findById(id).get();
    }

    @GetMapping("/contact/all")
    public List<Contact> listContact() {
        log.debug("affichage de tous les contacts au format json");
        return contactRepository.findAll();
    }
}
