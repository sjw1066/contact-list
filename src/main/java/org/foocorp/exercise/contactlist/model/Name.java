package org.foocorp.exercise.contactlist.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Embeddable Name entity
 */
@Embeddable
public class Name {

  private String first;
  private String last;
  private String middle;

  // for Jackson and JPA
  public Name() { }

  public Name(String first, String middle, String last) {
    this.first = first;
    this.middle= middle;
    this.last = last;
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }

  public String getMiddle() {
    return middle;
  }

  public void setMiddle(String middle) {
    this.middle = middle;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Name name = (Name) o;
    return Objects.equals(first, name.first) &&
      Objects.equals(last, name.last) &&
      Objects.equals(middle, name.middle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, last, middle);
  }

  @Override
  public String toString() {
    return "Name{" +
      "first='" + first + '\'' +
      ", last='" + last + '\'' +
      ", middle='" + middle + '\'' +
      '}';
  }
}
