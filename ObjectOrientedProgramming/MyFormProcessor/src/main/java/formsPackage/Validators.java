package formsPackage;

public abstract class Validators<T> implements IValidator<T> {

  /**
   * An abstract class for all types of Validators
   */

  private static final int PRIME = 31;

  public Validators() {
  }

  /**
   * Determines if input meets validator requirements
   *
   * @param input, as a String or Boolean data type.
   * @return if input is valid, stored as Boolean.
   */
  @Override
  public abstract Boolean isValid(T input);

  /**
   * hashCode method for generating hash code values for this Validator
   *
   * @return hash code, as an Integer
   */
  @Override
  public int hashCode() {
    return PRIME;
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
   * to String method for returning a String representation of this Validator
   *
   * @return string representation, as a String.
   */
  @Override
  public String toString() {
    return "Validators{}";
  }
}

