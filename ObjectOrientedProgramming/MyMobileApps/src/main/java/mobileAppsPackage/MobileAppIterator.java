package mobileAppsPackage;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import mobileAppsPackage.MobileAppCategory;

public class MobileAppIterator implements Iterator{

  List<MobileApp> listOfApps;
  private static final LocalDate DATE_THRESHOLD = LocalDate.of(2017, 1, 1);
  private static final MobileAppCategory VALID_EDUCATIONAL_APP = MobileAppCategory.EDUCATIONAL_APP;
  private static final MobileAppCategory VALID_PRODUCTIVITY_APP = MobileAppCategory.PRODUCTIVITY_APP;
  private static final String REMOVE_PHOTO_PERMISSION_REQUESTS = "PHOTO";
  private static final String REMOVE_MESSAGES_PERMISSION_REQUESTS = "MESSAGE";
  private static final String REMOVE_LOCATION_PERMISSION_REQUESTS = "LOCATION";


  public MobileAppIterator(List<MobileApp> listOfApps) {
    this.listOfApps = this.filterByReleaseDateCategoryAndPermissions(listOfApps);
  }

  /**
   * filterByReleaseDateCategoryAndPermissions is a private helper method that will filter an
   * input list of Mobile Apps and remove any apps that are (1) released before 1/1/2017, (2) do
   * not belong in the Educational or Productivity category, and (3) request Photo, Message,
   * or Location access:
   * @param listOfApps to be filtered, stored as a List of Mobile Apps
   * @return a filtered List of Mobile Apps, with the undesired specifications removed.
   * */
  private List<MobileApp> filterByReleaseDateCategoryAndPermissions(List<MobileApp> listOfApps){
    return listOfApps
        .stream()
        .filter(app -> app.getDateOfOriginalRelease().isAfter(DATE_THRESHOLD) &&
            (app.getAppCategory().equals(VALID_EDUCATIONAL_APP) ||
            app.getAppCategory().equals(VALID_PRODUCTIVITY_APP)) &&
                this.checkPermission(app.getRequestedPermissions()))
        .collect(Collectors.toList());
  }

  /**
   * checkPermission is a private helper method that will check if an input list of Permission
   * Requests contain any of the undesired permission request keywords (photo, message, and
   * location).
   * @param listOfPermissions to be checked, stored as a List of Strings.
   * @return True if none of the keywords are found in the list, or False if one or more keywords
   * have been identified in the List.
   * */
  private Boolean checkPermission(List<String> listOfPermissions){
    return listOfPermissions
        .stream()
        .noneMatch(permissions ->
            permissions.toUpperCase().contains(REMOVE_PHOTO_PERMISSION_REQUESTS) &&
                permissions.toUpperCase().contains(REMOVE_MESSAGES_PERMISSION_REQUESTS) &&
                permissions.toUpperCase().contains(REMOVE_LOCATION_PERMISSION_REQUESTS));
  }

  @Override
  public boolean hasNext() {
    return this.listOfApps.size() > 0;
  }

  @Override
  public Object next() {
    return this.listOfApps.remove(0);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MobileAppIterator)) {
      return false;
    }
    MobileAppIterator that = (MobileAppIterator) o;
    return Objects.equals(listOfApps, that.listOfApps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(listOfApps);
  }

  @Override
  public String toString() {
    return "MobileAppIterator{" +
        "listOfApps=" + listOfApps +
        '}';
  }
}
