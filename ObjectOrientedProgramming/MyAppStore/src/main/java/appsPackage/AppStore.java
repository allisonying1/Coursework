package appsPackage;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
/*
Class AppStore contains information about two different categories of apps - approved apps,
and apps currently under review, for approval and addition to the app store. Approved apps
are stored into a map, where the app represents the key, and the number of downloads,
represented as an Integer is the corresponding value.
*/
public class AppStore {
  private Map<MobileApp, Integer> approvedApps;
  private List<MobileApp> appsUnderReview;
  public AppStore(Map<MobileApp, Integer> approvedApps, List<MobileApp> appsUnderReview) {
    this.approvedApps = approvedApps;
    this.appsUnderReview = appsUnderReview;
  }
  public Map<MobileApp, Integer> getApprovedApps() {
    return approvedApps;
  }
  public List<MobileApp> getAppsUnderReview() {
    return appsUnderReview;
  }

  /**
   * mysteryMethod will take in two filter requests: (1) a specified Mobile App Category, and (2) a
   * specified release date, and return a filtered list of Mobile App Objects that match the input
   * Category and were released after the requested input date:
   * @param requestedCategory- the filter by input Category, stored as a MobileAppCategory Enum
   * @param requestedDate- the filter by input Release Date, stored as a LocalDate object
   * @return a filtered List of MobileApps, where all the Mobile Apps in the List match the
   * requested Category and were released after the requested Date.
   * */
  public List<MobileApp> mysteryMethod(MobileAppCategory requestedCategory, LocalDate requestedDate){
    return this.approvedApps.keySet().stream().filter(x -> x.getAppCategory().equals(requestedCategory))
        .filter(y -> y.getDateOfOriginalRelease().isAfter(requestedDate)).collect(Collectors.toList());
  }

  /**
   * filterByRequestedPermissions will return a filtered list of Mobile Apps Under Review whose
   * Permission Requests are less than the input Maximum Number of Permission Requests and none of
   * the Permission Requests match the input Permission Requests:
   * @param maxNumPermissions- do not include Mobile apps that surpass the maximum count of
   *                         permission requests, stored as an Integer.
   * @param permission1- do not include permission1 in the filtered list, stored as a String.
   * @param permission2- do not include permission2 in the filtered list, stored as a String.
   * @return a filtered List of Mobile Apps under review, where none of the apps surpass the
   * maximum number of permission requests and do not contain any of the input permissions.
   * */
  public List<MobileApp> filterByRequestedPermissions(Integer maxNumPermissions, String permission1,
      String permission2){
    return this.appsUnderReview
        .stream()
        .filter(app -> app.getRequestedPermissions().size() <= maxNumPermissions &&
            ! app.getRequestedPermissions().contains(permission1) &&
            ! app.getRequestedPermissions().contains(permission2))
        .collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AppStore)) return false;
    AppStore appStore = (AppStore) o;
    return Objects.equals(getApprovedApps(), appStore.getApprovedApps()) &&
        Objects.equals(getAppsUnderReview(), appStore.getAppsUnderReview());
  }
  @Override
  public int hashCode() {
    return Objects.hash(getApprovedApps(), getAppsUnderReview());
  }
  @Override
  public String toString() {
    return "AppStore{" +
        "approvedApps=" + approvedApps +
        ", appsUnderReview=" + appsUnderReview + "}";
  }
}
