package ci.imako.imakospringcrm.services;

import ci.imako.imakospringcrm.domain.Contact;

public interface IContactService extends IService<Contact, Long> {
    String changeStatusContact(Contact contact);
}
