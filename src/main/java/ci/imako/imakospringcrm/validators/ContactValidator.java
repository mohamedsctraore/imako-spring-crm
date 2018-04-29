package ci.imako.imakospringcrm.validators;

import ci.imako.imakospringcrm.domain.Contact;
import ci.imako.imakospringcrm.domain.User;
import ci.imako.imakospringcrm.repositories.ContactRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ContactValidator implements Validator{

    private ContactRepository contactRepository;

    public ContactValidator(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Contact.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        Contact contact = (Contact) o;
        String email = contact.getEmail();
        String telephone = contact.getTelephone();

        Optional<Contact> contactParEmail = contactRepository.findContactByEmail(email);
        Optional<Contact> contactParTelephone = contactRepository.findContactByTelephone(telephone);

        if (contactParEmail.isPresent()) {
            errors.rejectValue("email",
                    "email.exists",
                    new Object[] {email}, "Email " + email + " déjà utilisé.");
        }

        if (contactParTelephone.isPresent()) {
            errors.rejectValue("telephone",
                    "telephone.exists",
                    new Object[] {email}, "Telephone " + telephone + " déjà utilisé.");
        }
    }
}
