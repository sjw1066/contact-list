package org.foocorp.exercise.contactlist.service.impl;

import org.foocorp.exercise.contactlist.data.api.ContactItemDao;
import org.foocorp.exercise.contactlist.data.api.ContactsDao;
import org.foocorp.exercise.contactlist.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DefaultContactsServiceTest {

  private DefaultContactsService svc = new DefaultContactsService();

  private ContactsDao mockContactsDao = mock(ContactsDao.class);
  private ContactItemDao mockContactItemDao = mock(ContactItemDao.class);

  @Before
  public void init() {
    svc.setContactsDao(mockContactsDao);
    svc.setContactItemDao(mockContactItemDao);
  }

  private Contact createContact() {
    return createContact(null, null, null);
  }

  private Contact createContact(Long id, Long aId, Long pId) {
    Contact c = new Contact();
    c.setId(id);
    c.setName(new Name("Bobbie", "Jo", "Smith"));

    Address a = new Address();
    a.setId(aId);
    a.setState("123 Johnson St.");
    a.setCity("Wheresville");
    a.setState("VA");
    a.setZip("12345");

    c.setAddress(a);

    Phone p = new Phone();
    p.setId(pId);
    p.setNumber("123-456-7890");
    p.setType(PhoneType.home);

    c.setPhone(Collections.singleton(p));

    return c;
  }

  @Test
  public void testGetAllContacts() {
    Contact c = createContact(1L, 2L, 3L);

    when(mockContactsDao.findAll()).thenReturn(Collections.singletonList(c));

    List<Contact> list = svc.getAllContacts();

    assertEquals(1, list.size());
    assertEquals(c, list.get(0));
    verify(mockContactsDao).findAll();
  }

  @Test
  public void testCreateContact() {
    Contact c0 = createContact();
    Contact c1 = createContact(1L, 2L, 3L);

    when(mockContactsDao.save(eq(c0))).thenReturn(c1);

    Contact contact = svc.createContact(c0);

    assertEquals(c1, contact);
    verify(mockContactsDao).save(eq(c0));
  }

  @Test
  public void testUpdateContactWithIdSet() {
    Contact c0 = createContact();
    c0.setId(5L);

    when(mockContactsDao.save(eq(c0))).thenReturn(c0);

    Contact contact = svc.updateContact(5L, c0);

    assertEquals(c0, contact);
    verify(mockContactsDao).save(eq(c0));
  }

  @Test
  public void testUpdateContactWithNoIdSet() {
    Contact c0 = createContact();
    Contact c1 = createContact();
    c1.setId(5L);

    when(mockContactsDao.save(eq(c1))).thenReturn(c1);

    Contact contact = svc.updateContact(5L, c0);

    assertEquals(c1, contact);
    verify(mockContactsDao).save(eq(c1));
  }

  @Test
  public void testGetContactOk() {
    Contact c = createContact(1L, 2L, 3L);
    when(mockContactsDao.findById(eq(1L))).thenReturn(Optional.of(c));

    Contact contact = svc.getContact(1L);

    assertEquals(c, contact);
    verify(mockContactsDao).findById(eq(1L));
  }

  @Test
  public void testGetContactNotFound() {
    when(mockContactsDao.findById(eq(1L))).thenReturn(Optional.empty());

    Contact contact = svc.getContact(1L);

    assertNull(contact);
    verify(mockContactsDao).findById(eq(1L));
  }

  @Test
  public void testDeleteByIdTotallyUselessUnitTestSolelyToProvideCodeCoverage() {
    assertTrue(svc.deleteContact(1L));
    verify(mockContactsDao).deleteById(eq(1L));
  }

  @Test
  public void testContactExistsOk() {
    when(mockContactsDao.existsById(eq(5L))).thenReturn(true);
    assertTrue(svc.contactExists(5L));
    verify(mockContactsDao).existsById(eq(5L));
  }

  @Test
  public void testContactExistsFail() {
    when(mockContactsDao.existsById(eq(5L))).thenReturn(false);
    assertFalse(svc.contactExists(5L));
    verify(mockContactsDao).existsById(eq(5L));
  }

  @Test
  public void testGetCallList() {
    ContactItem item = new ContactItem(new Name("Fu", "Man", "Chu"), "555-555-5555");
    when(mockContactItemDao.getCallList()).thenReturn(Collections.singletonList(item));
    List<ContactItem> list = svc.getCallList();
    assertEquals(1, list.size());
    assertEquals(item, list.get(0));
    verify(mockContactItemDao).getCallList();
  }
}
