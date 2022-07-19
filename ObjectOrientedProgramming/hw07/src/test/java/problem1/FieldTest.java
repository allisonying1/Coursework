package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldTest<T> {

  String expectedLabel = "password";
  Validators<T> expectedValidator;
  Field<T> testField;
  Field<T> testFieldNotRequired;

  @BeforeEach
  void setUp() {
    expectedValidator = new Passwords(7, 10, 1, 2,
        3);
    testField = new Field<>(expectedLabel, Boolean.TRUE, expectedValidator);
    testFieldNotRequired = new Field<>(expectedLabel, Boolean.FALSE, expectedValidator);
  }

  @Test
  void updateValue_valid() {
    String expectedValue = "a_AB123";
    try {
      testField.updateValue((T) expectedValue);
      assertEquals(expectedValue, testField.getValue());
    } catch (GenericFieldException e) {
      System.out.println("An Exception should not be thrown here!");
    }
  }

  @Test
  void updateValue_invalidInput() {
    String expectedValue = "1000";
    Exception exception = assertThrows(GenericFieldException.class, ()
        -> testField.updateValue((T) expectedValue));
    String expectedMessage = "Invalid input. Value has not been updated.";
    assertEquals(expectedMessage, exception.getMessage());
    assertNull(testField.getValue());
  }

  @Test
  void updateValue_invalidDataType() {
    /*
     * We can delete this test later. I just wanted to check what would happen if you inputted a
     * Boolean value into a String method!
     * */
    Exception exception = assertThrows(java.lang.ClassCastException.class, ()
        -> testField.updateValue((T) Boolean.TRUE));
    String expectedMessage = "class java.lang.Boolean cannot be cast to class java.lang.String "
        + "(java.lang.Boolean and java.lang.String are in module java.base of loader 'bootstrap')";
    assertEquals(expectedMessage, exception.getMessage());
    assertNull(testField.getValue());
  }

  @Test
  void updateValue_integerDataType() {
    /*
     * We can delete this test later. I just wanted to check what would happen if you inputted an
     * Integer value into a String method!
     * */
    Integer expectedValue = 1000;
    Exception exception = assertThrows(java.lang.ClassCastException.class, ()
        -> testField.updateValue((T) expectedValue));
    String expectedMessage = "class java.lang.Integer cannot be cast to class java.lang.String "
        + "(java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')";
    assertEquals(expectedMessage, exception.getMessage());
    assertNull(testField.getValue());
  }

  @Test
  void isFilled_requiredAndFilled() {
    String expectedValue = "a_AB123";
    try {
      testField.updateValue((T) expectedValue);
      assertTrue(testField.isFilled());
    } catch (GenericFieldException e) {
      System.out.println("An Exception should not be thrown here!");
    }
  }

  @Test
  void isFilled_requiredNotFilled() {
    assertFalse(testField.isFilled());
  }

  @Test
  void isFilled_notRequiredAndFilled() {
    String expectedValue = "a_AB123";
    try {
      testFieldNotRequired.updateValue((T) expectedValue);
      assertTrue(testFieldNotRequired.isFilled());
    } catch (GenericFieldException e) {
      System.out.println("An Exception should not be thrown here!");
    }
  }

  @Test
  void isFilled_notRequiredNotFilled() {
    assertTrue(testFieldNotRequired.isFilled());
  }

  @Test
  void getLabel() {
    assertEquals(expectedLabel, testField.getLabel());
  }

  @Test
  void getValue() {
    assertNull(testField.getValue());
  }

  @Test
  void getRequired() {
    assertTrue(testField.getRequired());
  }

  @Test
  void getValidator() {
    assertEquals(expectedValidator, testField.getValidator());
  }


  @Test
  void testEquals_equalsItself() {
    assertTrue(testField.equals(testField));
  }


  @Test
  void testEquals_differentObjects() {
    Validators<T> testRadioButton = new RadioButton();
    assertFalse(testField.equals(testRadioButton));
  }

  @Test
  void testEquals_true() {
    Field<T> newTestField = new Field<>(expectedLabel, Boolean.TRUE, expectedValidator);
    assertTrue(testField.equals(newTestField));
  }

  @Test
  void testEquals_differentMinLabel() {
    Field<T> newTestField = new Field<>("username", Boolean.TRUE, expectedValidator);
    assertFalse(testField.equals(newTestField));
  }

  @Test
  void testEquals_differentRequired() {
    assertFalse(testField.equals(testFieldNotRequired));
  }

  @Test
  void testEquals_differentValue() {
    Field<T> newTestField = new Field<>(expectedLabel, Boolean.TRUE, expectedValidator);
    String expectedValue = "a_AB123";
    try {
      newTestField.updateValue((T) expectedValue);
    } catch (GenericFieldException e) {
      System.out.println("An Exception should not be thrown here!");
    }
    assertFalse(testField.equals(newTestField));
  }

  @Test
  void testEquals_differentValidator() {
    Validators<T> testRadioButton = new RadioButton();
    Field<T> newTestField = new Field<>(expectedLabel, Boolean.TRUE, testRadioButton);
    assertFalse(testField.equals(newTestField));
  }


  @Test
  void testHashCode() {
    Field<T> newTestField = new Field<>(expectedLabel, Boolean.TRUE, expectedValidator);
    assertEquals(newTestField.hashCode(), testField.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Field{" +
        "label='" + expectedLabel + '\'' +
        ", value=" + null +
        ", required=" + Boolean.TRUE +
        ", validator=" + expectedValidator +
        '}';
    assertEquals(expectedString, testField.toString());
  }
}