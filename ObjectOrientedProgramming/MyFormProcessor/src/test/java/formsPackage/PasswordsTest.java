package formsPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordsTest {

  Integer expectedMinLength = 7;
  Integer expectedMaxLength = 10;
  Integer expectedMinLowercase = 1;
  Integer expectedMinUppercase = 2;
  Integer expectedMinDigits = 3;
  Passwords<String> testPassword;

  @BeforeEach
  void setUp() {
    testPassword = new Passwords(expectedMinLength, expectedMaxLength, expectedMinLowercase,
        expectedMinUppercase, expectedMinDigits);
  }

  @Test
  void isValid_validPassword() {
    String expectedPassword = "a_AB123";
    assertTrue(testPassword.isValid(expectedPassword));
  }

  @Test
  void isValid_tooShort() {
    String expectedPassword = "aAB123";
    assertFalse(testPassword.isValid(expectedPassword));
  }

  @Test
  void isValid_tooLong() {
    String expectedPassword = "abcdABCD123456";
    assertFalse(testPassword.isValid(expectedPassword));
  }

  @Test
  void isValid_invalidMinLowercase() {
    String expectedPassword = "ABCD123";
    assertFalse(testPassword.isValid(expectedPassword));
  }

  @Test
  void isValid_invalidMinUppercase() {
    String expectedPassword = "abcd123";
    assertFalse(testPassword.isValid(expectedPassword));
  }

  @Test
  void isValid_invalidMinDigit() {
    String expectedPassword = "abcdABCD";
    assertFalse(testPassword.isValid(expectedPassword));
  }

  @Test
  void isValid_hasSpace() {
    String expectedPassword = "a b A B 1 2 3";
    assertFalse(testPassword.isValid(expectedPassword));
  }

  @Test
  void getMinLength() {
    assertEquals(expectedMinLength, testPassword.getMinLength());
  }

  @Test
  void getMaxLength() {
    assertEquals(expectedMaxLength, testPassword.getMaxLength());
  }

  @Test
  void getMinLowercase() {
    assertEquals(expectedMinLowercase, testPassword.getMinLowercase());
  }

  @Test
  void getMinUppercase() {
    assertEquals(expectedMinUppercase, testPassword.getMinUppercase());
  }

  @Test
  void getMinDigits() {
    assertEquals(expectedMinDigits, testPassword.getMinDigits());
  }

  @Test
  void testEquals_equalsItself() {
    assertTrue(testPassword.equals(testPassword));
  }


  @Test
  void testEquals_differentObjects() {
    RadioButton<Boolean> testRadioButton = new RadioButton();
    assertFalse(testPassword.equals(testRadioButton));
  }

  @Test
  void testEquals_true() {
    Passwords<String> myNewPassword = new Passwords(expectedMinLength, expectedMaxLength,
        expectedMinLowercase, expectedMinUppercase, expectedMinDigits);
    assertTrue(testPassword.equals(myNewPassword));
  }

  @Test
  void testEquals_differentMinLength() {
    Passwords<String> myNewPassword = new Passwords(0, expectedMaxLength,
        expectedMinLowercase, expectedMinUppercase, expectedMinDigits);
    assertFalse(testPassword.equals(myNewPassword));
  }

  @Test
  void testEquals_differentMaxLength() {
    Passwords<String> myNewPassword = new Passwords(expectedMinLength, 20,
        expectedMinLowercase, expectedMinUppercase, expectedMinDigits);
    assertFalse(testPassword.equals(myNewPassword));
  }

  @Test
  void testEquals_differentLowercase() {
    Passwords<String> myNewPassword = new Passwords(expectedMinLength, expectedMaxLength,
        4, expectedMinUppercase, expectedMinDigits);
    assertFalse(testPassword.equals(myNewPassword));
  }

  @Test
  void testEquals_differentUppercase() {
    Passwords<String> myNewPassword = new Passwords(expectedMinLength, expectedMaxLength,
        expectedMinLowercase, 4, expectedMinDigits);
    assertFalse(testPassword.equals(myNewPassword));
  }

  @Test
  void testEquals_differentDigits() {
    Passwords<String> myNewPassword = new Passwords(expectedMinLength, expectedMaxLength,
        expectedMinLowercase, expectedMinUppercase, 4);
    assertFalse(testPassword.equals(myNewPassword));
  }

  @Test
  void testHashCode() {
    Passwords<String> myNewPassword = new Passwords(expectedMinLength, expectedMaxLength,
        expectedMinLowercase, expectedMinUppercase, expectedMinDigits);
    assertEquals(testPassword.hashCode(), myNewPassword.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Passwords{" +
        "minLength=" + expectedMinLength +
        ", maxLength=" + expectedMaxLength +
        ", minLowercase=" + expectedMinLowercase +
        ", minUppercase=" + expectedMinUppercase +
        ", minDigits=" + expectedMinDigits +
        "} ";
    assertEquals(testPassword.toString(), expectedString);
  }
}