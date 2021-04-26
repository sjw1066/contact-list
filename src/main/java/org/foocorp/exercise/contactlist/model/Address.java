package org.foocorp.exercise.contactlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

/**
 * Address entity
 */
@Entity
@Table(name = "addresses")
public class Address {

  @Id
  @GeneratedValue   // let db auto generate the PK
  @JsonIgnore       // don't render in JSON representation
  private Long id;

  @Column(name = "street")
  private String street;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "zipcode")
  private String zip;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Address address = (Address) o;
    return Objects.equals(id, address.id) &&
      Objects.equals(street, address.street) &&
      Objects.equals(city, address.city) &&
      Objects.equals(state, address.state) &&
      Objects.equals(zip, address.zip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, street, city, state, zip);
  }

  @Override
  public String toString() {
    return "Address{" +
      "id=" + id +
      ", street='" + street + '\'' +
      ", city='" + city + '\'' +
      ", state='" + state + '\'' +
      ", zip='" + zip + '\'' +
      '}';
  }
}
