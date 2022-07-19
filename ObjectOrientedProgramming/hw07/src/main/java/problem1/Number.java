package problem1;

import java.util.Objects;

/**
 * Number Class is a Validator that checks if the input is valid for a Number field.
 * A Number takes a String type input.
 * A Number is valid if the text entered by a user can be converted to the appropriate numeric
 * format. Client code should indicate minimum and maximum values (inclusive) and the maximum
 * number of decimal places allowed.
 */
public class Number<T extends String> extends Validators<T> {
  private Integer maxNumberOfDigits;
  private Integer minNumberOfDigits;
  private Integer maxNumberOfDecimals;
  private Integer minNumberOfDecimals;

  /**
   * Constructor for Client to Validate a Number input that is a decimal value
   *
   * @param maxNumberOfDigits   the client sets the max number of digits allowed before decimal, as
   *                            Integer
   * @param minNumberOfDigits   the client sets the min number of digits allowed before the decimal,
   *                            as Integer
   * @param maxNumberOfDecimals the client sets the max number of decimals after the decimal, as
   *                            Integer
   */
  public Number(Integer maxNumberOfDigits, Integer minNumberOfDigits, Integer maxNumberOfDecimals,
      Integer minNumberOfDecimals) throws ClientInputException {

    if (this.checkForNegative(maxNumberOfDigits) && this.checkForNegative(minNumberOfDigits)
        && this.checkForNegative(maxNumberOfDecimals) && this.checkForNegative(minNumberOfDecimals)
        && this.checkMinMax(maxNumberOfDigits, minNumberOfDigits)
        && this.checkMinMax(maxNumberOfDecimals, minNumberOfDecimals)) {
      this.maxNumberOfDigits = maxNumberOfDigits;
      this.minNumberOfDigits = minNumberOfDigits;
      this.maxNumberOfDecimals = maxNumberOfDecimals;
      this.minNumberOfDecimals = minNumberOfDecimals;
    } else {
      throw new ClientInputException("There was an invalid client value found!");
    }
  }

  /**
   * Constructor for Client to Validate a Number input that is not a decimal value, as Integer
   *
   * @param maxNumberOfDigits the client sets the max number of digits allowed, as Integer
   * @param minNumberOfDigits the client sets the min number of digits allowed, as Integer The max
   *                          number of decimals is set to zero, assuming the number is an integer
   */
  public Number(Integer maxNumberOfDigits, Integer minNumberOfDigits) throws ClientInputException {
    if (this.checkForNegative(maxNumberOfDigits)
        && this.checkForNegative(minNumberOfDigits)
        && this.checkMinMax(maxNumberOfDigits, minNumberOfDigits)) {
      this.maxNumberOfDigits = maxNumberOfDigits;
      this.minNumberOfDigits = minNumberOfDigits;
      this.maxNumberOfDecimals = 0; // the Number is an Integer
      this.minNumberOfDecimals = 0; // the Number is an Integer
    } else {
      throw new ClientInputException("There was an invalid client value found!");
    }
  }

  /**
   * Override the isValid method for a Numbers Field
   *
   * @param input, as either a String or Boolean data type.
   * @return true or false if the input is validated
   */
  @Override
  public Boolean isValid(String input) {
    if (this.requiredDecimal()) {
      return (isValidDecimal(input));
    }
    return (isValidNonDecimal(input));
  }

  /**
   * Checks the Client Input for Negative values
   *
   * @param clientInput the value given from the client
   * @return return true or false
   */
  public Boolean checkForNegative(Integer clientInput) {
    return (!(clientInput < 0));
  }

  /**
   * Checks the Client Input for Negative values
   *
   * @param clientInputMax the max input value given from the client
   * @param clientInputMin the min input given from the client
   * @return return true or false
   */
  public Boolean checkMinMax(Integer clientInputMax, Integer clientInputMin) {
    return (!(clientInputMax < clientInputMin));
  }

  /**
   * Checks if the field is requiring a decimal value
   *
   * @return true or false
   */
  public Boolean requiredDecimal() {
    return (!this.maxNumberOfDecimals.equals(0) && !this.minNumberOfDecimals.equals(0));
  }

  /**
   * Checks if the input has a decimal
   *
   * @param input the string value from user
   * @return true or false
   */
  public Boolean hasDecimal(String input) {
    return (input.contains("."));
  }

  /**
   * Checks if the input is within the correct length parameters
   *
   * @param input the string value from user
   * @return true or false
   */
  public Boolean checkLength(String input) {
    if (!this.requiredDecimal()) {
      return (input.length() <= this.maxNumberOfDigits && input.length() >= this.minNumberOfDigits);
    }
    int integerPlaces = input.indexOf('.'); // the number of digits before decimal
    return (integerPlaces <= this.maxNumberOfDigits && integerPlaces >= this.minNumberOfDigits);
  }

  /**
   * Checks if the input has the correct amount of digits after the decimal
   *
   * @param input the string value from the user
   * @return true or false
   */
  public Boolean checkDecimals(String input) {
    int integerPlaces = input.indexOf('.'); // the number of digits before decimal
    int decimalPlaces =
        input.length() - integerPlaces - 1; // the number of digits following decimal

    return (decimalPlaces <= this.maxNumberOfDecimals && decimalPlaces >= this.minNumberOfDecimals);
  }

  /**
   * Checks if the Decimal value is valid
   *
   * @param input the string value from the user
   * @return true or false
   */
  public Boolean isValidDecimal(String input) {
    return (this.requiredDecimal() && this.hasDecimal(input) && this.checkDecimals(input)
        && this.checkLength(input));
  }

  /**
   * Checks if the non-decimal value is valid
   *
   * @param input the string value from the user
   * @return true or false
   */
  public Boolean isValidNonDecimal(String input) {
    return (!this.requiredDecimal() && !this.hasDecimal(input) && this.checkLength(input));
  }


  /**
   * Get the max number of digits allowed
   *
   * @return the integer value for max
   */
  public Integer getMaxNumberOfDigits() {
    return maxNumberOfDigits;
  }

  /**
   * Get the min number of digits allowed
   *
   * @return the integer value for min
   */
  public Integer getMinNumberOfDigits() {
    return minNumberOfDigits;
  }

  /**
   * Get the max number of digits after decimal
   *
   * @return the integer value for max
   */
  public Integer getMaxNumberOfDecimals() {
    return maxNumberOfDecimals;
  }

  /**
   * Get the min number of digits after decimals
   *
   * @return the integer value for min
   */
  public Integer getMinNumberOfDecimals() {
    return minNumberOfDecimals;
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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Number<?> number = (Number<?>) o;
    return Objects.equals(maxNumberOfDigits, number.maxNumberOfDigits)
        && Objects.equals(minNumberOfDigits, number.minNumberOfDigits)
        && Objects.equals(maxNumberOfDecimals, number.maxNumberOfDecimals)
        && Objects.equals(minNumberOfDecimals, number.minNumberOfDecimals);
  }

  /**
   * hashCode method for generating hash code values for this Number Validator
   *
   * @return hash code, as an Integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), maxNumberOfDigits, minNumberOfDigits, maxNumberOfDecimals,
        minNumberOfDecimals);
  }

  /**
   * toString method for returning a String representation of this Number Validator
   *
   * @return string representation, as a String.
   */
  @Override
  public String toString() {
    return "Number{" +
        "maxNumberOfDigits=" + maxNumberOfDigits +
        ", minNumberOfDigits=" + minNumberOfDigits +
        ", maxNumberOfDecimals=" + maxNumberOfDecimals +
        ", minNumberOfDecimals=" + minNumberOfDecimals +
        "} ";
  }
}
