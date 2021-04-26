package org.foocorp.exercise.contactlist.data.api;

import org.foocorp.exercise.contactlist.model.Contact;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring-data CRUD repo for <code>Contact</code> entities
 */
public interface ContactsDao extends CrudRepository<Contact, Long> {

}
