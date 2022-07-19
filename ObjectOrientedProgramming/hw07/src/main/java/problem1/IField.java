package problem1;

public interface IField<T> {

  /**
   * Updates the Field's value, if the input is valid
   *
   * @param input as a Generic T data type
   * @throws GenericFieldException if input is not valid
   */
  void updateValue(T input) throws GenericFieldException;

  /**
   * Checks if Field is ready to be submitted
   *
   * @return if all requirements are met, as Boolean
   */
  boolean isFilled();
}
