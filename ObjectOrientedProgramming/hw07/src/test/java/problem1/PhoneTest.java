package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PhoneTest {

  private Integer expectedLength = 10;
  private Phone<String> testPhone;
  private Phone testPhone2;

  @BeforeEach
  void setUp() {
    testPhone = new Phone(expectedLength);
  }

  @Test
  void isValidCheckDigit() {
    String expectedPhone = "ab12345678";
    assertFalse(testPhone.isValid(expectedPhone));
  }

  @Test
  void isValidCheckDigit2() {
    String expectedPhone = "1234567890";
    assertTrue(testPhone.isValid(expectedPhone));
  }

  @Test
  void isValidLength() {
    String expectedPhone = "12345678";
    assertFalse(testPhone.isValid(expectedPhone));
  }

  @Test
  void testEquals() {
    assertTrue(testPhone.equals(testPhone));
  }

  @Test
  void testEqualsDifferentObject() {
    RadioButton<Boolean> testRadioButton = new RadioButton();
    assertFalse(testPhone.equals(testRadioButton));
  }

  @Test
  void testEquals2() {
    Phone<String> testPhone2 = new Phone(expectedLength);
    assertTrue(testPhone.equals(testPhone2));
  }

  @Test
  void testHashCode() {
    Phone<String> newTestPhone = new Phone(expectedLength);
    assertEquals(testPhone.hashCode(), newTestPhone.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Phone{" +
        "length=" + testPhone.getLength() +
        "} ";
    assertEquals(expectedString, testPhone.toString());
  }
}