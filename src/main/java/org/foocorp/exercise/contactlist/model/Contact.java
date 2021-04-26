package org.foocorp.exercise.contactlist.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Contact entity
 */
@Entity
@Table(name = "contacts")
public class Contact {

  @Id
  @GeneratedValue   // let db auto generate the PK
  private Long id;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride( name = "first", column = @Column(name = "contact_first_name")),
    @AttributeOverride( name = "last", column = @Column(name = "contact_last_name")),
    @AttributeOverride( name = "middle", column = @Column(name = "contact_middle_name"))
  })
  private Name name;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact", orphanRemoval = true)
  private Set<Phone> phone;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Set<Phone> getPhone() {
    return phone;
  }

  public void setPhone(Set<Phone> phone) {
    this.phone = phone;
    if (phone != null && !phone.isEmpty()) {
      phone.forEach(p -> p.setContact(this));
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Contact contact = (Contact) o;
    return Objects.equals(id, contact.id) &&
      Objects.equals(name, contact.name) &&
      Objects.equals(address, contact.address) &&
      Objects.equals(phone, contact.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, address, phone);
  }

  @Override
  public String toString() {
    return "Contact{" +
      "id=" + id +
      ", name=" + name +
      ", address=" + address +
      ", phone=" + phone +
      '}';
  }
}
