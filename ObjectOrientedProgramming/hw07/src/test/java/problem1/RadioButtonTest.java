package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RadioButtonTest {

  RadioButton testRadioButton;

  @BeforeEach
  void setUp() {
    testRadioButton = new RadioButton();
  }

  @Test
  void isValidCorrectTrue() {
    assertTrue(testRadioButton.isValid(true));
  }

  @Test
  void isValidCorrectFalse() {
    assertTrue(testRadioButton.isValid(false));
  }

  @Test
  void isValidIncorrect() {
    assertFalse(testRadioButton.isValid(null));
  }

  @Test
  void testEquals() {
    assertTrue(testRadioButton.equals(testRadioButton));
  }

  @Test
  void testHashCode() {
    RadioButton<Boolean> newTestRadioButton = new RadioButton<>();
    assertEquals(testRadioButton.hashCode(), newTestRadioButton.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "RadioButton{} " + "Validators{}";
    assertEquals(expectedString, testRadioButton.toString());
  }
}