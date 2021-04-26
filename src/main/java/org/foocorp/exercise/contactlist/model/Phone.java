package org.foocorp.exercise.contactlist.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

/**
 * Phone entity
 */
@Entity
@Table(name = "phones")
public class Phone {

  @Id
  @GeneratedValue   // let db auto generate the PK
  @JsonIgnore       // don't render in JSON representation
  private Long id;

  @ManyToOne
  @JoinColumn(name = "contact_id", nullable = false)
  @JsonBackReference
  private Contact contact;

  @Column(name = "number")
  private String number;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private PhoneType type;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public PhoneType getType() {
    return type;
  }

  public void setType(PhoneType type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Phone phone = (Phone) o;
    return Objects.equals(id, phone.id) &&
      Objects.equals(number, phone.number) &&
      type == phone.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, number, type);
  }

  @Override
  public String toString() {
    return "Phone{" +
      "id=" + id +
      ", number='" + number + '\'' +
      ", type=" + type +
      '}';
  }
}
