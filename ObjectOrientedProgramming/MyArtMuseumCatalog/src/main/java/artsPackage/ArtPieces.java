package artsPackage;

import java.util.Arrays;
import java.util.Objects;

public abstract class ArtPieces implements IArtPieces {

  protected String name;
  protected Name[] creators;
  protected Name[] owners;
  protected Integer numberOfDaysAvailableForAuction; // will be 0
  protected Double latestAskingPrice;
  protected static final Double BASE_VALUE_FACTOR = 1.25;

  public ArtPieces(String name, Name[] creators, Name[] owners, Double latestAskingPrice) {
    this.name = name;
    this.creators = creators;
    this.owners = owners;
    this.numberOfDaysAvailableForAuction = 0;
    this.latestAskingPrice = latestAskingPrice;
  }

  @Override
  public Double calculateStartingBid() {
    return this.latestAskingPrice * BASE_VALUE_FACTOR;
  }

  public void setNumberOfDaysAvailableForAuction(Integer numberOfDaysAvailableForAuction)
      throws InvalidNumberOfDaysException {
    if (numberOfDaysAvailableForAuction < 0) {
      throw new InvalidNumberOfDaysException("Number of Days must be a non-negative integer.");
    }
    this.numberOfDaysAvailableForAuction = numberOfDaysAvailableForAuction;
  }

  public String getName() {
    return name;
  }

  public Name[] getCreators() {
    return creators;
  }

  public Name[] getOwners() {
    return owners;
  }

  public Integer getNumberOfDaysAvailableForAuction() {
    return numberOfDaysAvailableForAuction;
  }

  public Double getLatestAskingPrice() {
    return latestAskingPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArtPieces)) {
      return false;
    }
    ArtPieces artPieces = (ArtPieces) o;
    return Objects.equals(getName(), artPieces.getName()) && Arrays.equals(
        getCreators(), artPieces.getCreators()) && Arrays.equals(getOwners(),
        artPieces.getOwners()) && Objects.equals(getNumberOfDaysAvailableForAuction(),
        artPieces.getNumberOfDaysAvailableForAuction()) && Objects.equals(
        getLatestAskingPrice(), artPieces.getLatestAskingPrice());
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(getName(), getNumberOfDaysAvailableForAuction(),
        getLatestAskingPrice());
    result = 31 * result + Arrays.hashCode(getCreators());
    result = 31 * result + Arrays.hashCode(getOwners());
    return result;
  }

  @Override
  public String toString() {
    return "ArtPieces{" +
        "name='" + name + '\'' +
        ", creators=" + Arrays.toString(creators) +
        ", owners=" + Arrays.toString(owners) +
        ", numberOfDaysAvailableForAuction=" + numberOfDaysAvailableForAuction +
        ", latestAskingPrice=" + latestAskingPrice +
        '}';
  }
}
