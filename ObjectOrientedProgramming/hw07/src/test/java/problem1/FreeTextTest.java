package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FreeTextTest {

  private Integer expectedNumLine = 2;
  private Integer expectedNumCharacter = 5;
  private FreeText<String> testFreeText;

  @BeforeEach
  void setUp() {
    testFreeText = new FreeText(expectedNumLine, expectedNumCharacter);
  }

  @Test
  void isValidEqual() {
    String expectedFreeText = "abc12345DE";
    assertTrue(testFreeText.isValid(expectedFreeText));
  }

  @Test
  void isValidLess() {
    String expectedFreeText2 = "ab1235DE";
    assertTrue(testFreeText.isValid(expectedFreeText2));
  }

  @Test
  void isValidMore() {
    String expectedFreeText3 = "abcd12345DEF";
    assertFalse(testFreeText.isValid(expectedFreeText3));
  }

  @Test
  void testEquals() {
    assertTrue(testFreeText.equals(testFreeText));
  }

  @Test
  void testEqualsDifferentObject() {
    RadioButton<Boolean> testRadioButton = new RadioButton();
    assertFalse(testFreeText.equals(testRadioButton));
  }

  @Test
  void testEqualsDifferentNumLine() {
    FreeText<String> testFreeText2 = new FreeText(10, expectedNumCharacter);
    assertFalse(testFreeText.equals(testFreeText2));
  }

  @Test
  void testEqualsDifferentNumCharacter() {
    FreeText<String> testFreeText3 = new FreeText(expectedNumLine, 10);
    assertFalse(testFreeText.equals(testFreeText3));
  }

  @Test
  void testEquals2() {
    FreeText<String> testFreeText3 = new FreeText(expectedNumLine, expectedNumCharacter);
    assertTrue(testFreeText.equals(testFreeText3));

  }

  @Test
  void testHashCode() {
    FreeText<String> newTestFreeText = new FreeText(expectedNumLine, expectedNumCharacter);
    assertEquals(testFreeText.hashCode(), newTestFreeText.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "FreeText{" +
        "numLine=" + testFreeText.getNumLine() +
        ", numCharacter=" + testFreeText.getNumCharacter() +
        "} ";
    assertEquals(expectedString, testFreeText.toString());
  }
}