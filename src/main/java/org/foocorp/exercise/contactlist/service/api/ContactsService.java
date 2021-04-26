package org.foocorp.exercise.contactlist.service.api;

import org.foocorp.exercise.contactlist.model.Contact;
import org.foocorp.exercise.contactlist.model.ContactItem;

import java.util.List;

/**
 * Contacts servicer contract
 */
public interface ContactsService {

  /**
   * Get all contacts
   * @return <code>Contact</code> entities as List
   */
  List<Contact> getAllContacts();

  /**
   * Create a new contact
   * @param contact to-be contact
   * @return contact after insert, id-assignment
   */
  Contact createContact(Contact contact);

  /**
   * Update existing contact
   * @param id contact id
   * @param contact new contact info
   * @return contact after merge
   */
  Contact updateContact(Long id, Contact contact);

  /**
   * Get existing contact, specified by id
   * @param id contact id
   * @return contact for id
   */
  Contact getContact(Long id);

  /**
   * Delete existing id
   * @param id contact id
   * @return <code>true</code>, if successful
   */
  Boolean deleteContact(Long id);

  /**
   * Determine whether Contact with id exists
   * @param id contact id
   * @return <code>true</code> if a Contact with <code>id</code> exists; <code>false</code>, otherwise
   */
  boolean contactExists(Long id);

  /**
   * Get call list
   * @return list of <code>ContactItem</code> objects, one for each Contact with a home phone
   */
  List<ContactItem> getCallList();
}
