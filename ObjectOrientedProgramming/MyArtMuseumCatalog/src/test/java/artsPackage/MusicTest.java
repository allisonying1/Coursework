package artsPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MusicTest {

  String expectedName = "Icky Thump";
  Name[] expectedCreators = new Name[2];
  Name[] expectedOwners = new Name[1];
  Double expectedLatestAskingPrice = 3000000000.0;
  Integer expectedNumOfDays = 99;
  String expectedTokenStandard = "ERC-721";
  Music testMusic;

  @BeforeEach
  void setUp() {
    expectedCreators[0] = new Name("Jack", null, "White");
    expectedCreators[1] = new Name("Meg", null, "White");
    expectedOwners[0] = new Name("Allison", "Leslie", "Ying");
    testMusic = new Music(expectedName, expectedCreators, expectedOwners,
        expectedLatestAskingPrice, expectedTokenStandard);
  }

  @Test
  void getName() {
    assertEquals(expectedName, testMusic.getName());
  }

  @Test
  void getCreators() {
    assertEquals(expectedCreators, testMusic.getCreators());
  }

  @Test
  void getOwners() {
    assertEquals(expectedOwners, testMusic.getOwners());
  }

  @Test
  void getAskingPrice() {
    assertEquals(expectedLatestAskingPrice, testMusic.getLatestAskingPrice());
  }

  @Test
  void getNumberOfDays() {
    assertEquals(0, testMusic.getNumberOfDaysAvailableForAuction());
  }

  @Test
  void setNumberOfDays_valid() {
    try {
      testMusic.setNumberOfDaysAvailableForAuction(expectedNumOfDays);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("Invalid Number of Days Exception should not have been thrown here!");
    }
    assertEquals(expectedNumOfDays, testMusic.getNumberOfDaysAvailableForAuction());
  }

  @Test
  void setNumberOfDays_invalid() {
    Exception exception = assertThrows(InvalidNumberOfDaysException.class, () ->
        testMusic.setNumberOfDaysAvailableForAuction(-expectedNumOfDays));
    String expectedMessage = "Number of Days must be a non-negative integer.";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void getTokenStandard() {
    assertEquals(expectedTokenStandard, testMusic.getTokenStandard());
  }

  @Test
  void calculateStartingBid_withNFTDiscount() {
    try {
      testMusic.setNumberOfDaysAvailableForAuction(61);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("An exception should not have been thrown here!");
    }
    assertEquals((expectedLatestAskingPrice * 1.25 * 0.6), testMusic.calculateStartingBid());
  }

  @Test
  void calculateStartingBid_withoutDiscount() {
    assertEquals((expectedLatestAskingPrice * 1.25), testMusic.calculateStartingBid());
  }
}