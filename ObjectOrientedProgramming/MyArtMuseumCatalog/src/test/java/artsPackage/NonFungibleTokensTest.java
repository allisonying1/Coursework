package artsPackage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NonFungibleTokensTest {

  String expectedDiscountTokenStandard = "ERC-721";
  String expectedNonDiscountedTokenStandard = "BTC-200";
  Double expectedStartingPrice = 100.0;
  NonFungibleTokens testGame;
  NonFungibleTokens testMusic;
  NonFungibleTokens testDigitalArt;

  @BeforeEach
  void setUp() {
    testGame = new Game("My Game", null, null, expectedStartingPrice,
        expectedDiscountTokenStandard);
    testMusic = new Music("My Music", null, null, expectedStartingPrice,
        expectedNonDiscountedTokenStandard);
    testDigitalArt = new DigitalArt("My Digital Art", null, null,
        expectedStartingPrice, expectedDiscountTokenStandard);
  }

  @Test
  void calculateStartingBid_expectedDiscount() {
    Double expectedStartingBid = expectedStartingPrice * 1.25 * 0.6;
    try {
      testGame.setNumberOfDaysAvailableForAuction(61);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("Invalid Number of Days Exception should not have been thrown here.");
    }
    assertEquals(expectedStartingBid, testGame.calculateStartingBid());
  }

  @Test
  void calculateStartingBid_NotEnoughDays() {
    Double expectedStartingBid = expectedStartingPrice * 1.25;
    try {
      testDigitalArt.setNumberOfDaysAvailableForAuction(59);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("Invalid Number of Days Exception should not have been thrown here.");
    }
    assertEquals(expectedStartingBid, testDigitalArt.calculateStartingBid());
  }

  @Test
  void calculateStartingBid_differentTokenStandard() {
    Double expectedStartingBid = expectedStartingPrice * 1.25;
    try {
      testMusic.setNumberOfDaysAvailableForAuction(61);
    } catch (InvalidNumberOfDaysException e) {
      System.out.println("Invalid Number of Days Exception should not have been thrown here.");
    }
    assertEquals(expectedStartingBid, testMusic.calculateStartingBid());
  }

  @Test
  void getTokenStandard() {
    assertEquals(expectedDiscountTokenStandard, testGame.getTokenStandard());
  }

  @Test
  void testEquals_sameObjects() {
    assertTrue(testMusic.equals(testMusic));
  }

  @Test
  void testEquals_sameFields() {
    Music sameMusic = new Music("My Music", null, null, expectedStartingPrice,
        expectedNonDiscountedTokenStandard);
    assertTrue(testMusic.equals(sameMusic));
  }

  @Test
  void testEquals_DifferentObjects() {
    assertFalse(testMusic.equals(new Name("test", "should", "fail")));
  }

  @Test
  void testEquals_DifferentSuperFields() {
    DigitalArt differentDigitalArt = new DigitalArt("Not My Digital Art", null,
        null, expectedStartingPrice, expectedDiscountTokenStandard);
    assertFalse(testDigitalArt.equals(differentDigitalArt));
  }

  @Test
  void testEquals_DifferentTokenStandard() {
    assertFalse(testGame.equals(testMusic));
  }

  @Test
  void testHashCode() {
    Game sameGame = new Game("My Game", null, null, expectedStartingPrice,
        expectedDiscountTokenStandard);
    assertEquals(testGame.hashCode(), sameGame.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "NonFungibleTokens{" +
        "tokenStandard='" + testMusic.getTokenStandard() + '\'' +
        "} " + "ArtPieces{" +
        "name='" + testMusic.getName() + '\'' +
        ", creators=" + Arrays.toString(testMusic.getCreators()) +
        ", owners=" + Arrays.toString(testMusic.getOwners()) +
        ", numberOfDaysAvailableForAuction=" + testMusic.getNumberOfDaysAvailableForAuction() +
        ", latestAskingPrice=" + testMusic.getLatestAskingPrice() +
        '}';
    assertEquals(expectedString, testMusic.toString());
  }
}