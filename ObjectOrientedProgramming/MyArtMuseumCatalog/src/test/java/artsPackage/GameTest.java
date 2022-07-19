package artsPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

  String expectedName = "Diner Dash";
  Name[] expectedCreators = new Name[1];
  Name[] expectedOwners = new Name[2];
  Double expectedLatestAskingPrice = 50.0;
  Integer expectedNumOfDays = 99;
  String expectedTokenStandard = "ERC-721";
  Game testGame;

  @BeforeEach
  void setUp() {
    expectedCreators[0] = new Name("Elizabeth", null, "Holmes");
    expectedOwners[0] = new Name("Allison", "Leslie", "Ying");
    expectedOwners[1] = new Name("Big", null, "Foot");
    testGame = new Game(expectedName, expectedCreators, expectedOwners,
        expectedLatestAskingPrice, expectedTokenStandard);
  }

  @Test
  void getName() {
    assertEquals(expectedName, testGame.getName());
  }

  @Test
  void getCreators() {
    assertEquals(expectedCreators, testGame.getCreators());
  }

  @Test
  void getOwners() {
    assertEquals(expectedOwners, testGame.getOwners());
  }

  @Test
  void getAskingPrice() {
    assertEquals(expectedLatestAskingPrice, testGame.getLatestAskingPrice());
  }

  @Test
  void getNumberOfDays() {
    assertEquals(0, testGame.getNumberOfDaysAvailableForAuction());
  }

  @Test
  void setNumberOfDays_valid() {
    try {
      testGame.setNumberOfDaysAvailableForAuction(expectedNumOfDays);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("Invalid Number of Days Exception should not have been thrown here!");
    }
    assertEquals(expectedNumOfDays, testGame.getNumberOfDaysAvailableForAuction());
  }

  @Test
  void setNumberOfDays_invalid() {
    Exception exception = assertThrows(InvalidNumberOfDaysException.class, () ->
        testGame.setNumberOfDaysAvailableForAuction(-expectedNumOfDays));
    String expectedMessage = "Number of Days must be a non-negative integer.";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void getTokenStandard() {
    assertEquals(expectedTokenStandard, testGame.getTokenStandard());
  }

  @Test
  void calculateStartingBid_withNFTDiscount() {
    try {
      testGame.setNumberOfDaysAvailableForAuction(61);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("An exception should not have been thrown here!");
    }
    assertEquals((expectedLatestAskingPrice * 1.25 * 0.6), testGame.calculateStartingBid());
  }

  @Test
  void calculateStartingBid_withoutDiscount() {
    assertEquals((expectedLatestAskingPrice * 1.25), testGame.calculateStartingBid());
  }
}