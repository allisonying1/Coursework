package artsPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DigitalArtTest {

  String expectedName = "Screenshot of a Tweet";
  Name[] expectedCreators = new Name[1];
  Name[] expectedOwners = new Name[1];
  Double expectedLatestAskingPrice = 10000.0;
  Integer expectedNumOfDays = 100;
  String expectedTokenStandard = "ERC-721";
  DigitalArt testDigitalArt;

  @BeforeEach
  void setUp() {
    expectedCreators[0] = new Name("Kanye", "Ye", "West");
    expectedOwners[0] = new Name("Allison", "Leslie", "Ying");
    testDigitalArt = new DigitalArt(expectedName, expectedCreators, expectedOwners,
        expectedLatestAskingPrice, expectedTokenStandard);
  }

  @Test
  void getName() {
    assertEquals(expectedName, testDigitalArt.getName());
  }

  @Test
  void getCreators() {
    assertEquals(expectedCreators, testDigitalArt.getCreators());
  }

  @Test
  void getOwners() {
    assertEquals(expectedOwners, testDigitalArt.getOwners());
  }

  @Test
  void getAskingPrice() {
    assertEquals(expectedLatestAskingPrice, testDigitalArt.getLatestAskingPrice());
  }

  @Test
  void getNumberOfDays() {
    assertEquals(0, testDigitalArt.getNumberOfDaysAvailableForAuction());
  }

  @Test
  void setNumberOfDays_valid() {
    try {
      testDigitalArt.setNumberOfDaysAvailableForAuction(expectedNumOfDays);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("Invalid Number of Days Exception should not have been thrown here!");
    }
    assertEquals(expectedNumOfDays, testDigitalArt.getNumberOfDaysAvailableForAuction());
  }

  @Test
  void setNumberOfDays_invalid() {
    Exception exception = assertThrows(InvalidNumberOfDaysException.class, () ->
        testDigitalArt.setNumberOfDaysAvailableForAuction(-expectedNumOfDays));
    String expectedMessage = "Number of Days must be a non-negative integer.";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void getTokenStandard() {
    assertEquals(expectedTokenStandard, testDigitalArt.getTokenStandard());
  }

  @Test
  void calculateStartingBid_withNFTDiscount() {
    try {
      testDigitalArt.setNumberOfDaysAvailableForAuction(61);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("An exception should not have been thrown here!");
    }
    assertEquals((expectedLatestAskingPrice * 1.25 * 0.6), testDigitalArt.calculateStartingBid());
  }

  @Test
  void calculateStartingBid_withoutDiscount() {
    assertEquals((expectedLatestAskingPrice * 1.25), testDigitalArt.calculateStartingBid());
  }
}