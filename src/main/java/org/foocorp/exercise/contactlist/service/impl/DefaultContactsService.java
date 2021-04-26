package org.foocorp.exercise.contactlist.service.impl;

import org.foocorp.exercise.contactlist.data.api.ContactItemDao;
import org.foocorp.exercise.contactlist.data.api.ContactsDao;
import org.foocorp.exercise.contactlist.model.Contact;
import org.foocorp.exercise.contactlist.model.ContactItem;
import org.foocorp.exercise.contactlist.service.api.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Default <code>ContactsService</code> implementation
 */
@Service
public class DefaultContactsService implements ContactsService {

  private ContactsDao contactsDao;
  private ContactItemDao contactItemDao;

  /**
   * @see ContactsService#getAllContacts()
   */
  @Override
  public List<Contact> getAllContacts() {
    List<Contact> result = new ArrayList<>();
    Iterable<Contact> it = contactsDao.findAll();
    it.forEach(result::add);
    return result;
  }

  /**
   * @see ContactsService#createContact(Contact)
   */
  @Override
  public Contact createContact(Contact contact) {
    return contactsDao.save(contact);
  }

  /**
   * @see ContactsService#updateContact(Long, Contact)
   */
  @Override
  public Contact updateContact(Long id, Contact contact) {
    contact.setId(id);
    return contactsDao.save(contact);
  }

  /**
   * @see ContactsService#getContact(Long)
   */
  @Override
  public Contact getContact(Long id) {
    return contactsDao.findById(id).orElse(null);
  }

  /**
   * @see ContactsService#deleteContact(Long)
   */
  @Override
  public Boolean deleteContact(Long id) {
    contactsDao.deleteById(id);
    return true;
  }

  /**
   * @see ContactsService#getCallList()
   */
  @Override
  public List<ContactItem> getCallList() {
    return contactItemDao.getCallList();
  }

  /**
   * @see ContactsService#contactExists(Long)
   */
  @Override
  public boolean contactExists(Long id) {
    return contactsDao.existsById(id);
  }

  /**
   * Set ContactsDao
   * @param contactsDao dao
   */
  @Autowired
  public void setContactsDao(ContactsDao contactsDao) {
    this.contactsDao = contactsDao;
  }

  /**
   * See ContactItemDao
   * @param contactItemDao dao
   */
  @Autowired
  public void setContactItemDao(ContactItemDao contactItemDao) {
    this.contactItemDao = contactItemDao;
  }
}
