package ci.imako.imakospringcrm.controllers;

import ci.imako.imakospringcrm.domain.Contact;
import ci.imako.imakospringcrm.exceptions.ResourceNotFoundException;
import ci.imako.imakospringcrm.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/contact/new")
    public Contact creerContact(@Valid @RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @PutMapping("/contact/{id}/edit")
    public Contact updateContact(@PathVariable Long id, @Valid @RequestBody Contact updatedContact) {
        Contact contact = contactRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Resource inexistante"));

        contact.setId(updatedContact.getId());
        contact.setNom(updatedContact.getNom());
        contact.setTelephone(updatedContact.getTelephone());
        contact.setEmail(updatedContact.getEmail());
        contact.setCategorie(updatedContact.getCategorie());
        contact.setRendezVousList(updatedContact.getRendezVousList());

        return contactRepository.save(contact);
    }

    @DeleteMapping("/contact/{id}/delete")
    public ResponseEntity<?> supprimerContact(Long id) {
        Contact contact = contactRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Resource inexistance"));
        contactRepository.delete(contact);

        return ResponseEntity.ok().build();
    }
}
