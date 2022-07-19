package problem1;

public class RadioButton<T extends Boolean> extends Validators<T> {

  /**
   * RadioButton Class is a Validator that checks if the input is valid for a RadioButton field.
   * RadioButton takes a Boolean type input. A RadioButton is valid only if the input is not null.
   */
  public RadioButton() {
  }

  /**
   * Determines if input meets validator requirements
   *
   * @param input - input, as Boolean
   * @return if input is valid, stored as Boolean.
   */
  @Override
  public Boolean isValid(Boolean input) {
    return (input != null);
  }

<<<<<<< HEAD
  /**
   * hashCode method for generating hash code values for this RadioButton Validator
   *
   * @return hash code, as an Integer
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * equals method for comparing two objects
   *
   * @param obj as another Object instance
   * @return if instances are a match, as Boolean
   */
  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  /**
   * toString method for returning a String representation of this RadioButton Validator
   *
   * @return string representation, as a String.
   */
  @Override
  public String toString() {
    return "RadioButton{} " + super.toString();
  }
=======

>>>>>>> 53423236c5238adfc3fea57be220dd1e62e0a82f
}
