package problem1;

/**
 * CheckBox class is a Validators. CheckBox input is a Boolean indicating whether the button is
 * checked. null is a valid input for a checkbox.
 */
public class CheckBox<T extends Boolean> extends Validators<T> {

  /**
   * empty constructor for CheckBox
   */
  public CheckBox() {
  }

  /**
   * Determines if input meets validator requirements
   *
   * @param input - input, as String
   * @return if input is valid, stored as Boolean.
   */
  @Override
  public Boolean isValid(Boolean input) {
    return Boolean.TRUE;
  }

  /**
   * hashCode method for generating hash code values for this CheckBox Validator
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
   * toString method for returning a String representation of this CheckBox Validator
   *
   * @return string representation, as a String.
   */
  @Override
  public String toString() {
    return "CheckBox{} " + super.toString();
  }
}
