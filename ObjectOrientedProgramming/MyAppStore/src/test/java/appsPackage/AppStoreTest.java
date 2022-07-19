package appsPackage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppStoreTest {
  Name developer1 = new Name("Steve", "P", "Jobs");
  Name developer2 = new Name("Elizabeth", "A", "Holmes");
  LocalDate release1 = LocalDate.of(2010, 4, 18);
  LocalDate release2 = LocalDate.of(2020, 4, 18);
  LocalDate update = LocalDate.of(2021, 4, 18);
  MobileApp testApp1;
  MobileApp testApp2;
  MobileApp testApp3;
  MobileApp testApp4;
  List<Name> expectedDevelopers = new ArrayList<>();
  List<String> requestedPermissions1 = new ArrayList<>();
  List<String> requestedPermissions2 = new ArrayList<>();
  List<String> requestedPermissions3 = new ArrayList<>();
  List<String> requestedPermissions4 = new ArrayList<>();
  Map<MobileApp, Integer> approvedApps = new HashMap<>();
  List<MobileApp> appsUnderReview = new ArrayList<>();
  AppStore testAppStore;

  @BeforeEach
  void setUp() {
    // create list of developers:
    expectedDevelopers.add(developer1);
    expectedDevelopers.add(developer2);
    // create 4 lists of requested permissions:
    requestedPermissions1.add("Camera Access");
    requestedPermissions1.add("Enable Notifications");
    requestedPermissions2.add("Camera Access");
    requestedPermissions2.add("Microphone Access");
    requestedPermissions3.add("Camera Access");
    requestedPermissions3.add("Data Sharing");
    requestedPermissions4.add("Wallet Access");
    requestedPermissions4.add("Microphone Access");
    requestedPermissions4.add("Email Access");
    // create 4 approved app instances:
    testApp1 = new MobileApp("Flappy Bird", MobileAppCategory.GAME_APP, 2f,
        2f, expectedDevelopers, requestedPermissions1, release1, update);
    testApp2 = new MobileApp("Temple Run", MobileAppCategory.GAME_APP, 2f,
        2f, expectedDevelopers, requestedPermissions2, release2, update);
    testApp3 = new MobileApp("Subway Surfers", MobileAppCategory.GAME_APP, 2f,
        2f, expectedDevelopers, requestedPermissions3, release2, update);
    testApp4 = new MobileApp("Instagram", MobileAppCategory.SOCIAL_MEDIA_APP, 2f,
        2f, expectedDevelopers, requestedPermissions4, release2, update);
    /*
    * ASSUMPTIONS: For testing purposes, I will be adding the same four apps to the Approved Apps
    * map and the Apps under review List.
    * */
    approvedApps.put(testApp1, 1000);
    approvedApps.put(testApp2, 2000);
    approvedApps.put(testApp3, 3000);
    approvedApps.put(testApp4, 4000);

    appsUnderReview.add(testApp1);
    appsUnderReview.add(testApp2);
    appsUnderReview.add(testApp3);
    appsUnderReview.add(testApp4);

    // create AppStore instance:
    testAppStore = new AppStore(approvedApps, appsUnderReview);
  }

  @Test
  void mysteryMethod_noMatchingCategory() {
    List<MobileApp> expectedResults = new ArrayList<>();
    LocalDate validReleaseDate = LocalDate.of(2005, 01, 01);
    assertEquals(expectedResults, testAppStore.mysteryMethod(MobileAppCategory.EDUCATIONAL_APP,
        validReleaseDate));
  }

  @Test
  void mysteryMethod_noMatchingDate() {
    List<MobileApp> expectedResults = new ArrayList<>();
    LocalDate noMatchingReleaseDate = LocalDate.of(2022, 01, 01);
    assertEquals(expectedResults, testAppStore.mysteryMethod(MobileAppCategory.GAME_APP,
        noMatchingReleaseDate));
  }

  @Test
  void mysteryMethod_validMatch() {
    List<MobileApp> expectedResults = new ArrayList<>();
    expectedResults.add(testApp4);
    LocalDate validReleaseDate = LocalDate.of(2015, 01, 01);
    assertEquals(expectedResults, testAppStore.mysteryMethod(MobileAppCategory.SOCIAL_MEDIA_APP,
        validReleaseDate));
  }

  @Test
  void mysteryMethod_MultipleMatches() {
    List<MobileApp> expectedResults = new ArrayList<>();
    expectedResults.add(testApp2);
    expectedResults.add(testApp3);
    LocalDate validReleaseDate = LocalDate.of(2015, 01, 01);
    assertTrue(expectedResults.containsAll(testAppStore.mysteryMethod(MobileAppCategory.GAME_APP,
        validReleaseDate)));
  }

  @Test
  void filterByRequestedPermissions_noPassMaxCount() {
    List<MobileApp> expectedResults = new ArrayList<>();
    assertEquals(expectedResults, testAppStore.filterByRequestedPermissions(
        1, "Contacts Access", "Wallet Access"));
  }

  @Test
  void filterByRequestedPermissions_noPassPermissions() {
    List<MobileApp> expectedResults = new ArrayList<>();
    assertEquals(expectedResults, testAppStore.filterByRequestedPermissions(
        2, "Microphone Access", "Camera Access"));
  }

  @Test
  void filterByRequestedPermissions_onePass() {
    List<MobileApp> expectedResults = new ArrayList<>();
    expectedResults.add(testApp4);
    assertEquals(expectedResults, testAppStore.filterByRequestedPermissions(
        3, "Camera Access", "Enable Notification"));
  }

  @Test
  void filterByRequestedPermissions_MultiplePass() {
    List<MobileApp> expectedResults = new ArrayList<>();
    expectedResults.add(testApp1);
    expectedResults.add(testApp3);
    assertEquals(expectedResults, testAppStore.filterByRequestedPermissions(
        2, "Microphone Access", "Email Access"));
  }


}