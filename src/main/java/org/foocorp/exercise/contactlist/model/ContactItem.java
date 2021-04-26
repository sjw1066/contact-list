package org.foocorp.exercise.contactlist.model;

import java.util.Objects;

/**
 * Call-list item DTO
 */
public class ContactItem {

  private Name name;
  private String phone;

  public ContactItem() {

  }

  public ContactItem(Name name, String phone) {
    this.name = name;
    this.phone = phone;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactItem that = (ContactItem) o;
    return Objects.equals(name, that.name) && Objects.equals(phone, that.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, phone);
  }

  @Override
  public String toString() {
    return "ContactItem{" +
      "name=" + name +
      ", phone='" + phone + '\'' +
      '}';
  }
}
