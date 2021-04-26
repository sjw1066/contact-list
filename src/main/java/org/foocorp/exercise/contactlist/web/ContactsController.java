package org.foocorp.exercise.contactlist.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.foocorp.exercise.contactlist.model.Contact;
import org.foocorp.exercise.contactlist.model.ContactItem;
import org.foocorp.exercise.contactlist.service.api.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contacts")
@Api(value = "Contacts and Contact List controller")
public class ContactsController {

  private ContactsService contactsService;

  @GetMapping
  @ApiOperation(value = "Get all contacts", produces = "application/json")
  @ApiResponses({
    @ApiResponse(code = 200, message = "OK", response = List.class)
  })
  public ResponseEntity<List<Contact>> getAllContacts() {
    return ResponseEntity.ok(contactsService.getAllContacts());
  }

  @PostMapping
  @ApiOperation(value = "Create new contact", produces = "application/json", consumes = "application/json")
  @ApiResponses({
    @ApiResponse(code = 201, message = "Contact created", response = Contact.class)
  })
  public ResponseEntity<Contact> createContact(@RequestBody Contact newContact) {
    newContact.setId(null);
    return ResponseEntity.status(HttpStatus.CREATED).body(contactsService.createContact(newContact));
  }

  @PutMapping("{id}")
  @ApiResponses({
    @ApiResponse(code = 200, message = "OK", response = Contact.class),
    @ApiResponse(code = 400, message = "Bad request; object id does not match path param"),
    @ApiResponse(code = 404, message = "Contact with {id} not found")
  })
  @ApiOperation(value = "Update existing contact", produces = "application/json", consumes = "application/json")
  public ResponseEntity<Contact> updateContact(@PathVariable("id") Long id, @RequestBody Contact contact) {
    if (contactsService.contactExists(id)) {
      if (contact.getId() != null && !id.equals(contact.getId())) {
        return ResponseEntity.badRequest().build();
      } else {
        return ResponseEntity.ok(contactsService.updateContact(id, contact));
      }
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("{id}")
  @ApiResponses({
    @ApiResponse(code = 200, message = "OK", response = Contact.class),
    @ApiResponse(code = 404, message = "Contact with {id} not found")
  })
  @ApiOperation(value = "Get specific contact by id", produces = "application/json")
  public ResponseEntity<Contact> getContact(@PathVariable("id") Long id) {
    if (contactsService.contactExists(id)) {
      return ResponseEntity.ok(contactsService.getContact(id));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("{id}")
  @ApiOperation(value = "Delete specific contact by id", produces = "application/json")
  @ApiResponses({
    @ApiResponse(code = 200, message = "OK", response = Contact.class),
    @ApiResponse(code = 404, message = "Contact with {id} not found")
  })
  public ResponseEntity<Boolean> deleteContact(@PathVariable("id") Long id) {
    if (contactsService.contactExists(id)) {
      return ResponseEntity.ok(contactsService.deleteContact(id));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("call-list")
  @ApiOperation(value = "Get call list", produces = "application/json")
  @ApiResponses({
    @ApiResponse(code = 200, message = "OK", response = List.class)
  })
  public ResponseEntity<List<ContactItem>> getCallList() {
    return ResponseEntity.ok(contactsService.getCallList());
  }

  @Autowired
  public void setContactsService(ContactsService svc) {
    this.contactsService = svc;
  }

}
