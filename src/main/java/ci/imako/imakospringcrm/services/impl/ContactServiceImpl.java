package ci.imako.imakospringcrm.services.impl;

import ci.imako.imakospringcrm.domain.Categorie;
import ci.imako.imakospringcrm.domain.Commande;
import ci.imako.imakospringcrm.domain.Contact;
import ci.imako.imakospringcrm.domain.RendezVous;
import ci.imako.imakospringcrm.repositories.ContactRepository;
import ci.imako.imakospringcrm.services.IContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ContactServiceImpl implements IContactService {
    private ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public String changeStatusContact(Contact contact) {
        List<RendezVous> rendezVousList= contact.getRendezVousList();
        List<Commande> commandeList = contact.getCommandes();
        String status = Categorie.SUSPECT.toString();

        int tailleRdvList = rendezVousList.size();
        int tailleCommandeList = commandeList.size();

        if (tailleRdvList != 0 &&  tailleCommandeList == 0) {
            status = Categorie.PROSPECT.toString();
        } else if (tailleRdvList != 0 && tailleCommandeList > 0) {
            status = Categorie.CLIENT.toString();
            if (tailleCommandeList > 10) {
                status = Categorie.VIP.toString();
            }
        }

        return status;

    }

    @Override
    public Contact save(Contact contact) {
        return null;
    }

    @Override
    public Optional<Contact> readById(Long aLong) {
        return null;
    }

    @Override
    public List<Contact> readAll() {
        return null;
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public void delete(Contact contact) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
