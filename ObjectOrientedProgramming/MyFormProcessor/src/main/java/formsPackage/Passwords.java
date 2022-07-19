package formsPackage;

import java.util.Objects;

public class Passwords<T extends String> extends Validators<T> {

  private final Integer minLength;
  private final Integer maxLength;
  private final Integer minLowercase;
  private final Integer minUppercase;
  private final Integer minDigits;

  /**
   * A class for Password Validators to check password requirements (inclusive).
   *
   * @param minLength    Minimum Acceptable Length requirement, stored as an Integer.
   * @param maxLength    Maximum Acceptable Length requirement, stored as an Integer.
   * @param minLowercase Minimum Lowercase Letter requirement, stored as an Integer.
   * @param minUppercase Minimum Uppercase Letter requirement, stored as an Integer.
   * @param minDigits    Minimum Digit Character requirement, stored as an Integer.
   */

  public Passwords(Integer minLength, Integer maxLength, Integer minLowercase,
      Integer minUppercase, Integer minDigits) {
    this.minLength = minLength;
    this.maxLength = maxLength;
    this.minLowercase = minLowercase;
    this.minUppercase = minUppercase;
    this.minDigits = minDigits;
  }

  /**
   * Determines if a String input meets validator requirements
   *
   * @param input, as a String data type.
   * @return if input is valid, stored as Boolean.
   */
  @Override
  public Boolean isValid(String input) {
    return (this.checkLength(input) && this.checkChar(input) && !this.containsSpace(input));
  }

  /**
   * Private helper method that checks if a string meets the MIN and MAX length requirement
   *
   * @param input, as a String data type.
   * @return if MIN and MAX are met, as Boolean.
   */
  private Boolean checkLength(String input) {
    return ((input.length() >= this.minLength) && (input.length() <= this.maxLength));
  }

  /**
   * Private helper method that checks if a string meets the minimum character requirements
   *
   * @param input, as a String data type.
   * @return if minimum requirements are met, as Boolean.
   */
  private Boolean checkChar(String input) {
    int countLower = 0;   // count number of lowercase characters
    int countUpper = 0;   // count number of uppercase characters
    int countDigit = 0;   // count number of digit characters

    // iterate through string:
    for (int i = 0; i < input.length(); ++i) {
      // increase counter for lowercase char:
      if (Character.isLowerCase(input.charAt(i))) {
        countLower++;
      }
      // increase counter for uppercase char:
      else if (Character.isUpperCase(input.charAt(i))) {
        countUpper++;
      }

      // increase counter for digit char:
      else if (Character.isDigit(input.charAt(i))) {
        countDigit++;
      }
    }
    // verify that all thresholds are met:
    return (countLower >= this.minLowercase && countUpper >= this.minUppercase &&
        countDigit >= this.minDigits);
  }

  /**
   * Private helper method that checks if a string contains any space characters
   *
   * @param input, as a String data type.
   * @return if there are spaces, as Boolean
   */
  private Boolean containsSpace(String input) {
    return (input.contains(" "));
  }

  /**
   * getter method for Minimum Length requirement
   *
   * @return minLength as an Integer
   */
  public Integer getMinLength() {
    return minLength;
  }

  /**
   * getter method for Maximum Length requirement
   *
   * @return maxLength as an Integer
   */
  public Integer getMaxLength() {
    return maxLength;
  }

  /**
   * getter method for Minimum Lowercase Char requirements
   *
   * @return minLowerCase as an Integer
   */
  public Integer getMinLowercase() {
    return minLowercase;
  }

  /**
   * getter method for Minimum Uppercase requirements
   *
   * @return minUpperCase as an Integer
   */
  public Integer getMinUppercase() {
    return minUppercase;
  }

  /**
   * getter method for Minimum Digits requirements
   *
   * @return minDigits as an Integer
   */
  public Integer getMinDigits() {
    return minDigits;
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
    if (!(o instanceof Passwords)) {
      return false;
    }
    Passwords<?> passwords = (Passwords<?>) o;
    return Objects.equals(getMinLength(), passwords.getMinLength())
        && Objects.equals(getMaxLength(), passwords.getMaxLength())
        && Objects.equals(getMinLowercase(), passwords.getMinLowercase())
        && Objects.equals(getMinUppercase(), passwords.getMinUppercase())
        && Objects.equals(getMinDigits(), passwords.getMinDigits());
  }

  /**
   * hashCode method for generating hash code values for this Password Validator
   *
   * @return hash code, as an Integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(getMinLength(), getMaxLength(), getMinLowercase(), getMinUppercase(),
        getMinDigits());
  }

  /**
   * toString method for returning a String representation of this Password Validator
   *
   * @return string representation, as a String.
   */
  @Override
  public String toString() {
    return "Passwords{" +
        "minLength=" + minLength +
        ", maxLength=" + maxLength +
        ", minLowercase=" + minLowercase +
        ", minUppercase=" + minUppercase +
        ", minDigits=" + minDigits +
        "} ";
  }
}
