package ci.imako.imakospringcrm.services;

import ci.imako.imakospringcrm.domain.Contact;

import java.util.List;
import java.util.Map;

public interface IContactService extends IService<Contact, Long> {
    String changeStatusContact(Contact contact);
    Map<String, Object> reports(Long id);
}
