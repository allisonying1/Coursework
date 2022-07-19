package parksPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NationalParkDirectoryTest {

  NationalPark testPark1;
  NationalPark testPark2;
  NationalParkDirectory testDirectory;

  @BeforeEach
  void setUp() {
    testDirectory = new NationalParkDirectory();
    testPark1 = new NationalPark("1234", "Yellowstone",
        "Wyoming", null, null, null, true);
    testPark2 = new NationalPark("5678", "Zion", "Utah",
        null, null, null, true);
  }

  @Test
  void count() {
    assertEquals(0, testDirectory.count());
  }

  @Test
  void count_multiple() {
    NationalPark[] myCollection = new NationalPark[2];
    myCollection[0] = testPark1;
    myCollection[1] = testPark2;
    NationalParkDirectory newTestDirectory = new NationalParkDirectory(myCollection);
    assertEquals(2, newTestDirectory.count());
  }

  @Test
  void add_different() {
    testDirectory = testDirectory.add(testPark1);
    testDirectory = testDirectory.add(testPark2);
    NationalPark[] myCollection = new NationalPark[2];
    myCollection[0] = testPark1;
    myCollection[1] = testPark2;
    NationalParkDirectory expectedDirectory = new NationalParkDirectory(myCollection);
    assertEquals(expectedDirectory, testDirectory);
  }

  @Test
  void add_duplicate() {
    testDirectory = testDirectory.add(testPark1);
    testDirectory = testDirectory.add(testPark1);
    NationalPark[] myCollection = new NationalPark[1];
    myCollection[0] = testPark1;
    NationalParkDirectory expectedDirectory = new NationalParkDirectory(myCollection);
    assertEquals(expectedDirectory, testDirectory);
  }

  @Test
  void findAllYearRound() {
    testDirectory = testDirectory.add(testPark1);
    testDirectory = testDirectory.add(testPark2);
    NationalPark[] myCollection = new NationalPark[2];
    myCollection[0] = testPark1;
    myCollection[1] = testPark2;
    assertArrayEquals(myCollection, testDirectory.findAllYearRound());
  }

  @Test
  void findAllYearRound_none() {
    testDirectory = testDirectory.add(testPark1);
    testDirectory = testDirectory.add(testPark2);
    try {
      testDirectory.modifyOpenYearRound(testPark1, false);
      testDirectory.modifyOpenYearRound(testPark2, false);
    } catch (NationalParkNotFoundException e) {
      System.out.println("National Park Not Found exception should not have been thrown here!");
    }
    NationalPark[] myCollection = new NationalPark[0];
    assertArrayEquals(myCollection, testDirectory.findAllYearRound());
  }

  @Test
  void get_valid() {
    testDirectory = testDirectory.add(testPark1);
    try {
      assertEquals(testPark1, testDirectory.get("1234"));
    } catch (InvalidNationalParkIDException e) {
      System.out.println("An Invalid National Park ID exception should not have been thrown here!");
    }
  }

  @Test
  void get_emptyCollection() {
    Exception exception = assertThrows(InvalidNationalParkIDException.class, () ->
        testDirectory.get("1234"));
    String expectedMessage = "System is empty.";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void get_InvalidID() {
    testDirectory = testDirectory.add(testPark2);
    Exception exception = assertThrows(InvalidNationalParkIDException.class, () ->
        testDirectory.get("1234"));
    String expectedMessage = "National Park ID is not in our system.";
    assertEquals(expectedMessage, exception.getMessage());
  }
}