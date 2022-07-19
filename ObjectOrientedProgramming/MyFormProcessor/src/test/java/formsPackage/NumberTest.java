package formsPackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

  private Number testNumberValidatorNoDecimal;
  private Number testNumberValidatorDecimal;

  @BeforeEach
  void setUp() throws ClientInputException {

    testNumberValidatorDecimal = new Number(2, 1,
        2, 1);
    testNumberValidatorNoDecimal = new Number(4, 4);
  }

  @Test
  void constructor1Negative() throws ClientInputException {

    try {
      Number testNumberValidatorDecimal2 = new Number(-1, 1,
          2, 1);
      fail("An Exception should've been thrown");
    } catch (ClientInputException e) {
      assertEquals("There was an invalid client value found!", e.getMessage());
    }

  }

  @Test
  void constructor1MinMax() throws ClientInputException {
    try {
      Number testNumberValidatorDecimal2 = new Number(1, 3, 2, 1);
      fail("An Exception should've been thrown");
    } catch (ClientInputException e) {

    }

  }

  @Test
  void constructor2Negative() throws ClientInputException {
    try {
      Number testNumberValidatorNoDecimal2 = new Number(-1, 1);
      fail("An Exception should've been thrown");
    } catch (ClientInputException e) {

    }

  }

  @Test
  void constructor2MinMax() throws ClientInputException {
    try {
      Number testNumberValidatorNoDecimal2 = new Number(2, 4);
      fail("An Exception should've been thrown");
    } catch (ClientInputException e) {

    }

  }

  @Test
  void isValidCorrectDecimal() {

    assertTrue(testNumberValidatorDecimal.isValid("15.25"));
  }

  @Test
  void isValidTooLongIntegerValue() {

    assertFalse(testNumberValidatorDecimal.isValid("300.00"));
  }

  @Test
  void isValidTooShortIntegerValue() {

    assertFalse(testNumberValidatorDecimal.isValid(".00"));
  }

  @Test
  void isValidTooLongDecimalValue() {

    assertFalse(testNumberValidatorDecimal.isValid("30.334"));
  }

  @Test
  void isValidTooLShortDecimalValue() {

    assertFalse(testNumberValidatorDecimal.isValid("30."));
  }

  @Test
  void isValidMissingDecimal() {

    assertFalse(testNumberValidatorDecimal.isValid("30"));
  }

  @Test
  void isValidCorrectNoDecimal() {

    assertTrue(testNumberValidatorNoDecimal.isValid("2022"));
  }

  @Test
  void isValidTooLong() {

    assertFalse(testNumberValidatorNoDecimal.isValid("20221"));
  }

  @Test
  void isValidTooShort() {

    assertFalse(testNumberValidatorNoDecimal.isValid("202"));
  }

  @Test
  void isValidWithDecimal() {

    assertFalse(testNumberValidatorNoDecimal.isValid("202.0"));
  }

  @Test
  void getMaxNumberOfDigits() {
    assertEquals(4, testNumberValidatorNoDecimal.getMaxNumberOfDigits());
  }

  @Test
  void getMinNumberOfDigits() {
    assertEquals(4, testNumberValidatorNoDecimal.getMinNumberOfDigits());
  }

  @Test
  void getMaxNumberOfDecimals() {
    assertEquals(2, testNumberValidatorDecimal.getMaxNumberOfDecimals());
  }

  @Test
  void getMinNumberOfDecimals() {
    assertEquals(1, testNumberValidatorDecimal.getMinNumberOfDecimals());
  }

  @Test
  void testEqualsNull() {
    assertFalse(testNumberValidatorDecimal.equals(null));
  }

  @Test
  void testEqualsSameObject() {
    assertTrue(testNumberValidatorDecimal.equals(testNumberValidatorDecimal));
  }

  @Test
  void testEqualsDifferentObject() {
    assertFalse(testNumberValidatorDecimal.equals(testNumberValidatorNoDecimal));
  }

  @Test
  void testEqualsDifferentField() throws ClientInputException {
    Number testNumberValidatorDecimal2;
    testNumberValidatorDecimal2 = new Number(5, 1,
        2, 1);
    assertFalse(testNumberValidatorDecimal.equals(testNumberValidatorDecimal2));
  }

  @Test
  void testHashCode() throws ClientInputException {
    Number newTestNumber = new Number(2, 1, 2,
        1);
    assertEquals(testNumberValidatorDecimal.hashCode(), newTestNumber.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Number{" +
        "maxNumberOfDigits=" + testNumberValidatorDecimal.getMaxNumberOfDigits() +
        ", minNumberOfDigits=" + testNumberValidatorDecimal.getMinNumberOfDigits() +
        ", maxNumberOfDecimals=" + testNumberValidatorDecimal.getMaxNumberOfDecimals() +
        ", minNumberOfDecimals=" + testNumberValidatorDecimal.getMinNumberOfDecimals() +
        "} ";
    assertEquals(expectedString, testNumberValidatorDecimal.toString());
  }
}