package artsPackage;

import java.util.Objects;

public abstract class NonFungibleTokens extends ArtPieces {

  protected String tokenStandard;
  protected static final Integer AUCTION_DAYS_THRESHOLD = 60;
  protected static final Double DECREASE_NFT_FACTOR = 0.6;

  public NonFungibleTokens(String name, Name[] creators, Name[] owners,
      Double latestAskingPrice, String tokenStandard) {
    super(name, creators, owners, latestAskingPrice);
    this.tokenStandard = tokenStandard;
  }

  @Override
  public Double calculateStartingBid() {
    if (this.numberOfDaysAvailableForAuction > AUCTION_DAYS_THRESHOLD &&
        this.tokenStandard.equals("ERC-721")) {
      return super.calculateStartingBid() * DECREASE_NFT_FACTOR;
    }
    return super.calculateStartingBid();
  }

  public String getTokenStandard() {
    return tokenStandard;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NonFungibleTokens)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    NonFungibleTokens that = (NonFungibleTokens) o;
    return Objects.equals(getTokenStandard(), that.getTokenStandard());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getTokenStandard());
  }

  @Override
  public String toString() {
    return "NonFungibleTokens{" +
        "tokenStandard='" + tokenStandard + '\'' +
        "} " + super.toString();
  }
}
