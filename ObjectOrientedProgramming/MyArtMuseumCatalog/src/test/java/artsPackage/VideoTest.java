package artsPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VideoTest {

  String expectedName = "An Artsy-Fartsy French Film";
  Name[] expectedCreators = new Name[1];
  Name[] expectedOwners = new Name[1];
  Double expectedLatestAskingPrice = 900000.0;
  Integer expectedNumOfDays = 70;
  String expectedTokenStandard = "ERC-721";
  Video testVideo;

  @BeforeEach
  void setUp() {
    expectedCreators[0] = new Name("Jean", "Luc", "Goddard");
    expectedOwners[0] = new Name("Jack", null, "Black");
    testVideo = new Video(expectedName, expectedCreators, expectedOwners,
        expectedLatestAskingPrice, expectedTokenStandard);
  }

  @Test
  void getName() {
    assertEquals(expectedName, testVideo.getName());
  }

  @Test
  void getCreators() {
    assertEquals(expectedCreators, testVideo.getCreators());
  }

  @Test
  void getOwners() {
    assertEquals(expectedOwners, testVideo.getOwners());
  }

  @Test
  void getAskingPrice() {
    assertEquals(expectedLatestAskingPrice, testVideo.getLatestAskingPrice());
  }

  @Test
  void getNumberOfDays() {
    assertEquals(0, testVideo.getNumberOfDaysAvailableForAuction());
  }

  @Test
  void setNumberOfDays_valid() {
    try {
      testVideo.setNumberOfDaysAvailableForAuction(expectedNumOfDays);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("Invalid Number of Days Exception should not have been thrown here!");
    }
    assertEquals(expectedNumOfDays, testVideo.getNumberOfDaysAvailableForAuction());
  }

  @Test
  void setNumberOfDays_invalid() {
    Exception exception = assertThrows(InvalidNumberOfDaysException.class, () ->
        testVideo.setNumberOfDaysAvailableForAuction(-expectedNumOfDays));
    String expectedMessage = "Number of Days must be a non-negative integer.";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void getTokenStandard() {
    assertEquals(expectedTokenStandard, testVideo.getTokenStandard());
  }

  @Test
  void calculateStartingBid_withNFTDiscount() {
    try {
      testVideo.setNumberOfDaysAvailableForAuction(61);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("An exception should not have been thrown here!");
    }
    assertEquals((expectedLatestAskingPrice * 1.25 * 0.6), testVideo.calculateStartingBid());
  }

  @Test
  void calculateStartingBid_withoutDiscount() {
    assertEquals((expectedLatestAskingPrice * 1.25), testVideo.calculateStartingBid());
  }
}