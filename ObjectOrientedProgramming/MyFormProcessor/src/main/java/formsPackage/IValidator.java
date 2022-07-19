package formsPackage;

public interface IValidator<T> {

  /**
   * Determines if input meets validator requirements
   *
   * @param input, as a String or Boolean data type.
   * @return if input is valid, stored as Boolean.
   */
  Boolean isValid(T input);
}
