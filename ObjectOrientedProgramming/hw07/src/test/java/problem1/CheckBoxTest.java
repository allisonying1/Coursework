package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckBoxTest {

  private CheckBox<Boolean> testCheckBox;

  @BeforeEach
  void setUp() {
    testCheckBox = new CheckBox();
  }

  @Test
  void isValid() {
    assertTrue(testCheckBox.isValid(true));
    assertTrue(testCheckBox.isValid(true));
    assertTrue(testCheckBox.isValid(null));
  }

  @Test
  void testEquals() {
    assertTrue(testCheckBox.equals(testCheckBox));
  }

  @Test
  void testHashCode() {
    CheckBox<Boolean> newTestCheckBox = new CheckBox();
    assertEquals(testCheckBox.hashCode(), newTestCheckBox.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "CheckBox{} " + "Validators{}";
    assertEquals(expectedString, testCheckBox.toString());
  }
}