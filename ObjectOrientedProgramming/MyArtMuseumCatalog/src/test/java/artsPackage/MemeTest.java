package artsPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemeTest {

  String expectedName = "First Wives Meme Club";
  Name[] expectedCreators = new Name[1];
  Name[] expectedOwners = new Name[2];
  Double expectedLatestAskingPrice = 100.00;
  Integer expectedNumOfDays = 16;
  String expectedTokenStandard = "BTC-230";
  Meme testMeme;

  @BeforeEach
  void setUp() {
    expectedCreators[0] = new Name("Mary", "Todd", "Lincoln");
    expectedOwners[0] = new Name("John", "F", "Kennedy");
    expectedOwners[1] = new Name("Jackie", "Todd", "Kennedy");
    testMeme = new Meme(expectedName, expectedCreators, expectedOwners,
        expectedLatestAskingPrice, expectedTokenStandard);
  }

  @Test
  void getName() {
    assertEquals(expectedName, testMeme.getName());
  }

  @Test
  void getCreators() {
    assertEquals(expectedCreators, testMeme.getCreators());
  }

  @Test
  void getOwners() {
    assertEquals(expectedOwners, testMeme.getOwners());
  }

  @Test
  void getAskingPrice() {
    assertEquals(expectedLatestAskingPrice, testMeme.getLatestAskingPrice());
  }

  @Test
  void getNumberOfDays() {
    assertEquals(0, testMeme.getNumberOfDaysAvailableForAuction());
  }

  @Test
  void setNumberOfDays_valid() {
    try {
      testMeme.setNumberOfDaysAvailableForAuction(1);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("Invalid Number of Days Exception should not have been thrown here!");
    }
    assertEquals(1, testMeme.getNumberOfDaysAvailableForAuction());
  }

  @Test
  void setNumberOfDays_invalid() {
    Exception exception = assertThrows(InvalidNumberOfDaysException.class, () ->
        testMeme.setNumberOfDaysAvailableForAuction(-1));
    String expectedMessage = "Number of Days must be a non-negative integer.";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void getTokenStandard() {
    assertEquals(expectedTokenStandard, testMeme.getTokenStandard());
  }

  @Test
  void calculateStartingBid_moreThan16Days() {
    try {
      testMeme.setNumberOfDaysAvailableForAuction(expectedNumOfDays);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("InvalidNumberOfDaysException should not have been thrown here!");
    }
    assertEquals((expectedLatestAskingPrice * 1.25 * 0.7), testMeme.calculateStartingBid());
  }

  @Test
  void calculateStartingBid_lessThan16Days() {
    assertEquals((expectedLatestAskingPrice * 1.25), testMeme.calculateStartingBid());
  }
}