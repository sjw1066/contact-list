package org.foocorp.exercise.contactlist.data.api;

import org.foocorp.exercise.contactlist.model.ContactItem;

import java.util.List;

/**
 * Simple contact-list dao
 */
public interface ContactItemDao {

  /**
   * Return call-list.
   *
   * Returns list of <code>ContactItem</code> objects representing all comntacts with a home phone.
   * @return contact list
   */
  List<ContactItem> getCallList();
}
