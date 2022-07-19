package formsPackage;

import java.util.Objects;

/**
 * FreeText class is a Validators that will be used for freetext fields such as messages or
 * comments. To be valid, input must be no longer than the number of lines multiplied by the number
 * of characters allowed per line.
 */
public class FreeText<T extends String> extends Validators<T> {

  private Integer numLine;
  private Integer numCharacter;

  /**
   * Constructor for FreeText
   *
   * @param numLine      - the number of lines, as Integer
   * @param numCharacter - the number of characters allowed per line, as Integer
   */
  public FreeText(Integer numLine, Integer numCharacter) {
    this.numLine = numLine;
    this.numCharacter = numCharacter;
  }

  /**
   * getter for number of lines
   *
   * @return the number of lines, as Integer
   */
  public Integer getNumLine() {
    return numLine;
  }

  /**
   * getter for number of characters allowed per line
   *
   * @return the number of characters allowed per line, as Integer
   */
  public Integer getNumCharacter() {
    return numCharacter;
  }

  /**
   * Determines if input meets validator requirements
   *
   * @param input - input, as String
   * @return if input is valid, stored as Boolean.
   */
  @Override
  public Boolean isValid(String input) {
    return (input.length() <= (this.numLine * this.numCharacter));
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
    if (!(o instanceof FreeText)) {
      return false;
    }
    FreeText<?> freeText = (FreeText<?>) o;
    return Objects.equals(numLine, freeText.numLine) && Objects.equals(
        numCharacter, freeText.numCharacter);
  }

  /**
   * hashCode method for generating hash code values for this FreeText Validator
   *
   * @return hash code, as an Integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(numLine, numCharacter);
  }

  /**
   * toString method for returning a String representation of this Freetext Validator
   *
   * @return string representation, as a String.
   */
  @Override
  public String toString() {
    return "FreeText{" +
        "numLine=" + numLine +
        ", numCharacter=" + numCharacter +
        "} ";
  }
}
