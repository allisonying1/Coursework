package problem1;

import java.util.Objects;

public class Field<T> implements IField<T> {

  private final String label;
  private T value;
  private final Boolean required;
  private final Validators<T> validator;

  /**
   * A Generic Field class
   *
   * @param label     associated with the form field, stored as a String.
   * @param required  indicating whether the value field must be completed, stored as a Boolean.
   * @param validator that will perform the input validation, stored as a Validator Object. Fields
   *                  might also have a value input captured by the GUI, stored as a Generic T
   *                  Type.
   */

  public Field(String label, Boolean required, Validators<T> validator) {
    this.label = label;
    this.value = null;
    this.required = required;
    this.validator = validator;
  }

  /**
   * Updates the Field's value, if the input is valid
   *
   * @param input as a Generic T data type
   * @throws GenericFieldException if input is not valid
   */
  @Override
  public void updateValue(T input) throws GenericFieldException {
    // pass input T into the isValid method
    if (this.validator.isValid(input)) {
      // change value if validation passes
      this.value = input;
    }
    // else throw an error:
    else {
      throw new GenericFieldException("Invalid input. Value has not been updated.");
    }
  }

  /**
   * Checks if Field is ready to be submitted
   *
   * @return if all requirements are met, as Boolean
   */
  @Override
  public boolean isFilled() {
    // if field value is required:
    if (this.required) {
      // return true if value is no longer null:
      if (this.value != null) {
        return Boolean.TRUE;
      }
      // return false if value is still null:
      else {
        return Boolean.FALSE;
      }
    }
    // if field value is not required:
    else {
      return Boolean.TRUE;
    }
  }

  /**
   * getter method for returning the Label associated with the form field
   *
   * @return label, stored as a String.
   */
  public String getLabel() {
    return this.label;
  }

  /**
   * getter method for returning the input value captured by the GUI
   *
   * @return value, stored as either a String or a Boolean.
   */
  public T getValue() {
    return this.value;
  }

  /**
   * getter method for returning whether a particular field needs to be completed
   *
   * @return required, stored as a Boolean.
   */
  public Boolean getRequired() {
    return this.required;
  }

  /**
   * getter method for returning the validator that will perform input validation.
   *
   * @return validator, stored as a Validators Object.
   */
  public Validators<T> getValidator() {
    return this.validator;
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
    if (!(o instanceof Field)) {
      return false;
    }
    Field<?> field = (Field<?>) o;
    return Objects.equals(getLabel(), field.getLabel()) && Objects.equals(
        getValue(), field.getValue()) && Objects.equals(getRequired(), field.getRequired())
        && Objects.equals(getValidator(), field.getValidator());
  }

  /**
   * hashCode method for generating hash code values for a generic Field class
   *
   * @return hash code, as an Integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(getLabel(), getValue(), getRequired(), getValidator());
  }

  /**
   * toString method for returning a String representation of this generic Field class
   *
   * @return string representation, as a String.
   */
  @Override
  public String toString() {
    return "Field{" +
        "label='" + label + '\'' +
        ", value=" + value +
        ", required=" + required +
        ", validator=" + validator +
        '}';
  }
}
