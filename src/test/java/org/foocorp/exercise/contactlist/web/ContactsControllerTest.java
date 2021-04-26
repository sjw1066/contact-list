package org.foocorp.exercise.contactlist.web;

import org.foocorp.exercise.contactlist.model.Contact;
import org.foocorp.exercise.contactlist.model.ContactItem;
import org.foocorp.exercise.contactlist.service.api.ContactsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ContactsControllerTest {

  private ContactsController controller = new ContactsController();
  private ContactsService mockService = mock(ContactsService.class);

  @Before
  public void init() {
    controller.setContactsService(mockService);
  }

  @Test
  public void testGetAllContacts() {
    when(mockService.getAllContacts()).thenReturn(Collections.emptyList());

    ResponseEntity<List<Contact>> response = controller.getAllContacts();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(0, response.getBody().size());
    verify(mockService).getAllContacts();
  }

  @Test
  public void testCreateContact() {
    Contact mockContact = mock(Contact.class);
    when(mockService.createContact(eq(mockContact))).thenReturn(mockContact);

    ResponseEntity<Contact> response = controller.createContact(mockContact);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(mockContact, response.getBody());
    verify(mockContact).setId(eq(null));
    verify(mockService).createContact(eq(mockContact));
  }

  @Test
  public void testUpdateContactHappyPathWithId() {
    Contact contact = new Contact();
    contact.setId(5L);


    when(mockService.updateContact(eq(5L), eq(contact))).thenReturn(contact);
    when(mockService.contactExists(eq(5L))).thenReturn(true);

    ResponseEntity<Contact> response = controller.updateContact(5L, contact);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(contact, response.getBody());
    verify(mockService).contactExists(eq(5L));
    verify(mockService).updateContact(eq(5L), eq(contact));
  }

  @Test
  public void testUpdateContactHappyPathNoId() {
    Contact contact = new Contact();

    when(mockService.updateContact(eq(5L), eq(contact))).thenReturn(contact);
    when(mockService.contactExists(eq(5L))).thenReturn(true);

    ResponseEntity<Contact> response = controller.updateContact(5L, contact);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(contact, response.getBody());
    verify(mockService).contactExists(eq(5L));
    verify(mockService).updateContact(eq(5L), eq(contact));
  }

  @Test
  public void testUpdateContactNotFound() {
    Contact contact = new Contact();

    when(mockService.updateContact(eq(5L), eq(contact))).thenReturn(contact);
    when(mockService.contactExists(eq(5L))).thenReturn(false);

    ResponseEntity<Contact> response = controller.updateContact(5L, contact);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(mockService).contactExists(eq(5L));
    verify(mockService, never()).updateContact(eq(5L), eq(contact));
  }

  @Test
  public void testUpdateContactIdMismatch() {
    Contact contact = new Contact();
    contact.setId(11L);

    when(mockService.updateContact(eq(5L), eq(contact))).thenReturn(contact);
    when(mockService.contactExists(eq(5L))).thenReturn(true);

    ResponseEntity<Contact> response = controller.updateContact(5L, contact);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    verify(mockService).contactExists(eq(5L));
    verify(mockService, never()).updateContact(eq(5L), eq(contact));
  }

  @Test
  public void testGetContactOk() {
    Contact contact = new Contact();

    when(mockService.getContact(eq(5L))).thenReturn(contact);
    when(mockService.contactExists(eq(5L))).thenReturn(true);

    ResponseEntity<Contact> response = controller.getContact(5L);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(contact, response.getBody());
    verify(mockService).contactExists(eq(5L));
    verify(mockService).getContact(eq(5L));
  }

  @Test
  public void testGetContactNotFound() {
    when(mockService.contactExists(eq(5L))).thenReturn(false);

    ResponseEntity<Contact> response = controller.getContact(5L);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(mockService).contactExists(eq(5L));
    verify(mockService, never()).getContact(eq(5L));
  }

  @Test
  public void testDeleteContactOk() {
    when(mockService.deleteContact(eq(5L))).thenReturn(true);
    when(mockService.contactExists(eq(5L))).thenReturn(true);

    ResponseEntity<Boolean> response = controller.deleteContact(5L);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody());
    verify(mockService).contactExists(eq(5L));
    verify(mockService).deleteContact(eq(5L));
  }

  @Test
  public void testDeleteContactNotFound() {
    when(mockService.contactExists(eq(5L))).thenReturn(false);

    ResponseEntity<Boolean> response = controller.deleteContact(5L);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(mockService).contactExists(eq(5L));
    verify(mockService, never()).deleteContact(eq(5L));
  }

  @Test
  public void testGetCallList() {
    when(mockService.getCallList()).thenReturn(Collections.emptyList());

    ResponseEntity<List<ContactItem>> response = controller.getCallList();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody().isEmpty());
    verify(mockService).getCallList();

  }
}
