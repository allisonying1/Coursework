package problem1;

import java.util.Objects;

public class Phone<T extends String> extends Validators<T> {

  /**
   * Phone class is a Validators. A valid phone number is a String that contains only digits and has
   * a specified length supplied by client code. The length must match exactly.
   */
  private Integer length;


  /**
   * Constructor for Phone
   *
   * @param length - length, as Integer
   */
  public Phone(Integer length) {
    this.length = length;
  }

  /**
   * getter for length
   *
   * @return length, as Integer
   */
  public Integer getLength() {
    return length;
  }

  /**
   * Determines if input meets validator requirements
   *
   * @param input@return if input is valid, stored as Boolean.
   */
  @Override
  public Boolean isValid(String input) {
    return (this.checkDigit(input) && this.checkLength(input));
  }

  /**
   * helper method, check contains only digits
   *
   * @param input - input, as String
   * @return if all input is digit, as Boolean
   */
  private Boolean checkDigit(String input) {
    int count = 0;
    for (int i = 0; i < input.length(); i++) {
      if (Character.isDigit(input.charAt(i))) {
        count++;
      }
    }
    return (input.length() == count);
  }

  /**
   * helper method, check the length
   *
   * @param input - input, as String
   * @return if the length matched, as Boolean
   */
  private Boolean checkLength(String input) {
    return (this.length == input.length());
  }


  /**
   * equals method for comparing two objects
   *
   * @param o as another Object instance
   * @return if instances are a match, as Boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Phone)) {
      return false;
    }
    Phone<?> phone = (Phone<?>) o;
    return Objects.equals(length, phone.length);
  }

  /**
   * hashCode method for generating hash code values for this Phone Validator
   *
   * @return hash code, as an Integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(length);
  }

  /**
   * toString method for returning a String representation of this Phone Validator
   *
   * @return string representation, as a String.
   */
  @Override
  public String toString() {
    return "Phone{" +
        "length=" + length +
        "} ";
  }
}
